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


            // verbinde dich mit COM1
            SerialAPI serialAPI = new SerialAPI();
            serialAPI.initPort("COM4");
            serialAPI.openPort();
            serialAPI.initCNC();

            // Testprogramm
            Instructions instructions = new Instructions();
            instructions.goCoordinate(0, 0, 0);               // Zurück zum Nullpunkt
            instructions.moveAxis("x", 100 * 1000);      // Gehe 100 mm nach rechts
            instructions.moveAxis("y", 100 * 1000);      // Gehe 100 mm nach vorne
            instructions.moveAxis("z", 50 * 1000);       // Gehe 50 mm nach unten
            instructions.goCoordinate(0, 0, 0);               // Zurück zum Nullpunkt

            instructions.startTool(20000);
            Thread.sleep(7 * 1000);
            instructions.setzeVorschub(20 * 1000);
            instructions.goCoordinate(100 * 1000, 100 * 1000, 0);
            instructions.stopTool();

            Thread.sleep(7 * 1000);
            for (String s : CncState.cncLOG) {
                System.out.println("CncState.cncLOG " + s);
            }

            //serialAPI.closePort();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
