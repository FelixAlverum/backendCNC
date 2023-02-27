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

    private ArrayList<String> gCode = new ArrayList<String>();
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

            if (pxCmScale > ((double) CncState.workpart_length / (double)CncState.canvas_length) ){
                pxCmScale = CncState.workpart_length / CncState.canvas_length;
            }

            System.out.println("pxCmScale " + pxCmScale);

            // ratio
            double gcf = gcd(CncState.canvas_length, CncState.canvas_width);
            System.out.println("GFC: " + gcf);

            // calculate new width and length where the picture will be milled
            double new_w = pxCmScale * (CncState.canvas_width / gcf);
            double new_l = pxCmScale * (CncState.canvas_length / gcf);

            System.out.println("neue länge: " + pxCmScale + " /(" + CncState.canvas_length +"/"+ gcf +") = "+ new_w);
            System.out.println("neue länge: " + pxCmScale + " /(" + CncState.canvas_width +"/"+ gcf +") = "+ new_l);

            // calculate offset
            double offsetX = ((double)CncState.workpart_width - new_w) / 2;
            double offsetY = ((double)CncState.workpart_length - new_l) / 2;

            System.out.println("offsetX: " + offsetX);
            System.out.println("offsetY: " + offsetY);

            double millimeterPixelRatio = new_w / (double)CncState.canvas_length;
            System.out.println("millimeterPixelRatio: " + millimeterPixelRatio);

            String newSvg = "<svg width=\""+CncState.workpart_width+"mm\" height=\""+CncState.workpart_length+"mm\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n";

            int qCase = 0;

            for (String[] path : this.svg) {
                newSvg += "<path fill=\"white\" stroke=\"red\" stroke-width=\"2mm\" opacity=\"1.0\"\n d=\"";
                for (String s : path) {
                    if (s.equals("") || s == null) {
                        continue;
                    }
                    if(s.equals("L")){
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




            System.out.println(newSvg);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
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
