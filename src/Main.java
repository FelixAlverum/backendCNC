import cnc.CncState;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import connection.cnc.SerialAPI;

import java.io.InputStream;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello CNC Backend");


        try {
            // Serial Port Simulieren --> virtual port verwenden

            // ------------------ API testen ------------------

            // zeige alle seriellen ports an
            System.out.println(Arrays.stream(SerialPort.getCommPorts())
                    .map(SerialPort::getSystemPortName)
                    .collect(Collectors.toList()));


            // verbinde dich mit COM1
            SerialAPI serialAPI = new SerialAPI();
            serialAPI.initPort("COM1");
            serialAPI.openPort();

            // sende daten an die CNC
            String command = "Hallo ich sende etwas zum test";
            System.out.println("Sende an CNC: " + command);
            serialAPI.sendStringToComm(command);

            // empfange daten von der CNC --> sollte ein Event sein



            //serialAPI.closePort();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}