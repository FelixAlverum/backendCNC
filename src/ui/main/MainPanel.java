package ui.main;

import ui.res.UI_CONST;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;

public class MainPanel extends JPanel {

    private JLabel header, image;
    private JButton btControl, btDrawImage, btImportImage, btGuide;

    private GridBagConstraints constraints;

    public MainPanel(){
        setSize((int)(Toolkit. getDefaultToolkit(). getScreenSize().getWidth()*0.75), (int)(Toolkit. getDefaultToolkit(). getScreenSize().getHeight()*0.75));
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        initMainPanel();
    }

    private void initMainPanel(){
        header = new JLabel("MAIN MENU");
        constraints.gridx = 5;
        constraints.gridy = 0;
        add(header,constraints);

        try {
            BufferedImage img = ImageIO.read(new File("src/ui/res/logo-gds2.png"));
            ImageIcon icon = new ImageIcon(img);
            image = new JLabel(icon);

            constraints.gridx = 5;
            constraints.gridy = 1;
            add(image,constraints);
        }catch (Exception e){
            e.printStackTrace();
        }

        // Button -- Control CNC --
        btControl = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UI_CONST.cardLayout.show(UI_CONST.showPanel, UI_CONST.CONTROL_PANEL);
            }
        });
        btControl.setText("Control CNC");
        constraints.gridx = 2;
        constraints.gridy = 2;
        add(btControl,constraints);


        // Button -- Draw Image --
        btDrawImage = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UI_CONST.cardLayout.show(UI_CONST.showPanel, UI_CONST.DRAW_PANEL);
            }
        });
        btDrawImage.setText("Draw Image");
        constraints.gridx = 4;
        constraints.gridy = 2;
        add(btDrawImage,constraints);

        // Button -- Import Image --
        btImportImage = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UI_CONST.cardLayout.show(UI_CONST.showPanel, UI_CONST.IMPORT_IMAGE_PANEL);
            }
        });
        btImportImage.setText("Import Image");
        constraints.gridx = 2;
        constraints.gridy = 3;
        add(btImportImage,constraints);

        btGuide = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UI_CONST.cardLayout.show(UI_CONST.showPanel, UI_CONST.GUIDE_PANEL);
            }
        });
        btGuide.setText("Guide");
        constraints.gridx = 4;
        constraints.gridy = 3;
        add(btGuide,constraints);
    }
}