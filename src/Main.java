import cnc.CncState;
import cnc.cnc_instructions.Parser;
import com.fazecast.jSerialComm.SerialPort;
import connection.cnc.SerialAPI;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello CNC Backend");

        Parser p = new Parser();
        p.splitSvgCode(null);

    }
}