package cnc.cnc_instructions;

import cnc.CncState;

public class Instructions {

    /**
     * fahre in Achsenrichtung Richtung
     * TODO Prüfe ob außerhalb der CNC Grenzen gefahren wird
     */
    public void moveAxis(String axis, int mikometer) throws Exception {
        String command = "";
        switch (axis.toUpperCase()) {
            case "X":
                CncState.absolute_X = CncState.absolute_X + mikometer;
                command = "GA" + CncState.absolute_X + ",,;";
                break;
            case "Y":
                CncState.absolute_Y = CncState.absolute_Y + mikometer;
                command = "GA," + CncState.absolute_Y + ",;";
                break;
            case "Z":
                CncState.absolute_Z = CncState.absolute_Z + mikometer;
                command = "GA,," + CncState.absolute_Z + ";";
                break;
            default:
                throw new Exception("Achse: " + axis + " nicht bekannt.");
        }

        CncState.CNC_CONNECTION.sendStringToComm(command);
    }

    /**
     * fahre an den Punkt mit den Koordinaten X;Y;Z
     * Koordinaten müssen in mikometer angegeben werden
     */
    public void goCoordinate(int x, int y, int z) throws Exception {
        String command = "";
        if (CncState.toolOn == true) {
            command = "PA" + x + "," + y + ",0;";
        } else {
            command = "GA" + x + "," + y + ",0;";
        }
        CncState.CNC_CONNECTION.sendStringToComm(command);

        if (CncState.toolOn == true) {
            command = "PA0,0," + z + ";";
        } else {
            command = "GA0,0," + z + ";";
        }
        CncState.CNC_CONNECTION.sendStringToComm(command);

        CncState.absolute_X = x;
        CncState.absolute_Y = y;
        CncState.absolute_Z = z;

    }

    /**
     * Werkzeug anschalten
     */
    public void startTool(int rotationsPerMinute) throws Exception {
        String command = "RVS" + rotationsPerMinute + ";";
        CncState.CNC_CONNECTION.sendStringToComm(command);
        CncState.toolOn = true;
        CncState.rotationalSpeed = rotationsPerMinute;
    }

    /**
     * Werkzeug vorschub setzen
     */
    public void setzeVorschub(int micrometerPerSecond) throws Exception {
        String command = "VS" + micrometerPerSecond + ";";
        CncState.CNC_CONNECTION.sendStringToComm(command);
        CncState.feed = micrometerPerSecond;
    }

    /**
     * Werkzeug ausschalten
     */
    public void stopTool() throws Exception {
        String command = "RVS0;";
        CncState.CNC_CONNECTION.sendStringToComm(command);
        CncState.toolOn = false;
    }
}