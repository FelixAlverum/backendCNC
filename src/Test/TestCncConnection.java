package Test;

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

            // sende daten an die CNC
            String command = "/Date (YYYY-MM-DD)                                #DES \"2013-01-24\"                    \\\n" +
                    "/Author                                           #DES \"tr\"                            \\\n" +
                    "/Custumer                                         #DES \"Akademie fŸr Datenverarbeitung\"\\\n" +
                    "/Cenon version (3.6x)                             #DES \"4.0.1\"                         \\\n" +
                    "/Additional software                              #DES \"CNCTerm\"                       \\\n" +
                    "/Serial Number                                    #DES \"CAM 132271C0220\"               \\\n" +
                    "/CAM-system (CAM100)                              #DES \"CAM 220\"                       \\\n" +
                    "/Computer-Typ                                     #DES \"PC\"                            \\\n" +
                    "/Operating System                                 #DES \"WIN 7\"                         \\\n" +
                    "/CNC-controller (CNC550)                          #DES \"CNC 580+/5\"                    \\\n" +
                    "/CNC-controller Version                           #DES \"V10.65\"                        \\\n" +
                    "/Spindle-Typ (SF600P)                             #DES \"SPC1000\"                       \\\n" +
                    "/Frequenzy-converter                              #DES \"SFU 1500-1\"                    \\\n" +
                    "/Tool Change (Yes/No)                             #DES \"NO\"                            \\\n" +
                    "/Compressor Cooling                               #DES \"NO\"                            \\\n" +
                    "/Tangential Cutting                               #DES \"NO\"                            \\\n" +
                    "/Levelling (Yes/No)                               #DES \"NO\"                            \\\n" +
                    "/Automatic cooling (Yes/No)                       #DES \"NO\"                            \\\n" +
                    "/Minimum quantity lubrication (Yes/No)            #DES \"NO\"                            \\\n" +
                    "/Surface sensor (Yes/No)                          #DES \"YES\"                           \\\n" +
                    "/Additional switches (Yes-Manuell/Yes-Auto/No)    #DES \"NO\"                            \\\n" +
                    "/Control Paneel (Yes/No)                          #DES \"YES\"                           \\\n" +
                    "/Housing  (Yes/No)                                #DES \"NO\"                            \\\n" +
                    "/Housing with lift gate  (Yes/No)                 #DES \"NO\"                            \\\n" +
                    "/System Rack  (Yes/No)                            #DES \"NO\"                            \\\n" +
                    "/Safety Devices (light grid) (Yes/No)             #DES \"NO\"                            \\\n" +
                    "/Dust Extraction Unit (Yes/No)                    #DES \"YES\"                           \\\n" +
                    "/Line Laser Unit (Yes/No)                         #DES \"NO\"                            \\\n" +
                    "/Optical Workpiece Recognition (Yes/No)           #DES \"NO\"                            \\\n" +
                    "/Special Vakuum Tables (Yes/No)                   #DES \"NO\"                            \\\n" +
                    "/OUT 1                                            #DES \"NO\"                            \\\n" +
                    "/OUT 2                                            #DES \"NO\"                            \\\n" +
                    "/OUT 3                                            #DES \"NO\"                            \\\n" +
                    "/OUT 4                                            #DES \"NO\"                            \\\n" +
                    "/IN 1                                             #DES \"NO\"                            \\\n" +
                    "/IN 2                                             #DES \"NO\"                            \\\n" +
                    "/IN 3                                             #DES \"Surface sensor\"                \\\n" +
                    "/IN 4                                             #DES \"NO\"                            \\\n" +
                    "/HALT                                             #DES \"NO\"                            \\\n" +
                    "/ GLOBALE MASCHINENPARAMETER \\\n" +
                    "P0010=38400;   / Baudrate                                         \\\n" +
                    "P0011=1;       / Not-Aus bei Verfahrbereichsende                  \\\n" +
                    "P0014=1008;    / Wartezeit Haltestromabenkung in ms, 0=Aus        \\\n" +
                    "P0020=75;      / Rampenanfahrgeschwindigkeit (Vollschritte) in Hz \\\n" +
                    "P0022=75;      / Rampensteigung                                   \\\n" +
                    "P0023=10000;   / Maximale Rampenfrequenz (Vollschritte) in Hz     \\\n" +
                    "P0024=100;     / Maximale Winkelaenderung in Hz                   \\\n" +
                    "P0025=20000;   / Default Bahngeschwindigkeit in um/s              \\\n" +
                    "P0026=1;       / Konstante Bahngeschwindigkeit, 1=An, 0=Aus       \\\n" +
                    "\n" +
                    "\n" +
                    "/ X-ACHSENPARAMETER \\\n" +
                    "P1001=X;       / Achsbezeichnung, 0=Achse nicht vorhanden         \\\n" +
                    "P1002=1;       / Interne Aufloesung von 1-8fach, 1=Default        \\\n" +
                    "P1003=0;       / Achskopplung, 0=Aus                              \\\n" +
                    "P1004=50000;   / Schrittaufloesung in Vollschritten pro Meter     \\\n" +
                    "P1005=0;       / Motorpolung, 0=Default, 1=Umgedreht              \\\n" +
                    "P1007=0;       / Verfahren im Halt, 0=Nein, 1=Achse auf Null      \\\n" +
                    "P1011=25000;   / Referenzfahrtgeschwindigkeit in um/s             \\\n" +
                    "P1013=100000;  / Maximale Achsengeschwindigkeit (Eilgang) in um/s \\\n" +
                    "P1020=1;       / Endschalter 1, 1=Oeffner, 2=Schliesser           \\\n" +
                    "P1021=0;       / Endschalter 2, 0=Keiner, 1=Siehe Endschalter 1   \\\n" +
                    "P1022=1000;    / Abstand des Referenzpunktes vom Endschalter 1    \\\n" +
                    "P1023=540000;  / Maximaler Arbeitsbereich in um                   \\\n" +
                    "P1024=0;       / Wegfahrweg in um vor RF, 0=Deaktiviert           \\\n" +
                    "P1025=0;       / NotAus bei Fahrt in Endschalter, 0=Ja, 1=Nein    \\\n" +
                    "\n" +
                    "P1030=75;      / GA-Rampenanfahrgeschwindigkeit in Hz             \\\n" +
                    "P1031=75;      / GA-Rampensteigung                                \\\n" +
                    "P1032=10000;   / GA-Maximale Rampenfrequenz in Hz                 \\\n" +
                    "\n" +
                    "P1006=4000;    / Motorstrom in mA (nur bei CNC550/800), 3000=3A   \\\n" +
                    "\n" +
                    "\n" +
                    "/ Y-ACHSENPARAMETER \\\n" +
                    "P2001=Y;       / Achsbezeichnung, 0=Achse nicht vorhanden         \\\n" +
                    "P2002=1;       / Interne Aufloesung von 1-8fach, 1=Default        \\\n" +
                    "P2003=0;       / Achskopplung, 0=Aus                              \\\n" +
                    "P2004=50000;   / Schrittaufloesung in Vollschritten pro Meter     \\\n" +
                    "P2005=0;       / Motorpolung, 0=Default, 1=Umgedreht              \\\n" +
                    "P2007=0;       / Verfahren im Halt, 0=Nein, 1=Achse auf Null      \\\n" +
                    "P2011=25000;   / Referenzfahrtgeschwindigkeit in um/s             \\\n" +
                    "P2013=100000;  / Maximale Achsengeschwindigkeit (Eilgang) in um/s \\\n" +
                    "P2020=1;       / Endschalter 1, 1=Oeffner, 2=Schliesser           \\\n" +
                    "P2021=0;       / Endschalter 2, 0=Keiner, 1=Siehe Endschalter 1   \\\n" +
                    "P2022=1000;    / Abstand des Referenzpunktes vom Endschalter 1    \\\n" +
                    "P2023=500000;  / Maximaler Arbeitsbereich in um                   \\\n" +
                    "P2024=0;       / Wegfahrweg in um vor RF, 0=Deaktiviert           \\\n" +
                    "P2025=0;       / NotAus bei Fahrt in Endschalter, 0=Ja, 1=Nein    \\\n" +
                    "\n" +
                    "P2030=75;      / GA-Rampenanfahrgeschwindigkeit in Hz             \\\n" +
                    "P2031=75;      / GA-Rampensteigung                                \\\n" +
                    "P2032=10000;   / GA-Maximale Rampenfrequenz in Hz                 \\\n" +
                    "\n" +
                    "P2006=4000;    / Motorstrom in mA (nur bei CNC550/800), 3000=3A   \\\n" +
                    "\n" +
                    "\n" +
                    "/ Z-ACHSENPARAMETER \\\n" +
                    "P3001=Z;       / Achsbezeichnung, 0=Achse nicht vorhanden         \\\n" +
                    "P3002=1;       / Interne Aufloesung von 1-8fach, 1=Default        \\\n" +
                    "P3003=0;       / Achskopplung, 0=Aus                              \\\n" +
                    "P3004=50000;   / Schrittaufloesung in Vollschritten pro Meter     \\\n" +
                    "P3005=0;       / Motorpolung, 0=Default, 1=Umgedreht              \\\n" +
                    "P3007=1;       / Verfahren im Halt, 0=Nein, 1=Achse auf Null      \\\n" +
                    "P3011=15000;   / Referenzfahrtgeschwindigkeit in um/s             \\\n" +
                    "P3013=50000;   / Maximale Achsengeschwindigkeit (Eilgang) in um/s \\\n" +
                    "P3020=1;       / Endschalter 1, 1=Oeffner, 2=Schliesser           \\\n" +
                    "P3021=0;       / Endschalter 2, 0=Keiner, 1=Siehe Endschalter 1   \\\n" +
                    "P3022=1000;    / Abstand des Referenzpunktes vom Endschalter 1    \\\n" +
                    "P3023=160000;  / Maximaler Arbeitsbereich in um                   \\\n" +
                    "P3024=0;       / Wegfahrweg in um vor RF, 0=Deaktiviert           \\\n" +
                    "P3025=0;       / NotAus bei Fahrt in Endschalter, 0=Ja, 1=Nein    \\\n" +
                    "\n" +
                    "P3030=75;      / GA-Rampenanfahrgeschwindigkeit in Hz             \\\n" +
                    "P3031=75;      / GA-Rampensteigung                                \\\n" +
                    "P3032=10000;   / GA-Maximale Rampenfrequenz in Hz                 \\\n" +
                    "\n" +
                    "P3006=4000;    / Motorstrom in mA (nur bei CNC550/800), 3000=3A   \\\n" +
                    "\n" +
                    "\n" +
                    "/ 4.-ACHSPARAMETER \\\n" +
                    "P4001=0;       / Achsbezeichnung, 0=Achse nicht vorhanden         \\\n" +
                    "\n" +
                    "\n" +
                    "/ 5.-ACHSPARAMETER \\\n" +
                    "P5001=0;       / Achsbezeichnung, 0=Achse nicht vorhanden         \\\n" +
                    "\n" +
                    "\n" +
                    "/ 6.-ACHSPARAMETER \\\n" +
                    "P6001=0;       / Achsbezeichnung, 0=Achse nicht vorhanden         \\\n" +
                    "\n" +
                    "\n" +
                    "/ IOS \\\n" +
                    "P7000=0;       / Halt 1=Oeffner, 0=Schliesser                     \\\n" +
                    "P7001=1;       / Eingang 1                                        \\\n" +
                    "\n" +
                    "P7100=3;       / Eingang fuer Antasten, 2=Eingang 2               \\\n" +
                    "P7101=1;       / Ueberfahren bei Antasten, 0=Ja, 1=Nein           \\\n" +
                    "P7102=10000;   / Geschwindigkeit bei Antasten in um/s             \\\n" +
                    "P7103=11870;   / Dicke des Messplaettchens in um                  \\\n" +
                    "\n" +
                    "/ Handsteuerbox \\\n" +
                    "P8001=1;       / Notauseingang aktiv, 1=Ja                        \\\n" +
                    "P8010=20000;   / Geschwindigkeit im Normalgang in um/s            \\\n" +
                    "P8011=100;     / Wegsterecke im Normalgang in um                  \\\n" +
                    "P8020=100000;  / Geschwindigkeit im Eilgang in um/s               \\\n" +
                    "P8021=1000;    / Wegsterecke im Eilgang in um                     \\\n" +
                    "\n" +
                    "\n" +
                    "/ SPINDEL \\\n" +
                    "P9000=6;       / Spindeltyp,0=Keine,1=BMR,2=Analog,4=SPC,Int.SFU  \\\n" +
                    "P9001=1;       / Spindel- Notauseingang  1= aktiv                 \\\n" +
                    "P9101=500;     / Zeitintervall Kommunikation Steuerung mit Spindel\\\n" +
                    "P9202=30000;   / Maximale Spindeldrehzahl in U/min                \\\n" +
                    "P9203=6000;    / Minimale Spindeldrehzahlin U/min                 \\\n" +
                    "P9204=10700;   / Ausgangsspannung f. Maximaldrehzahl, 5000=5V     \\\n" +
                    "P9205=2200;    / Ausgangsspannung f. Minimaldrehzahl, 0=0V        \\\n" +
                    "\n" +
                    "/ WERKZEUGPARAMETER \\\n" +
                    "\n" +
                    "/ WERKZEUGWECHSEL TOOL AUFNEHMEN \\\n" +
                    "\n" +
                    "/ WERKZEUGWECHSEL TOOL ABLEGEN \\\n" +
                    "\n" +
                    "/ INITIALISIERUNG \\\n" +
                    "!N;RF;";

            serialAPI.sendStringToComm(command);

            // Initiaisierung -> Globale Parameter
            serialAPI.sendStringToComm("P0010=38400;");
            serialAPI.sendStringToComm("P0011=1;");
            serialAPI.sendStringToComm("P0014=1008;");
            serialAPI.sendStringToComm("P0020=75;");
            serialAPI.sendStringToComm("P0022=75;");
            serialAPI.sendStringToComm("P0023=10000;");
            serialAPI.sendStringToComm("P0024=100;");
            serialAPI.sendStringToComm("P0025=20000;");
            serialAPI.sendStringToComm("P0026=1;");

            // Initiaisierung -> X-Achse Parameter
            serialAPI.sendStringToComm("P1001=X;");
            serialAPI.sendStringToComm("P1002=1;");
            serialAPI.sendStringToComm("P1003=0;");
            serialAPI.sendStringToComm("P1004=50000;");
            serialAPI.sendStringToComm("P1005=0;");
            serialAPI.sendStringToComm("P1007=0;");
            serialAPI.sendStringToComm("P1011=25000;");
            serialAPI.sendStringToComm("P1013=100000;");
            serialAPI.sendStringToComm("P1020=1;");
            serialAPI.sendStringToComm("P1021=0;");
            serialAPI.sendStringToComm("P1022=1000;");
            serialAPI.sendStringToComm("P1023=540000;");
            serialAPI.sendStringToComm("P1024=0;");
            serialAPI.sendStringToComm("P1025=0;");
            serialAPI.sendStringToComm("P1030=75;");
            serialAPI.sendStringToComm("P1031=75;");
            serialAPI.sendStringToComm("P1032=10000;");
            serialAPI.sendStringToComm("P1006=4000;");

            // Initiaisierung -> Y-Achse Parameter
            serialAPI.sendStringToComm("P2001=Y;");
            serialAPI.sendStringToComm("P2002=1;");
            serialAPI.sendStringToComm("P2003=0;");
            serialAPI.sendStringToComm("P2004=50000;");
            serialAPI.sendStringToComm("P2005=0;");
            serialAPI.sendStringToComm("P2007=0;");
            serialAPI.sendStringToComm("P2011=25000;");
            serialAPI.sendStringToComm("P2013=100000;");
            serialAPI.sendStringToComm("P2020=1;");
            serialAPI.sendStringToComm("P2021=0;");
            serialAPI.sendStringToComm("P2022=1000;");
            serialAPI.sendStringToComm("P2023=500000;");
            serialAPI.sendStringToComm("P2024=0;");
            serialAPI.sendStringToComm("P2025=0;");
            serialAPI.sendStringToComm("P2030=75;");
            serialAPI.sendStringToComm("P2031=75;");
            serialAPI.sendStringToComm("P2032=10000;");
            serialAPI.sendStringToComm("P2006=4000;");

            // Initiaisierung -> Z-Achse Parameter
            serialAPI.sendStringToComm("P3001=Z;");
            serialAPI.sendStringToComm("P3002=1;");
            serialAPI.sendStringToComm("P3003=0;");
            serialAPI.sendStringToComm("P3004=50000;");
            serialAPI.sendStringToComm("P3005=0;");
            serialAPI.sendStringToComm("P3007=1;");
            serialAPI.sendStringToComm("P3011=15000;");
            serialAPI.sendStringToComm("P3013=50000;");
            serialAPI.sendStringToComm("P3020=1;");
            serialAPI.sendStringToComm("P3021=0;");
            serialAPI.sendStringToComm("P3022=1000;");
            serialAPI.sendStringToComm("P3023=160000;");
            serialAPI.sendStringToComm("P3024=0;");
            serialAPI.sendStringToComm("P3025=0;");
            serialAPI.sendStringToComm("P3030=75;");
            serialAPI.sendStringToComm("P3031=75;");
            serialAPI.sendStringToComm("P3032=10000;");
            serialAPI.sendStringToComm("P3006=4000;");

            // Initiaisierung -> 4.-ACHSPARAMETER
            serialAPI.sendStringToComm("P4001=0;");

            // Initiaisierung -> 5.-ACHSPARAMETER
            serialAPI.sendStringToComm("P5001=0;");

            // Initiaisierung -> 6.-ACHSPARAMETER
            serialAPI.sendStringToComm("P6001=0;");

            // Initiaisierung -> IOS
            serialAPI.sendStringToComm("P7000=0;");
            serialAPI.sendStringToComm("P7001=1;");
            serialAPI.sendStringToComm("P7100=3;");
            serialAPI.sendStringToComm("P7101=1;");
            serialAPI.sendStringToComm("P7102=10000;");
            serialAPI.sendStringToComm("P7103=11870;");

            // Initiaisierung -> Handsteuerbox
            serialAPI.sendStringToComm("P8001=1;");
            serialAPI.sendStringToComm("P8010=20000;");
            serialAPI.sendStringToComm("P8011=100;");
            serialAPI.sendStringToComm("P8020=100000;");
            serialAPI.sendStringToComm("P8021=1000;");

            // Initiaisierung -> Spindel
            serialAPI.sendStringToComm("P9000=6;");
            serialAPI.sendStringToComm("P9001=1;");
            serialAPI.sendStringToComm("P9101=500;");
            serialAPI.sendStringToComm("P9202=30000;");
            serialAPI.sendStringToComm("P9203=6000;");
            serialAPI.sendStringToComm("P9204=10700;");
            serialAPI.sendStringToComm("P9205=2200;");

            serialAPI.sendStringToComm("!N;RF;");

            // Testprogramm
            serialAPI.sendStringToComm("T1;");
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

            // empfange daten von der CNC --> sollte ein Event sein



            //serialAPI.closePort();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
