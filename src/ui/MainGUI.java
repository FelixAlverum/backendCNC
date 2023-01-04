package ui;

import ui.control.ControlPanel;
import ui.guide.GuidePanel;
import ui.image.DrawPanel;
import ui.image.DrawSetDataPanel;
import ui.image.ExecuteImagePanel;
import ui.image.ImportImagePanel;
import ui.main.MainPanel;
import ui.res.UI_CONST;

import javax.swing.*;
import java.awt.*;

/**
 * https://stackoverflow.com/questions/46870004/how-to-switch-jpanels-in-a-jframe-from-within-the-panel
 *
 */

public class MainGUI extends JFrame {

    private String currentVisiblePanel = null;



    public MainGUI(){
        setTitle("CNC - Group 3");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize((int)(Toolkit. getDefaultToolkit(). getScreenSize().getWidth()*0.75), (int)(Toolkit. getDefaultToolkit(). getScreenSize().getHeight()*0.75));
        setLocationRelativeTo(null);

        UI_CONST.cardLayout = new CardLayout();
        UI_CONST.showPanel = new JPanel();
        UI_CONST.showPanel.setLayout(UI_CONST.cardLayout);
        UI_CONST.showPanel.setSize((int)(Toolkit. getDefaultToolkit(). getScreenSize().getWidth()*0.75), (int)(Toolkit. getDefaultToolkit(). getScreenSize().getHeight()*0.75));

        // create Panels
        UI_CONST.mainPanel = new MainPanel();
        UI_CONST.controlPanel = new ControlPanel();
        UI_CONST.drawPanel = new DrawPanel();
        UI_CONST.drawSetDataPanel = new DrawSetDataPanel();
        UI_CONST.importImagePanel = new ImportImagePanel();
        UI_CONST.executeImagePanel = new ExecuteImagePanel();
        UI_CONST.guidePanel = new GuidePanel();

        UI_CONST.showPanel.add(UI_CONST.MAIN_PANEL, UI_CONST.mainPanel);
        UI_CONST.showPanel.add(UI_CONST.CONTROL_PANEL, UI_CONST.controlPanel);
        UI_CONST.showPanel.add(UI_CONST.DRAW_PANEL, UI_CONST.drawPanel);
        UI_CONST.showPanel.add(UI_CONST.DRAW_SET_DATA_PANEL, UI_CONST.drawSetDataPanel);
        UI_CONST.showPanel.add(UI_CONST.IMPORT_IMAGE_PANEL, UI_CONST.importImagePanel);
        UI_CONST.showPanel.add(UI_CONST.EXECUTE_IMAGE_PANEL, UI_CONST.executeImagePanel);
        UI_CONST.showPanel.add(UI_CONST.GUIDE_PANEL, UI_CONST.guidePanel);

        UI_CONST.cardLayout.show(UI_CONST.showPanel, UI_CONST.MAIN_PANEL);

        add(UI_CONST.showPanel);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}
