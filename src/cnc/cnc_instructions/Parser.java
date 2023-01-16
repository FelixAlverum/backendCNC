package cnc.cnc_instructions;

import org.apache.batik.dom.svg.SAXSVGDocumentFactory;
import org.apache.batik.util.XMLResourceDescriptor;
import org.w3c.dom.svg.SVGDocument;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    /**
     * GCode in den VHS CNC Code übersetzen
     */

    private ArrayList<String> gCode = new ArrayList<String>();
    private ArrayList<String> svg = new ArrayList<String>();

    public void splitSvgCode(File svg) throws IOException {
        // get the SVG as String --> batik jar bekommen
        // https://stackoverflow.com/questions/26027313/how-to-load-and-parse-svg-documents

        StringReader reader = new StringReader("src/Test/DrawPanelSVG.svg");
        String uri = "file:src/Test/DrawPanelSVG.svg";
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory f = new SAXSVGDocumentFactory(parser);
        SVGDocument doc = f.createSVGDocument(uri, reader);

        System.out.println(doc);
    }

    public void resizeCoordinates(File svg){

    }
}
