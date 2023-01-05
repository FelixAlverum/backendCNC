package ui.image;

import ui.res.UI_CONST;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * https://wiki.byte-welt.net/wiki/Malen_in_Swing_Teil_2:_ein_einfaches_Malprogramm
 */
public class DrawPanel extends JPanel {

    private JPanel mainPanel, drawPanel, brushPanel, footerPanel;

    public DrawPanel(){
        initDrawPanel();
        initBrushPanel();
        //initFooterPanel();

        // mainPanel = new JPanel();

        //GridBagLayout gridBagLayout = new GridBagLayout();
        //mainPanel.setLayout(gridBagLayout);
        //GridBagConstraints c = new GridBagConstraints();

//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 0.5;
//        c.gridx = 0;
//        c.gridy = 0;
//        c.gridwidth = 1;
//        c.gridheight = 1;
//        gridBagLayout.setConstraints(drawPanel, c);
//        mainPanel.add(drawPanel);

//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.weightx = 0.5;
//        c.gridx = 1;
//        c.gridy = 0;
//        c.gridwidth = 1;
//        c.gridheight = 1;
//        gridBagLayout.setConstraints(brushPanel, c);
//        mainPanel.add(brushPanel);

//        c.fill = GridBagConstraints.HORIZONTAL;
//        c.gridx = 0;
//        c.gridy = 1;
//        c.gridwidth = 2;
//        c.gridheight = 1;
//        gridBagLayout.setConstraints(footerPanel, c);
//        mainPanel.add(footerPanel);

        mainPanel = new JPanel(new GridLayout(5, 1));
        mainPanel.add(drawPanel);
        mainPanel.add(brushPanel);
        //mainPanel.add(footerPanel);
    }

    private void initDrawPanel(){
        drawPanel = new JPanel();
        setBackground(Color.CYAN);
    }

    private void initBrushPanel(){
        brushPanel = new JPanel();
        setBackground(Color.DARK_GRAY);
    }

    private void initFooterPanel(){
        footerPanel = new JPanel();
        setBackground(Color.GREEN);
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

            @Override
            public void actionPerformed(final ActionEvent e) {
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
