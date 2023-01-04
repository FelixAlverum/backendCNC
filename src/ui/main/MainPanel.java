package ui.main;

import ui.res.UI_CONST;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    private JButton btControl, btDrawImage, btImportImage, btGuide;

    public MainPanel(){
        setSize((int)(Toolkit. getDefaultToolkit(). getScreenSize().getWidth()*0.75), (int)(Toolkit. getDefaultToolkit(). getScreenSize().getHeight()*0.75));
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        initMainPanel();
    }

    private void initMainPanel(){

    }
}