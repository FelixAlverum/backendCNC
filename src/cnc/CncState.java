package cnc;

public class CncState {

     // Gerneral Info
    /**
     * Are you connected to a CNC router
     */
    public static boolean connected;
    /**
     * Is the CNC executing a program?
     * Vielleicht ändern in String status --> mögliche Zustände sind: stopp, error, werkzeug_wechsel, pausiert, ausfuehrend, abgeschlossen
     */
    public static boolean running;

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
}
