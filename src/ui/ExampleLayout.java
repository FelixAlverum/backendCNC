package ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

/**
 * LÖSCHEN
 */

public class ExampleLayout {

    public static void main(String[] args) {
        new ExampleLayout();
    }

    APanel ap;
    BPanel bp;

    private void initPanelA() {
        ap = new APanel();
    }

    private void initPanelB() {
        bp = new BPanel();
    }

    public ExampleLayout() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
                         UnsupportedLookAndFeelException ex) {
                    ex.printStackTrace();
                }

                initPanelA();
                initPanelB();

                JPanel mainPane = new JPanel(new GridLayout(5, 1));
                mainPane.add(ap);
                mainPane.add(bp);

                JFrame frame = new JFrame("Testing");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(mainPane);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

    }

    public class APanel extends JPanel {

        public APanel() {
            setLayout(new FlowLayout(4));
            for (int index = 0; index < 4; index++) {
                add(new JLabel(Integer.toString(index)));
            }
            setBorder(new LineBorder(Color.RED));
        }

    }

    public class BPanel extends JPanel {

        public BPanel() {
            setLayout(new GridLayout(7, 2));
            for (int index = 0; index < 14; index++) {
                add(new JLabel(Integer.toString(index)));
            }
            setBorder(new LineBorder(Color.BLUE));
        }

    }

}