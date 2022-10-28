package connection.cnc;

import org.apache.commons.lang3.StringUtils;

import cnc.CncState;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SerialAPI implements SerialPortDataListener {

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
     * TODO add apache Commons Lang to externalJARs; download from https://commons.apache.org/proper/commons-lang/download_lang.cgi
     *
     * Interessant:
     * http://www.codeplastic.com/2017/06/05/g-code-with-processing-part-1/
     * https://github.com/winder/Universal-G-Code-Sender/tree/master/ugs-core/src/com/willwinder/universalgcodesender
     * https://github.com/winder/Universal-G-Code-Sender/blob/master/ugs-core/src/com/willwinder/universalgcodesender/connection/JSerialCommConnection.java
     */

    public SerialPort serialPort;

    /**
     * Open the serial port to the CNC
     * @return boolean if connection was succesful (true) or not (false)
     * @throws Exception for connection errors
     */
    public boolean openPort() throws Exception{
        if (serialPort == null){
            throw new ConnectException("Connection was not initialized");
        }

        if(serialPort.isOpen()){
            throw new ConnectException("Can not connect, serial port is already open");
        }

        if(serialPort.openPort() == true){
            CncState.cnc_state = CncState.cnc_state.INIT;
            return true;
        }else {
            CncState.cnc_state = CncState.cnc_state.ERROR;
            return false;
        }
    }

    /**
     * Initialize the port to the CNC
     * @param portDescriptor name of the port to connect to
     * @throws Exception
     */
    public void initPort(String portDescriptor) throws Exception{
        // close ports before init
        if (serialPort != null){
            closePort();
        }

        // variables for initialisation
        portDescriptor = "";
        int baudrate = 38400;                       // Wert aus moodle entnommen
        int data_bits = 8;                          // Wert aus moodle entnommen
        int stop_bits= SerialPort.ONE_STOP_BIT;     // Wert aus moodle entnommen
        int parity = SerialPort.NO_PARITY;          // Wert aus moodle entnommen
        int flow_control = SerialPort.FLOW_CONTROL_XONXOFF_IN_ENABLED; // Wert aus moodle entnommen

        // init port
        serialPort = SerialPort.getCommPort(portDescriptor);
        serialPort.setParity(parity);
        serialPort.setNumStopBits(stop_bits);
        serialPort.setNumDataBits(data_bits);
        serialPort.addDataListener(this);
        serialPort.setBaudRate(baudrate);
        serialPort.setFlowControl(flow_control);

        CncState.cnc_state = CncState.cnc_state.CONNECTED;
    }

    /**
     * Removes listener an closes serial port
     * @throws Exception
     */
    public void closePort() throws Exception{
        if(serialPort != null){
            serialPort.removeDataListener();
            serialPort.closePort();

            CncState.cnc_state = CncState.cnc_state.DISCONNECTED;
        }
    }

    public void sendByteImmediately(byte b) throws Exception {
        serialPort.writeBytes(new byte[]{b}, 1);
    }

    public void sendStringToComm(String command) throws Exception {
        serialPort.writeBytes(command.getBytes(), command.length());
    }

    public boolean isOpen() {
        return serialPort.isOpen();
    }

    @Override
    public int getListeningEvents() {
        return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        if (serialPortEvent.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE){
            return;
        }

        byte [] newData = new byte[serialPort.bytesAvailable()];
        int numRead = serialPort.readBytes(newData, newData.length);

        // TODO keine Ahnung was von der CNC zurückkommt. Gut möglich, dass kein String verwendet werden kann
        String event = new String(newData, 0, numRead);
        StringBuilder inputBuffer = new StringBuilder();

        inputBuffer.append(StringUtils.remove(event, '\r'));

        // Only continue if there is a line terminator and split out command(response).
        if (!inputBuffer.toString().contains("\n")) {
            return;
        }

        // Split with the -1 option will give an empty string at
        // the end if there is a terminator there as well.
        String[] messages = inputBuffer.toString().split("\\n", -1);
        for (int i = 0; i < messages.length; i++) {
            // Make sure this isn't the last command.
            if ((i + 1) < messages.length) {

                // TODO notifyListeners(messages[i]); einbauen

                // Append last command to input buffer because it didn't have a terminator.
            } else {
                inputBuffer.setLength(0);
                inputBuffer.append(messages[i]);
            }
        }
    }
}
