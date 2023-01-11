package cnc.cnc_instructions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Parser {
    /**
     * GCode in den VHS CNC Code übersetzen
     */

    private ArrayList<String> gCode = new ArrayList<String>();
    private ArrayList<String> svg = new ArrayList<String>();

    public void splitSvgCode(File svg){
        // get the SVG as String --> batik jar bekommen
        // https://stackoverflow.com/questions/26027313/how-to-load-and-parse-svg-documents
    }

    public void resizeCoordinates(File svg){

    }
}
