package ui.image;

import ui.res.UI_CONST;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ExecuteImagePanel extends JPanel {
    private GridBagConstraints gbc;
    JLabel bild = new JLabel();
    ImportImagePanel test = new ImportImagePanel();

    public ExecuteImagePanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        initImport();
    }
    private void initImport() {
        JPanel executeImage = new JPanel();
        executeImage.setLayout(new BoxLayout(executeImage, BoxLayout.LINE_AXIS));
        executeImage.add(Box.createHorizontalBox());
        executeImage.setBackground(Color.GRAY);
        executeImage.setBorder(new LineBorder(Color.GRAY, 5));

        JButton back = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UI_CONST.cardLayout.show(UI_CONST.showPanel, UI_CONST.IMPORT_IMAGE_PANEL);
            }
        });
        back.setText("Zurück");
        back.setMaximumSize(new Dimension(200, 200));
        executeImage.add(back);

        JButton fraesen = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UI_CONST.cardLayout.show(UI_CONST.showPanel, UI_CONST.IMPORT_IMAGE_PANEL);
            }
        });
        fraesen.setText("An die CNC senden");
        fraesen.setMaximumSize(new Dimension(200, 200));
        executeImage.add(fraesen);

        bild.setIcon(test.bildLaden.getIcon());
        bild.repaint();
        add(executeImage, gbc);
    }
}
