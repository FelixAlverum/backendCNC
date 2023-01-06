package ui.res;

import ui.control.ControlPanel;
import ui.guide.GuidePanel;
import ui.image.DrawPanel;
import ui.image.DrawSetDataPanel;
import ui.image.ExecuteImagePanel;
import ui.image.ImportImagePanel;
import ui.main.MainPanel;

import javax.swing.*;
import java.awt.*;

public class UI_CONST {
    public static final String MAIN_PANEL = "MAIN_PANEL";
    public static final String CONTROL_PANEL = "CONTROL_PANEL";
    public static final String DRAW_PANEL = "DRAW_PANEL";
    public static final String DRAW_SET_DATA_PANEL = "DRAW_SET_DATA_PANEL";
    public static final String EXECUTE_IMAGE_PANEL = "EXECUTE_IMAGE_PANEL";
    public static final String IMPORT_IMAGE_PANEL = "IMPORT_IMAGE_PANEL";
    public static final String GUIDE_PANEL = "GUIDE_PANEL";

    public static JPanel showPanel = null;
    public static MainPanel mainPanel;
    public static ControlPanel controlPanel;
    public static DrawPanel drawPanel;
    public static DrawSetDataPanel drawSetDataPanel;
    public static ImportImagePanel importImagePanel;
    public static ExecuteImagePanel executeImagePanel;
    public static GuidePanel guidePanel;

    public static CardLayout cardLayout;


    // Draw Panel Variables
    public static int brushSize = 5;
}
