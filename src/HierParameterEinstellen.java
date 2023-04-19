public class HierParameterEinstellen {

    // ------------------- Startpunkt des Werkstücks -------------------
    // Angabe in Micrometer --> 1 (cm) * 10 (mm) * 1000 (um)
    /**
     * WICHTIG IMMER APASSEN
     * Startpunkt des Werkstücks in Z Richtung (Höhe des Werkstücks) - Immer Abhängig von Höhe des Werkstücks - Ausmessen mit MainGUI
     * Default ist ein sicherer Punkt
     */
    public static int z = 10000;

    /**
     * Startpunkt des Werkstücks in X Richtung - default ist passend für Einspannvorrichtung auf CNC
     */
    public static int x = 40000;
    /**
     * Startpunkt des Werkstücks in Y Richtung - default ist passend für Einspannvorrichtung auf CNC
     */
    public static int y = 200000;


    // ------------------- Dimensionen des Werkstücks -------------------
    // Angabe in Millimeter

    /**
     * 1) Angabe in Millimeter
     * 2) Sicherheitsabstand muss selber berechnet werden (z.B. 10 cm Länge --> 95mm wg. Sicherheitsabstand)
     */
    public static int werkstueckbreite = 10;
    public static int werkstuecklaenge = 10;

    /**
     * Dokumentation
     * 1) Die Datei auf dem Pfad src/Test/DrawPanelPNG.png mit gewünschtem Bild in png Format und gleicher Benennung austauschen
     * 2) Obere Variablen korrekt befüllen
     * 3) src/Main.java ausführen
     * 4) Juchhuuu Frässpass :-)
     */

}
