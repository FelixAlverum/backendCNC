package cnc.cnc_instructions;

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
            content = content.replaceAll("[\\t\\n\\r]+"," ");

            while (content.contains("d=\"")) {
                int d = content.indexOf("d=\"") + 3;
                if(d == -1){
                    d = 0;
                }
                int z = content.indexOf("Z\"/>");
                if(z == -1){
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
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void resizeCoordinates(File svg) {

    }
}
