package connection.cnc;

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
     * https://diymachining.com/g-code-example/
     */

}
