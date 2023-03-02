import cnc.CncState;
import cnc.cnc_instructions.Instructions;
import cnc.cnc_instructions.Parser;
import com.fazecast.jSerialComm.SerialPort;
import connection.cnc.SerialAPI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        // https://docs.google.com/presentation/d/1tZuU7LJWOnLlUOs3lPrZc1c8jhYz_w3FXTQQsNvyV_o/edit?usp=sharing
        System.out.println("Hello CNC Backend");

        Parser p = new Parser();
        Instructions i = new Instructions();
        p.splitSvgCode();

        ArrayList<int[]> gcode = p.getgCode();

        i.goCoordinate(10000,10000,10000);

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
            i.goCoordinate(koordinate[0], koordinate[1], koordinate[2]);
        }
    }
}