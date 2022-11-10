package cnc;

import java.util.ArrayList;

public class CncState {

    // Gerneral Info
    public enum cnc_state {DISCONNECTED, INIT, CONNECTED, PAUSE, EXECUTING, FINISHED, CHANGE_TOOL, ERROR}

    public static cnc_state cnc_state;

    // Absolute Position
    public static int absolute_X;
    public static int absolute_Y;
    public static int absolute_Z;

    // Relative Position
    public static int relative_X;
    public static int relative_Y;
    public static int relative_Z;

    // Tool
    public static String toolEquipped;
    public static boolean toolOn;

    // workpart
    public static int workpart_width;
    public static int workpart_length;
    public static int workpart_depth;

    // CNC Response Log
    public static ArrayList<String> cncLOG = new ArrayList<String>();
    public static boolean incompleteEntry = false;
    public static int indexLog = 0;
}
