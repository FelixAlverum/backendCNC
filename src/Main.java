import com.fazecast.jSerialComm.SerialPort;
import connection.cnc.SerialAPI;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello CNC Backend");


        try{
            // Serial Port Simulieren


            // API testen
            SerialAPI testverbindung = new SerialAPI();

            System.out.println(Arrays.stream(SerialPort.getCommPorts())
                    .map(SerialPort::getSystemPortName)
                    .collect(Collectors.toList()));

            testverbindung.openPort();
            testverbindung.initPort("Port_#0010.Hub_#0001");


            // Sende das Testprogramm von vhf an die CNC
            testverbindung.sendStringToComm("T1;OS2,1;GA0,0;GA0,,-1000;RVS20000;VS5000;PA,,5000;VS50000;PA100000,0,5000;\n" +
                    "PA100000,-100000,5000;PA0,-100000,5000;PA0,0,5000;VS5000;PA,,10000;VS50000;PA100000,0,10000;\n" +
                    "PA100000,-100000,10000;PA0,-100000,10000;PA0,0,10000;GA,,-1000;OS2,0;RVS0;T2;OS2,1;GA120000,0;\n" +
                    "GA0,,-1000;RVS18000;VS5000;PA,,5000;VS30000;PA220000,0,5000;PA220000,-100000,5000;PA120000,-\n" +
                    "100000,5000;PA120000,0,5000;VS5000;PA,,10000;VS30000;PA220000,0,10000;PA220000,-100000,10000;\n" +
                    "PA120000,-100000,10000;PA120000,0,10000;GA,,-1000;OS2,0;RVS0;T0;");

            testverbindung.closePort();

            System.out.println("Ende");


        }catch (Exception e){
            e.printStackTrace();
        }

    }
}