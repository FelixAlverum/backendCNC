package ui.control;

import cnc.cnc_instructions.Instructions;
import ui.res.UI_CONST;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ControlPanel extends JPanel implements ChangeListener {

    private JLabel header, headerdrehen, headerschritt;
    private JButton xRechts, xLinks, yHoch, yRunter, zHoch, zRunter, drehen, back;
    private int pruefer = 0;
    private int geschwindigkeit = 10000;
    private int schrittweite = 5000;
    private GridBagConstraints constraints;
    private Instructions instructions;

    Icon iconXlinks = new ImageIcon("src/ui/x_links.png");
    Icon iconXrechts = new ImageIcon("src/ui/x_rechts.png");
    Icon iconYhoch = new ImageIcon("src/ui/y_hoch.png");
    Icon iconYrunter = new ImageIcon("src/ui/y_runter.png");
    Icon iconZhoch = new ImageIcon("src/ui/z_hoch.png");
    Icon iconZrunter = new ImageIcon("src/ui/z_runter.png");
    Icon iconDrehen = new ImageIcon("src/ui/drehen.png");



    public ControlPanel(){
        setSize((int)(Toolkit. getDefaultToolkit(). getScreenSize().getWidth()*0.75), (int)(Toolkit. getDefaultToolkit(). getScreenSize().getHeight()*0.75));
        setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        instructions = new Instructions();
        initControlPanel();
    }

    private void initControlPanel(){
        header = new JLabel("CNC-Steuerung");
        constraints.gridx = 5;
        constraints.gridy = 0;
        add(header,constraints);

        // Controller

        //Schrittweite Slider
        headerschritt = new JLabel("Schrittweite anpassen (mm)");
        constraints.gridx = 0;
        constraints.gridy = 5;
        add(headerschritt,constraints);
        //Slider Drehgeschwindigkeit
        final int SCHRITT_MIN = 0;
        final int SCHRITT_MAX = 10;
        final int SCHRITT_INIT = 5;
        JSlider schrittWeiteSlider = new JSlider(JSlider.HORIZONTAL,
                SCHRITT_MIN, SCHRITT_MAX, SCHRITT_INIT);
        schrittWeiteSlider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider quelle = (JSlider)e.getSource();
                schrittweite = quelle.getValue() * 1000;
            }
        });
        schrittWeiteSlider.setMajorTickSpacing(1);
        schrittWeiteSlider.setMinorTickSpacing(1);
        schrittWeiteSlider.setPaintTicks(true);
        schrittWeiteSlider.setPaintLabels(true);
        constraints.gridx = 0;
        constraints.gridy = 4;
        add(schrittWeiteSlider, constraints);



        // X Rechts
        xRechts = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    instructions.moveAxis("X", schrittweite * (-1));
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
        constraints.gridx = 6;
        constraints.gridy = 2;
        add(xRechts,constraints);

        //X Links
        xLinks = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    instructions.moveAxis("X", schrittweite);
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
        constraints.gridx = 4;
        constraints.gridy = 2;
        add(xLinks,constraints);

        //Y Hoch
        yHoch = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    instructions.moveAxis("Y", schrittweite);
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
        constraints.gridx = 5;
        constraints.gridy = 1;
        add(yHoch,constraints);

        //Y Runter
        yRunter = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    instructions.moveAxis("Y", schrittweite * (-1));
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
        constraints.gridx = 5;
        constraints.gridy = 3;
        add(yRunter,constraints);

        //Z Hoch
        zHoch = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    instructions.moveAxis("Z", schrittweite * (-1));
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
        constraints.gridx = 8;
        constraints.gridy = 1;
        add(zHoch,constraints);

        //Z Runter
        zRunter = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    instructions.moveAxis("Z", schrittweite);
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
        constraints.gridx = 8;
        constraints.gridy = 3;
        add(zRunter,constraints);

        //Drehen des Werkzeugs
        drehen = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (pruefer == 0){
                        instructions.startTool(geschwindigkeit);
                        pruefer = 1;
                    } else if (pruefer == 1){
                        instructions.stopTool();
                        pruefer = 0;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        ImageIcon drehenIcon = new ImageIcon(getClass().getResource("drehen.png"));
        Image drehenBild = drehenIcon.getImage();
        Image skaliertDrehen = drehenBild.getScaledInstance(130, 130,  java.awt.Image.SCALE_SMOOTH);
        drehenIcon = new ImageIcon(skaliertDrehen);
        drehen.setIcon(drehenIcon);
        drehen.setBorderPainted(false);
        drehen.setFocusPainted(false);
        drehen.setContentAreaFilled(false);
        constraints.gridx = 5;
        constraints.gridy = 2;
        add(drehen,constraints);

        //Slider Drehgeschwindigkeit
        headerdrehen = new JLabel("<html>Drehgeschwindigkeit anpassen<br/>(*1000 Udr./min.)</html>", SwingConstants.CENTER);
        constraints.gridx = 0;
        constraints.gridy = 7;
        add(headerdrehen,constraints);

        final int DREH_MIN = 0;
        final int DREH_MAX = 40;
        final int DREH_INIT = 10;
        JSlider drehGeschwindigkeit = new JSlider(JSlider.HORIZONTAL,
                DREH_MIN, DREH_MAX, DREH_INIT);
        drehGeschwindigkeit.addChangeListener(this);
        drehGeschwindigkeit.setMajorTickSpacing(5);
        drehGeschwindigkeit.setMinorTickSpacing(1);
        drehGeschwindigkeit.setPaintTicks(true);
        drehGeschwindigkeit.setPaintLabels(true);
        constraints.gridx = 0;
        constraints.gridy = 6;
        add(drehGeschwindigkeit, constraints);

        //this.geschwindigkeit = drehGeschwindigkeit.getValue() * 1000;

        // back button
        back = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UI_CONST.cardLayout.show(UI_CONST.showPanel, UI_CONST.MAIN_PANEL);
            }
        });

        back.setText("Zurück");
        constraints.gridx = 7;
        constraints.gridy = 5;
        add(back,constraints);
    }


    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider quelle = (JSlider)e.getSource();
        this.geschwindigkeit = quelle.getValue() * 1000;
    }
}
