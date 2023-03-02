package Test;

import cnc.CncState;
import cnc.cnc_instructions.Instructions;
import com.fazecast.jSerialComm.SerialPort;
import connection.cnc.SerialAPI;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TestCncConnection {

    public static void main(String[] args) {

        try {
            /*
             * 1. Serial Port emmulieren --> Virtual Serial Port Driver Pro öffnen
             * 2. Alle seriellen Ports anzeigen
             * 3.
             */

            // ------------------ API testen ------------------

            // zeige alle seriellen ports an
            System.out.println(Arrays.stream(SerialPort.getCommPorts())
                    .map(SerialPort::getSystemPortName)
                    .collect(Collectors.toList()));


            // verbinde dich mit COMPort
            CncState.CNC_CONNECTION = new SerialAPI();
            CncState.CNC_CONNECTION.initPort("COM4");
            CncState.CNC_CONNECTION.openPort();
            CncState.CNC_CONNECTION.initCNC();

            // Testprogramm

            //Thread.sleep(10 * 1000);

            Instructions instructions = new Instructions();
            instructions.goCoordinate(0, 0, 0);               // Zurück zum Nullpunkt
            instructions.goCoordinate(30 * 10 * 1000, 30 * 10 * 1000, 1000);      // Gehe zu koordinate
            instructions.moveAxis("x", -2 * 10 * 1000);      // Gehe 10 mm nach rechts
            instructions.moveAxis("y", 14 * 10 * 1000);      // Gehe 100 mm nach vorne
            instructions.moveAxis("z", 90 * 1000);       // Gehe 50 mm nach unten

            instructions.goCoordinate(100000, 100000, 2000);               // Zurück zum Nullpunkt
            instructions.moveAxis("x", 10 * 1000);      // Gehe 10 mm nach rechts
            instructions.moveAxis("y", 10 * 1000);      // Gehe 100 mm nach vorne
            instructions.moveAxis("z", 50 * 1000);       // Gehe 50 mm nach unten
            instructions.goCoordinate(0, 0, 0);               // Zurück zum Nullpunkt
            //instructions.startTool(20000);              // Umdrehungen pro Minute einstellen
            instructions.setzeVorschub(10 * 1000);                      // Vorschub setzen
            instructions.goCoordinate(100 * 1000, 100 * 1000, 0);   // Gehe zu einer Koordinate
            instructions.stopTool();                                    // Spindel ausschalten
            instructions.goCoordinate(0, 0, 0);

            CncState.CNC_CONNECTION.sendStringToComm("?PA;");
            Thread.sleep(5000);
            CncState.CNC_CONNECTION.sendStringToComm("?PA;");
            Thread.sleep(5000);
            CncState.CNC_CONNECTION.sendStringToComm("?PA;");
            Thread.sleep(5000);
            CncState.CNC_CONNECTION.sendStringToComm("?PA;");

            System.out.println("\n\n\nEnde Testprogramm\n\n\n\n");

            //CncState.CNC_CONNECTION.closePort();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
