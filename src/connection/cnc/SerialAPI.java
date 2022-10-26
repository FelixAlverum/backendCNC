package connection.cnc;

import com.fazecast.jSerialComm.SerialPort;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SerialAPI {

    /**
     * FYI
     * Diese Dokumentation beinhaltet die wichtigsten Informationen zur Erstellung eines Postprozessors, um
     * eine vhf-Fräsmaschine mit 3 Achsen (x, y und z-Achse) anzusteuern. Die hier beschriebenen
     * Befehlsformate sind für die Steuerungen CNC 980 und CNC 580 ausgelegt.
     * Bearbeitungsbefehle werden in einer Ausgabedatei gespeichert. Mit einem Terminal-Programm werden
     * die darin enthaltenen Befehle schließlich über eine USB-Schnittstelle oder seriell über eine RS-232-
     * Schnittstelle an die Steuerung übertragen.
     *
     * Bei einer seriellen Datenübertragung wird die serielle Schnittstelle der Steuerung durch folgende
     * Parameter definiert (parameterisieren durch übetragen der Initialisierungsdatei):
     * Baudrate: 38400
     * Data Bits: 8
     * Stop Bits: 1
     * Parity: keine
     * Flow Control: Xon/Xoff
     *
     * TODO add jSerialComm to externalJARs; download from https://fazecast.github.io/jSerialComm/
     *
     * Interessant:
     * http://www.codeplastic.com/2017/06/05/g-code-with-processing-part-1/
     * https://winder.github.io/ugs_website/
     */

    private SerialPort cncPort;
    public void connectCnc(){
        /*
         * TODO Parameter für Ports herausfinden und setzen:
         * - portDescriptor: Port_#0010.Hub_#0001 (allgemeinere Lösung finden)
         * - safetySleepTime:
         * - deviceSendQueueSize:
         * - deviceReceiveQueueSize:
         */
        String portDescriptor = "Port_#0010.Hub_#0001";
        int safetySleepTime = 10;
        int deviceSendQueueSize = 10;
        int deviceReceiveQueueSize = 10;

        cncPort = SerialPort.getCommPort(portDescriptor);
        if(cncPort.openPort(safetySleepTime,deviceSendQueueSize,deviceReceiveQueueSize)==false){
            System.out.println("Can't open port.");
        }

    }

    /**
     * Send gCode File to the CNC
     * @param gCode Path to the gCode File
     * @throws IOException
     */
    public void sendFile(Path gCode) throws IOException {
        byte[] buffer = Files.readAllBytes(gCode);
        long bytesToWrite = buffer.length;

        // TODO falls unterbrochen wird (Werkzeugwechsel, Programmabbruch) darüber wieder einsteigen
        long offset = 0;

        cncPort.writeBytes(buffer, bytesToWrite, offset);
    }

    /**
     * Send a single gCode Instrucion to CNC
     */
    public void sendInstruction(){
        //TODO keine Ahnung wie das umgesetzt wird
    }

}
