package ui.guide;

import ui.res.UI_CONST;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class GuidePanel extends JPanel {
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
        guidePanel.add(back);
    }
}
