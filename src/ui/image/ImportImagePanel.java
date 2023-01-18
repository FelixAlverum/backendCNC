package ui.image;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class ImportImagePanel extends JPanel {
    public String fileID;
    public JTextField txtField;
    public JButton btnBrowse;
    private GridBagConstraints gbc;

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
                    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                    chooser.setAcceptAllFileFilterUsed(false);

                    if (chooser.showSaveDialog(getParent()) == JFileChooser.APPROVE_OPTION)
                    {
                        fileID = chooser.getSelectedFile().getPath();
                        txtField.setText(fileID);
                    }
                }

            }
        });
    btnBrowse.setText("Durchsuchen");
    btnBrowse.setVisible(true);
    importImage.add(btnBrowse);
    add(importImage, gbc);
    }


}
