package ui.control;

import cnc.cnc_instructions.Instructions;
import ui.res.UI_CONST;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel {

    private JLabel header;
    private JButton xRechts, xLinks, yHoch, yRunter, zHoch, zRunter, toolSwitch, back;
    private GridBagConstraints constraints;
    private Instructions instructions;

    Icon iconXlinks = new ImageIcon("src/ui/x_links.png");
    Icon iconXrechts = new ImageIcon("src/ui/x_rechts.png");
    Icon iconYhoch = new ImageIcon("src/ui/y_hoch.png");
    Icon iconYrunter = new ImageIcon("src/ui/y_runter.png");
    Icon iconZhoch = new ImageIcon("src/ui/z_hoch.png");
    Icon iconZrunter = new ImageIcon("src/ui/z_runter.png");


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
        // X Rechts
        xRechts = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    instructions.moveAxis("X", 250);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        ImageIcon xRechtsIcon = new ImageIcon(getClass().getResource("x_rechts.png"));
        Image xRechtsBild = xRechtsIcon.getImage();
        Image skaliertxRechts = xRechtsBild.getScaledInstance(130, 130,  java.awt.Image.SCALE_SMOOTH);
        xRechtsIcon = new ImageIcon(skaliertxRechts);
        xRechts.setIcon(xRechtsIcon);
        xRechts.setBorderPainted(false);
        xRechts.setFocusPainted(false);
        xRechts.setContentAreaFilled(false);
        constraints.gridx = 4;
        constraints.gridy = 2;
        add(xRechts,constraints);

        //X Links
        xLinks = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    instructions.moveAxis("X", -250);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        ImageIcon xLinksIcon = new ImageIcon(getClass().getResource("x_links.png"));
        Image xLinksBild = xLinksIcon.getImage();
        Image skaliertxLinks = xLinksBild.getScaledInstance(130, 130,  java.awt.Image.SCALE_SMOOTH);
        xLinksIcon = new ImageIcon(skaliertxLinks);
        xLinks.setIcon(xLinksIcon);
        xLinks.setBorderPainted(false);
        xLinks.setFocusPainted(false);
        xLinks.setContentAreaFilled(false);
        constraints.gridx = 2;
        constraints.gridy = 2;
        add(xLinks,constraints);

        //Y Hoch
        yHoch = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    instructions.moveAxis("Y", 250);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        ImageIcon yHochIcon = new ImageIcon(getClass().getResource("y_hoch.png"));
        Image yHochBild = yHochIcon.getImage();
        Image skaliertyHoch = yHochBild.getScaledInstance(130, 130,  java.awt.Image.SCALE_SMOOTH);
        yHochIcon = new ImageIcon(skaliertyHoch);
        yHoch.setIcon(yHochIcon);
        yHoch.setBorderPainted(false);
        yHoch.setFocusPainted(false);
        yHoch.setContentAreaFilled(false);
        constraints.gridx = 3;
        constraints.gridy = 1;
        add(yHoch,constraints);

        //Y Runter
        yRunter = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    instructions.moveAxis("Y", -250);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        ImageIcon yRunterIcon = new ImageIcon(getClass().getResource("y_runter.png"));
        Image yRunterBild = yRunterIcon.getImage();
        Image skaliertyRunter = yRunterBild.getScaledInstance(130, 130,  java.awt.Image.SCALE_SMOOTH);
        yRunterIcon = new ImageIcon(skaliertyRunter);
        yRunter.setIcon(yRunterIcon);
        yRunter.setBorderPainted(false);
        yRunter.setFocusPainted(false);
        yRunter.setContentAreaFilled(false);
        constraints.gridx = 3;
        constraints.gridy = 3;
        add(yRunter,constraints);

        //Z Hoch
        zHoch = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    instructions.moveAxis("Z", 250);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        ImageIcon zHochIcon = new ImageIcon(getClass().getResource("z_hoch.png"));
        Image zHochBild = zHochIcon.getImage();
        Image skaliertzHoch = zHochBild.getScaledInstance(130, 130,  java.awt.Image.SCALE_SMOOTH);
        zHochIcon = new ImageIcon(skaliertzHoch);
        zHoch.setIcon(zHochIcon);
        zHoch.setBorderPainted(false);
        zHoch.setFocusPainted(false);
        zHoch.setContentAreaFilled(false);
        constraints.gridx = 6;
        constraints.gridy = 1;
        add(zHoch,constraints);

        //Z Runter
        zRunter = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    instructions.moveAxis("Z", -250);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        ImageIcon zRunterIcon = new ImageIcon(getClass().getResource("z_runter.png"));
        Image zRunterBild = zRunterIcon.getImage();
        Image skaliertzRunter = zRunterBild.getScaledInstance(130, 130,  java.awt.Image.SCALE_SMOOTH);
        zRunterIcon = new ImageIcon(skaliertzRunter);
        zRunter.setIcon(zRunterIcon);
        zRunter.setBorderPainted(false);
        zRunter.setFocusPainted(false);
        zRunter.setContentAreaFilled(false);
        constraints.gridx = 6;
        constraints.gridy = 3;
        add(zRunter,constraints);


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
