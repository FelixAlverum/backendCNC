package ui.image;

import ui.res.UI_CONST;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImportImagePanel extends JPanel {
    public String fileID;
    public JTextField txtField;
    public JButton btnBrowse;
    private GridBagConstraints gbc;
    JLabel bildLaden = new JLabel();

    public ImportImagePanel() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        initImport();
    }


    private void initImport() {
        JPanel importImage = new JPanel();
        importImage.setLayout(new BoxLayout(importImage, BoxLayout.LINE_AXIS));
        importImage.add(Box.createHorizontalBox());
        importImage.setBackground(Color.GRAY);
        importImage.setBorder(new LineBorder(Color.GRAY, 5));

        JButton back = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UI_CONST.cardLayout.show(UI_CONST.showPanel, UI_CONST.MAIN_PANEL);
            }
        });
        back.setText("Zurück");
        back.setMaximumSize(new Dimension(200, 200));
        importImage.add(back);

        JButton weitergehts = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UI_CONST.cardLayout.show(UI_CONST.showPanel, UI_CONST.EXECUTE_IMAGE_PANEL);
            }
        });
        weitergehts.setText("Fortfahren");
        weitergehts.setMaximumSize(new Dimension(200, 200));
        importImage.add(weitergehts);

        btnBrowse = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnBrowse) {
                    JFileChooser chooser = new JFileChooser(new File(System.getProperty("user.home")));
                    chooser.setDialogTitle("Wählen Sie die Datei");
                    chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    chooser.setAcceptAllFileFilterUsed(true);

                    if (chooser.showOpenDialog(getParent()) == JFileChooser.APPROVE_OPTION)
                    {
                        try {
                        BufferedImage bildAuswahl = ImageIO.read(chooser.getSelectedFile());
                            //bildLaden.paintComponents(bildAuswahl.getGraphics());

                            Image dimg = bildAuswahl.getScaledInstance(bildAuswahl.getWidth(), bildAuswahl.getHeight(),Image.SCALE_SMOOTH);
                            if (bildAuswahl.getWidth() > (int)(Toolkit. getDefaultToolkit(). getScreenSize().getWidth()*0.75) || bildAuswahl.getHeight() > (int)(Toolkit. getDefaultToolkit(). getScreenSize().getHeight()*0.75)) {
                                JOptionPane.showMessageDialog(bildLaden, "Bild ist zu groß zum Einfügen, bitte ein kleineres Bild auswählen. Maximal " + (int)(Toolkit. getDefaultToolkit(). getScreenSize().getWidth()*0.75) + " x " + (int)(Toolkit. getDefaultToolkit(). getScreenSize().getHeight()*0.75) + " px groß.",
                                        "Bild-Import", JOptionPane.ERROR_MESSAGE);
                            } else {
                                bildLaden.repaint();
                                bildLaden.setIcon(new ImageIcon(dimg));
                            }
                        }
                        catch (IOException ie) {
                        JOptionPane.showMessageDialog(null, "Bild konnte nicht geladen werden");
                        }
                    }
                }

            }
        });
    btnBrowse.setText("Durchsuchen");
    btnBrowse.setVisible(true);
    importImage.add(btnBrowse);
    bildLaden.setVisible(true);
    importImage.add(bildLaden);
    add(importImage, gbc);
    }
}
