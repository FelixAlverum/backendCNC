package ui;

import cnc.CncState;
import cnc.cnc_instructions.Instructions;
import connection.cnc.SerialAPI;
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
import java.io.IOException;
import java.net.URISyntaxException;

/**
 * https://stackoverflow.com/questions/46870004/how-to-switch-jpanels-in-a-jframe-from-within-the-panel
 *
 */

public class MainGUI extends JFrame {
    public MainGUI() throws IOException, URISyntaxException {

        // CNC Connection
        try {
            CncState.CNC_CONNECTION = new SerialAPI();
            CncState.CNC_CONNECTION.initPort("COM4");
            CncState.CNC_CONNECTION.openPort();
            CncState.CNC_CONNECTION.initCNC();

            Instructions instructions = new Instructions();
            instructions.goCoordinate(0, 0, 0);
        }catch (Exception e){
            e.printStackTrace();
        }
        // Schlechte Look and Feel
        try {
            //wahrscheinlich "javax.swing.plaf.nimbus.NimbusLookAndFeel"
            // vielleicht auch "com.sun.java.swing.plaf.gtk.GTKLookAndFeel"
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        UI_CONST.cardLayout = new CardLayout();
        UI_CONST.showPanel = new JPanel();
        UI_CONST.showPanel.setLayout(UI_CONST.cardLayout);

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

        setTitle("CNC - Gruppe 3");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize((int)(Toolkit. getDefaultToolkit(). getScreenSize().getWidth()*0.75), (int)(Toolkit. getDefaultToolkit(). getScreenSize().getHeight()*0.75));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new MainGUI();
            } catch (IOException | URISyntaxException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
