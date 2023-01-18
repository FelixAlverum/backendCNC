package cnc.cnc_instructions;

import cnc.CncState;
import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.Document;
import org.w3c.dom.svg.SVGDocument;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static cnc.CncState.workpart_length;
import static cnc.CncState.workpart_width;

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
                    System.out.println(s);
                }
            }

            // Resize coordinates
            // Workpart in mm
            CncState.workpart_width = 150;
            CncState.workpart_length = 100;
            CncState.workpart_depth = 5;

            // Canvas in px
            CncState.canvas_width = Integer.valueOf(content.substring(content.indexOf("width=\""+7), content.indexOf("\" height")));
            CncState.canvas_length = Integer.valueOf(content.substring(content.indexOf("height=\""+7), content.indexOf("\" version")));

            // scale
            double scale = CncState.workpart_width / CncState.canvas_width;

            if (scale > (CncState.workpart_length / CncState.canvas_length) ){
                scale = CncState.workpart_length / CncState.canvas_length;
            }

            // ratio
            double rw = CncState.workpart_width / (CncState.workpart_width+CncState.workpart_length),
                    rl = CncState.workpart_length / (CncState.workpart_width+CncState.workpart_length);

            // https://www.calculatorsoup.com/calculators/math/ratios.php

            // calculate new width and length where the picture will be milled
            double new_w = workpart_width / (workpart_width / workpart_length);   //e.g. 15 / (15/10) -->  15 * 3/2
            double new_l = workpart_length / (workpart_width / workpart_length);


        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void resizeCoordinates(File svg) {

    }
}
