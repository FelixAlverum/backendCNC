package ui.image;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.concurrent.TimeUnit;
import ui.image.DrawPanel;

public class DrawSetDataPanel<WindowEvent> extends JPanel{
    private GridBagConstraints gbc;
    private JLabel imagejuhu = new JLabel();
    private GridBagConstraints constraintsjuhu;
    private ImageIcon iconjuhu;

    public DrawSetDataPanel(){
        setSize((int)(Toolkit. getDefaultToolkit(). getScreenSize().getWidth()*0.75), (int)(Toolkit. getDefaultToolkit(). getScreenSize().getHeight()*0.75));
        setLayout(new GridBagLayout());
        constraintsjuhu = new GridBagConstraints();
        initDrawSetDataPanel();

    }

    public void initDrawSetDataPanel(){
        constraintsjuhu.gridx = 5;
        constraintsjuhu.gridy = 0;
        JPanel DrawSetDataPanel = new JPanel();
        DrawSetDataPanel.setLayout(new BoxLayout(DrawSetDataPanel, BoxLayout.LINE_AXIS));
        DrawSetDataPanel.setBackground(Color.GRAY);
        DrawSetDataPanel.setBorder(new LineBorder(Color.GRAY, 5));
        imagejuhu.setLayout(new BoxLayout(imagejuhu, BoxLayout.LINE_AXIS));
        imagejuhu.setBackground(Color.GRAY);
        imagejuhu.setBorder(new LineBorder(Color.GRAY, 5));
        // "./src/Test/DrawPanelSVG.svg"
        try {
            //DrawPanel test = new DrawPanel();
            //TimeUnit.SECONDS.sleep(1);
            System.out.println("INIT");
            //BufferedImage imgjuhu = (BufferedImage) DrawPanel.image;
            //Image juhumann = imgjuhu.getScaledInstance(imgjuhu.getWidth(), imgjuhu.getHeight(),Image.SCALE_SMOOTH);
            //ImageIcon iconjuhu = new ImageIcon(juhumann);
            //imagejuhu.setIcon(new ImageIcon(DrawPanel.uebergabeBild.getScaledInstance(test.getHeight(), test.getWidth(), Image.SCALE_SMOOTH)));
            //imagejuhu.repaint();
        }catch (Exception exception){
            exception.printStackTrace();
        }
        imagejuhu.setVisible(true);
        DrawSetDataPanel.add(imagejuhu);
        imagejuhu.setIcon(new ImageIcon("/src/ui/image/DrawPanelPNG.png"));
        add(DrawSetDataPanel, gbc);
    }

    public void add(ImageIcon testIcon) {
        System.out.println(imagejuhu);
        imagejuhu.setIcon(new ImageIcon("/src/ui/image/DrawPanelPNG.png"));    }
}
