package cnc.cnc_instructions;

import cnc.CncState;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class Parser {
    /**
     * GCode in den VHS CNC Code übersetzen
     */

    private ArrayList<int[]> gCode = new ArrayList<int[]>();
    private ArrayList<String[]> svg = new ArrayList<String[]>();

    public void splitSvgCode() throws IOException {
        // get the SVG as String --> batik jar bekommen
        // https://stackoverflow.com/questions/26027313/how-to-load-and-parse-svg-documents

        try {
            String content = Files.readString(Path.of("src/Test/DrawPanelSVG.svg"));
            content = content.replaceAll("[\\t\\n\\r]+", " ");

            while (content.contains("d=\"")) {
                int d = content.indexOf("d=\"") + 3;
                if (d == -1) {
                    d = 0;
                }
                int z = content.indexOf("Z\"/>");
                if (z == -1) {
                    z = content.length();
                }
                this.svg.add(content.substring(d, z).split(" "));
                content = content.substring(content.indexOf("Z\"/>") + 4);
            }

            for (String[] path : this.svg) {
                for (String s : path) {
                    if (s.equals("") || s == null) {
                        continue;
                    }
                    // System.out.println(s);
                }
            }

            // Resize coordinates
            // Workpart in mm
            CncState.workpart_width = 150;
            CncState.workpart_length = 100;
            CncState.workpart_depth = 5;

            // Canvas in px
            content = Files.readString(Path.of("src/Test/DrawPanelSVG.svg"));
            content = content.replaceAll("[\\t\\n\\r]+", " ");

            int start = content.indexOf("width") + 7;
            int end = content.indexOf("\" height");
            CncState.canvas_width = Integer.valueOf(content.substring(start, end));

            start = content.indexOf("height") + 8;
            end = content.indexOf("\" version");
            CncState.canvas_length = Integer.valueOf(content.substring(start, end));

            // scale
            System.out.println("CncState.workpart_width " + CncState.workpart_width);
            System.out.println("CncState.canvas_width " + CncState.canvas_width);
            System.out.println("CncState.workpart_length " + CncState.workpart_length);
            System.out.println("CncState.canvas_length " + CncState.canvas_length);

            double pxCmScale = (double) CncState.workpart_width / (double) CncState.canvas_width;

            System.out.println("pxCmScale " + pxCmScale);

            if (pxCmScale > ((double) CncState.workpart_length / (double) CncState.canvas_length)) {
                pxCmScale = CncState.workpart_length / CncState.canvas_length;
            }

            System.out.println("pxCmScale " + pxCmScale);

            // ratio
            double gcf = gcd(CncState.canvas_length, CncState.canvas_width);
            System.out.println("GFC: " + gcf);

            // calculate new width and length where the picture will be milled
            double new_w = pxCmScale * (CncState.canvas_width / gcf);
            double new_l = pxCmScale * (CncState.canvas_length / gcf);

            System.out.println("neue länge: " + pxCmScale + " /(" + CncState.canvas_length + "/" + gcf + ") = " + new_w);
            System.out.println("neue länge: " + pxCmScale + " /(" + CncState.canvas_width + "/" + gcf + ") = " + new_l);

            // calculate offset
            double offsetX = ((double) CncState.workpart_width - new_w) / 2;
            double offsetY = ((double) CncState.workpart_length - new_l) / 2;

            System.out.println("offsetX: " + offsetX);
            System.out.println("offsetY: " + offsetY);

            double millimeterPixelRatio = new_w / (double) CncState.canvas_length;
            System.out.println("millimeterPixelRatio: " + millimeterPixelRatio);

            String newSvg = "<svg width=\"" + CncState.workpart_width + "mm\" height=\"" + CncState.workpart_length + "mm\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n";

            for (String[] path : this.svg) {
                newSvg += "<path fill=\"white\" stroke=\"red\" stroke-width=\"2mm\" opacity=\"1.0\"\n d=\"";
                for (String s : path) {
                    if (s.equals("") || s == null) {
                        continue;
                    }
                    if (s.equals("L")) {
                        newSvg += " L ";
                    } else if (s.equals("Q")) {
                        newSvg += " L "; // TODO bei Q nur die nächsten 2 Werte übernehmen und nicht alle folgenden
                    } else if (s.equals("M")) {
                        newSvg += " M ";
                    } else {
                        double temp = Double.valueOf(s);
                        temp = temp * millimeterPixelRatio;
                        newSvg += " " + temp;
                    }
                }
                newSvg += " Z\"/>";
            }
            newSvg += "</svg>";


            // G-Code Koordinaten kalkulieren für goCoordinate Befehle
            for (int path = 0; path < svg.size(); path++) {
                svg.get(path);
                int i = 0;
                while (i < svg.get(path).length) {
                    String s = svg.get(path)[i];

                    // Mache nichs wenn leeres Feld
                    if (s.equals("") || s == null) {
                        continue;
                    }

                    // -1 = Nehme den Wert aus Absolute (CncState)
                    // -2 = Nehme den Wert aus Absolute_Z und rechne ein Offset von 1cm nach oben (CncState)
                    // -3 = Nehme den Wert aus Absolute_Z und rechne ein Offset von 1cm nach unten (CncState)


                    if (s.equals("L") || s.equals("Q")) {
                        // L = Linear im Material fräsen
                        // Q = Rundung im Material fräsen
                        // --> Abstände sind so gering dass auch Linear gefräst werden kann
                        // Wichtig nur die ersten 2 Werte übernehmen

                        int x = (int) Math.floor(Double.valueOf(svg.get(path)[i + 1]) * millimeterPixelRatio * 1000);
                        int y = (int) Math.floor(Double.valueOf(svg.get(path)[i + 2]) * millimeterPixelRatio * 1000);
                        int z = -1;

                        // Koordinate in gCode einfügen
                        gCode.add(new int[]{x, y, z});
                    } else if (s.equals("M")) {
                        // Bewege den Fräskopf an einen neuen Punkt
                        int x = -1;
                        int y = -1;
                        int z = -2;
                        gCode.add(new int[]{x, y, z});

                        x = (int) Math.floor(Double.valueOf(svg.get(path)[i + 1]) * millimeterPixelRatio * 1000);
                        y = (int) Math.floor(Double.valueOf(svg.get(path)[i + 2]) * millimeterPixelRatio * 1000);
                        z = -3;
                        gCode.add(new int[]{x, y, z});
                    } else {
                        // Fehlermeldung + Einfach ignorieren
                        System.out.println("Unbekannter Befehl: " + s);
                    }

                    // Neuen Index herausfinden

                    int j = 2;

                    if((i+j) >= svg.get(path).length){
                        break;
                    }

                    while (!isString(svg.get(path)[i + j]) && (i + j < svg.get(path).length)) {
                        j++;
                        if(i+j >= svg.get(path).length){
                            break;
                        }
                    }

                    i = i + j;      // Index auf den neuen String (nächsten Befehl) setzen
                    if(i >= svg.get(path).length){
                        break;
                    }

                }

            }

            //System.out.println(newSvg);

            for (int[] i : gCode) {
                System.out.println("x: " + i[0] + "\ty: " + i[1] + "\tz: " + i[2]);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private boolean isString(String s) {
        try {
            double d = Double.valueOf(s);
            return false;
        } catch (Exception e) {
            // Egal
        }
        return true;
    }

    public void resizeCoordinates(File svg) {

    }

    /*
     * Größter gemeinsamer Faktor
     */
    private int gcd(int a, int b) {
        if (a == 0)
            return b;

        while (b != 0) {
            if (a > b)
                a = a - b;
            else
                b = b - a;
        }

        return a;
    }
}
