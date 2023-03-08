import cnc.CncState;
import cnc.cnc_instructions.Instructions;
import cnc.cnc_instructions.Parser;
import com.fazecast.jSerialComm.SerialPort;
import connection.cnc.SerialAPI;
import jankovicsandras.imagetracer.ImageTracer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        // https://docs.google.com/presentation/d/1tZuU7LJWOnLlUOs3lPrZc1c8jhYz_w3FXTQQsNvyV_o/edit?usp=sharing
        System.out.println("Hello CNC Backend");

        HashMap<String, Float> options = new HashMap<String, Float>();

        // Tracing
        options.put("ltres", 1f);
        options.put("qtres", 1f);
        options.put("pathomit", 8f);

        // Color quantization
        options.put("colorsampling", 1f); // 1f means true ; 0f means false: starting with generated palette
        options.put("numberofcolors", 16f);
        options.put("mincolorratio", 0.02f);
        options.put("colorquantcycles", 8f);

        // SVG rendering
        options.put("scale", 1f);
        options.put("roundcoords", 1f); // 1f means rounded to 1 decimal places, like 7.3 ; 3f means rounded to 3 places, like 7.356 ; etc.
        options.put("lcpr", 0f);
        options.put("qcpr", 0f);
        options.put("desc", 0f); // 1f means true ; 0f means false: SVG descriptions deactivated
        options.put("viewbox", 0f); // 1f means true ; 0f means false: fixed width and height

        // Selective Gauss Blur
        options.put("blurradius", 0f); // 0f means deactivated; 1f .. 5f : blur with this radius
        options.put("blurdelta", 50f); // smaller than this RGB difference will be blurred

        String content = ImageTracer.imageToSVG("./src/Test/DrawPanelPNG.png", options, null);


        Parser p = new Parser();
        Instructions i = new Instructions();
        p.splitSvgCode(content);

        ArrayList<int[]> gcode = p.getgCode();
        // verbinde dich mit COMPort
        CncState.CNC_CONNECTION = new SerialAPI();
        CncState.CNC_CONNECTION.initPort("COM4");
        CncState.CNC_CONNECTION.openPort();
        CncState.CNC_CONNECTION.initCNC();

        i.setzeVorschub(38 * 1000);

        i.startTool(20000);

        i.goCoordinate(40000,20000,130000);
        i.goCoordinate(40000,20000,142300);

        for (int[] koordinate:gcode) {
            if (koordinate[0] == -1){
                koordinate[0] = CncState.absolute_X;
            }
            if (koordinate[1] == -1){
                koordinate[1] = CncState.absolute_Y;
            }
            if (koordinate[2] == -1){
                koordinate[2] = CncState.absolute_Z;
            }
            if (koordinate[2] == -2){
                koordinate[2] = CncState.absolute_Z - 10 * 1000;
            }
            if (koordinate[2] == -3){
                koordinate[2] = CncState.absolute_Z + 10 * 1000;
            }
            int x  = koordinate[0];
            int y  = koordinate[1];
            int z  = koordinate[2];

            i.goCoordinate(x , y , z);
        }

        i.goCoordinate(0,0,0);
        i.stopTool();

    }
}