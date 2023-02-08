package ui.guide;

import cnc.cnc_instructions.Instructions;
import ui.res.UI_CONST;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class GuidePanel extends JPanel {

    private GridBagConstraints constraints;
    private Instructions instructions;
    private JLabel header;

    public GuidePanel() throws IOException, URISyntaxException {
        setSize((int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 0.75), (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 0.75));
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        instructions = new Instructions();
        initGuide();
    }
        private void initGuide() {
            JPanel guidePanel = new JPanel();
            guidePanel.setLayout(new BoxLayout(guidePanel, BoxLayout.LINE_AXIS));
            guidePanel.add(Box.createHorizontalBox());
            guidePanel.setBackground(Color.GRAY);
            guidePanel.setBorder(new LineBorder(Color.GRAY, 5));
            JButton back = new JButton(new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    UI_CONST.cardLayout.show(UI_CONST.showPanel, UI_CONST.MAIN_PANEL);
                }
        });

        back.setText("Zurück");
        back.setVisible(true);
        back.setMaximumSize(new Dimension(200, 200));
        add(back, constraints);
        header = new JLabel("Hilfe / Info");
        constraints.gridx = 5;
        constraints.gridy = 0;
        add(header, constraints);
    }
}

