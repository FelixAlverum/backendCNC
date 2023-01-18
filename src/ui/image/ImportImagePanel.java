package ui.image;

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
                        System.out.println("Test");
                        try {
                        BufferedImage bildAuswahl = ImageIO.read(chooser.getSelectedFile());
                            //bildLaden.paintComponents(bildAuswahl.getGraphics());


                            Image dimg = bildAuswahl.getScaledInstance(getWidth(), getHeight(),Image.SCALE_SMOOTH);
                            bildLaden.setIcon(new ImageIcon(dimg));
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
