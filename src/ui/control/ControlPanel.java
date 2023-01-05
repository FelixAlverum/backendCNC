package ui.control;

import cnc.cnc_instructions.Instructions;
import ui.res.UI_CONST;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {

    private JLabel header;
    private JButton xPlus, xMinus, yPlus, yMinus, zPlus, zMinus, toolSwitch, back;
    private GridBagConstraints constraints;
    private Instructions instructions;


    public ControlPanel(){
        setSize((int)(Toolkit. getDefaultToolkit(). getScreenSize().getWidth()*0.75), (int)(Toolkit. getDefaultToolkit(). getScreenSize().getHeight()*0.75));
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        instructions = new Instructions();
        initControlPanel();
    }

    private void initControlPanel(){
        header = new JLabel("CNC Controller");
        constraints.gridx = 5;
        constraints.gridy = 0;
        add(header,constraints);

        // Controller
        xPlus = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    instructions.moveAxis("X", 250);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        xPlus.setText("X +");
        constraints.gridx = 2;
        constraints.gridy = 2;
        add(xPlus,constraints);

        xMinus = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    instructions.moveAxis("X", -250);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        xMinus.setText("X -");
        constraints.gridx = 3;
        constraints.gridy = 2;
        add(xMinus,constraints);

        // back button
        back = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UI_CONST.cardLayout.show(UI_CONST.showPanel, UI_CONST.MAIN_PANEL);
            }
        });
        back.setText("Back");
        constraints.gridx = 5;
        constraints.gridy = 5;
        add(back,constraints);
    }
}
