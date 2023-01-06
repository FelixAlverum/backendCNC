package ui.image;

import ui.res.UI_CONST;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * https://wiki.byte-welt.net/wiki/Malen_in_Swing_Teil_2:_ein_einfaches_Malprogramm
 * https://stackoverflow.com/questions/5621338/how-to-add-jtable-in-jpanel-with-null-layout/5630271#5630271
 */
public class DrawPanel extends JPanel {

    private JPanel drawPanel, brushPanel, footerPanel;
    GridBagConstraints gbc;

    public DrawPanel() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        initDrawPanel();
        initBrushPanel();
        initFooterPanel();
    }

    private void initDrawPanel() {
        drawPanel = new JPanel(new BorderLayout());
        drawPanel.setBorder(new TitledBorder("Drawing Panel"));
        drawPanel.setBackground(Color.WHITE);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 7;
        gbc.gridwidth = 8;
        gbc.weightx =0.8;
        gbc.weighty =0.9;
        add(drawPanel, gbc);
    }

    private void initBrushPanel() {
        brushPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        brushPanel.setBorder(new TitledBorder("Brush Panel"));
        brushPanel.setBackground(Color.LIGHT_GRAY);

        gbc.gridx = 8;
        gbc.gridy = 0;
        gbc.gridheight = 7;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx =0.2;
        gbc.weighty =0.9;
        add(brushPanel, gbc);

    }

    private void initFooterPanel() {
        footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBackground(Color.GRAY);
        footerPanel.setBorder(new LineBorder(Color.GRAY, 5));

        JButton back = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UI_CONST.cardLayout.show(UI_CONST.showPanel, UI_CONST.MAIN_PANEL);
            }
        });
        back.setText("Back");
        footerPanel.add(back, BorderLayout.LINE_START);

        JButton next = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // #TODO auf folgepage navigieren
                UI_CONST.cardLayout.show(UI_CONST.showPanel, UI_CONST.MAIN_PANEL);
            }
        });
        next.setText("Next");
        footerPanel.add(next, BorderLayout.LINE_END);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx =1.0;
        gbc.weighty =0.1;
        add(footerPanel, gbc);
    }

    /**
     final private DrawingPanel drawingPanel;
     final private JPanel buttonPanel;
     final private JButton clearButton, upSize, downSize;
     private final ActionListener actionHandler;


     public DrawPanel(){
     JFrame frame = new JFrame("DrawOnImage");
     drawingPanel = new DrawingPanel();
     actionHandler = new ActionListener() {

    @Override public void actionPerformed(final ActionEvent e) {
    String s = e.getActionCommand();
    if (s.equals("Paint")) {
    JButton button = (JButton) e.getSource();
    drawingPanel.setPaintColor(button.getBackground());
    } else if (s.equals("Clear")) {
    drawingPanel.clearPaint();
    } else if (s.equals("+")) {
    drawingPanel.increaseBrushSize();
    } else {
    drawingPanel.decreaseBrushSize();
    }
    }
    };
     buttonPanel = new JPanel();
     buttonPanel.setLayout(new GridLayout(2, 0, 2, 2));
     addButton(Color.BLACK);
     addButton(Color.BLUE);
     addButton(Color.GREEN);
     upSize = addButton(null);
     upSize.setText("+");
     addButton(Color.RED);
     addButton(Color.ORANGE);
     clearButton = addButton(null);
     clearButton.setText("Clear");
     downSize = addButton(null);
     downSize.setText("-");
     frame.getContentPane().add(new JScrollPane(drawingPanel));
     frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
     frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
     frame.pack();
     frame.setLocationRelativeTo(null);
     frame.setVisible(true);
     }

     private JButton addButton(final Color color) {
     JButton button = new JButton();
     button.setBackground(new Color(230, 240, 250));
     button.setBorder(BorderFactory.createEtchedBorder());
     if (color != null) {
     button.setForeground(Color.WHITE);
     button.setBackground(color);
     }
     button.setText("Paint");
     buttonPanel.add(button);
     button.addActionListener(actionHandler);
     return (button);
     }
     */
}
