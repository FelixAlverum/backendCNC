package Test;

import cnc.CncState;
import com.fazecast.jSerialComm.SerialPort;
import connection.cnc.SerialAPI;

import java.util.Arrays;
import java.util.stream.Collectors;

public class TestCncConnection {

    public static void main (String[] args){

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

            // for(int i = 1; i < 100; i++)
            // serialAPI.sendStringToComm("?E"+i+";");

            //serialAPI.sendStringToComm("T1;");
            serialAPI.sendStringToComm("OS2,1;");
            serialAPI.sendStringToComm("GA0,0;");
            serialAPI.sendStringToComm("GA0,,-1000;");
            serialAPI.sendStringToComm("RVS20000;");
            serialAPI.sendStringToComm("VS5000;");
            serialAPI.sendStringToComm("PA,,5000;");
            serialAPI.sendStringToComm("VS50000;");
            serialAPI.sendStringToComm("PA100000,0,5000;");
            serialAPI.sendStringToComm("PA100000,-100000,5000;");
            serialAPI.sendStringToComm("PA0,-100000,5000;");
            serialAPI.sendStringToComm("PA0,0,5000;");
            serialAPI.sendStringToComm("VS5000;");
            serialAPI.sendStringToComm("PA,,10000;");
            serialAPI.sendStringToComm("VS50000;");
            serialAPI.sendStringToComm("PA100000,0,10000;");
            serialAPI.sendStringToComm("PA100000,-100000,10000;");
            serialAPI.sendStringToComm("PA0,-100000,10000;");
            serialAPI.sendStringToComm("PA0,0,10000;");
            serialAPI.sendStringToComm("GA,,-1000;");
            serialAPI.sendStringToComm("OS2,0;");
            serialAPI.sendStringToComm("RVS0;");
            serialAPI.sendStringToComm("T2;");
            serialAPI.sendStringToComm("OS2,1;");
            serialAPI.sendStringToComm("GA120000,0;");
            serialAPI.sendStringToComm("GA0,,-1000;");
            serialAPI.sendStringToComm("RVS18000;");
            serialAPI.sendStringToComm("VS5000;");
            serialAPI.sendStringToComm("PA,,5000;");
            serialAPI.sendStringToComm("VS30000;");
            serialAPI.sendStringToComm("PA220000,0,5000;");
            serialAPI.sendStringToComm("PA220000,-100000,5000;");
            serialAPI.sendStringToComm("PA120000,100000,5000;");
            serialAPI.sendStringToComm("PA120000,0,5000;");
            serialAPI.sendStringToComm("VS5000;");
            serialAPI.sendStringToComm("PA,,10000;");
            serialAPI.sendStringToComm("VS30000;");
            serialAPI.sendStringToComm("PA220000,0,10000;");
            serialAPI.sendStringToComm("PA220000,-100000,10000;");
            serialAPI.sendStringToComm("PA120000,-100000,10000;");
            serialAPI.sendStringToComm("PA120000,0,10000;");
            serialAPI.sendStringToComm("GA,,-1000;");
            serialAPI.sendStringToComm("OS2,0;");
            serialAPI.sendStringToComm("RVS0;");
            serialAPI.sendStringToComm("T0;");


            Thread.sleep(7 *1000);
            for (String s:CncState.cncLOG) {
                System.out.println("CncState.cncLOG " + s);
            }

            //serialAPI.closePort();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
