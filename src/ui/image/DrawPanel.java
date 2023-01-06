package ui.image;

import ui.res.UI_CONST;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * https://wiki.byte-welt.net/wiki/Malen_in_Swing_Teil_2:_ein_einfaches_Malprogramm
 * https://stackoverflow.com/questions/5621338/how-to-add-jtable-in-jpanel-with-null-layout/5630271#5630271
 */
public class DrawPanel extends JPanel {

    private JPanel drawPanel, brushPanel, footerPanel;
    private GridBagConstraints gbc;

    private Point lastPoint;
    private Image image;
    private Graphics2D g2d;
    private ActionListener actionHandler;


    public DrawPanel() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        initDrawPanel();
        initBrushPanel();
        initFooterPanel();
    }

    private void initDrawPanel() {
        drawPanel = new JPanel(new BorderLayout()){
            @Override
            public void paintComponent(final Graphics g) {
                super.paintComponent(g);
                // initialises the image with the first paint
                // or checks the image size with the current panelsize
                if (image == null || image.getWidth(this) < getSize().width
                        || image.getHeight(this) < getSize().height) {
                    resetImage();
                }
                Graphics2D g2 = (Graphics2D) g;
                Rectangle r = g.getClipBounds();
                g2.drawImage(image, r.x, r.y, r.width + r.x, r.height + r.y,
                        r.x, r.y, r.width + r.x, r.height + r.y, this);
            }
        };
        drawPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2), "Drawing Panel"));
        drawPanel.setBackground(Color.WHITE);

        // Mouse Listener
        drawPanel.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("mousePressed()");
                lastPoint = e.getPoint();
                draw(lastPoint);
            }
        });
        drawPanel.addMouseMotionListener(new MouseInputAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                System.out.println("mouseDragged()");
                double xDelta = e.getX() - lastPoint.getX();
                double yDelta = e.getY() - lastPoint.getY();
                double delta = Math.max(Math.abs(xDelta), Math.abs(yDelta));
                double xIncrement = xDelta / delta;
                double yIncrement = yDelta / delta;
                double xStart = lastPoint.getX();
                double yStart = lastPoint.getY();
                for (int i = 0; i < delta; i++) {
                    Point interpolated = new Point((int) xStart, (int) yStart);
                    draw(interpolated);
                    xStart += xIncrement;
                    yStart += yIncrement;
                }
                draw(e.getPoint());
                lastPoint = e.getPoint();
            }
        });


        // Add to MainPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 7;
        gbc.gridwidth = 8;
        gbc.weightx =0.8;
        gbc.weighty =0.9;
        add(drawPanel, gbc);
    }

    private void draw(final Point start) {
        int brushSize = UI_CONST.brushSize;
        int x = start.x - (brushSize / 2) + 1;
        int y = start.y - (brushSize / 2) + 1;
        g2d.fillOval(x, y, brushSize, brushSize);
        drawPanel.repaint(x, y, brushSize, brushSize);
    }

    private void resetImage() {
        Image saveImage = image;
        Graphics2D saveG2d = g2d;
        image = createImage(getWidth(), getHeight());
        g2d = (Graphics2D) image.getGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, getWidth(), getHeight());
        g2d.setColor(Color.BLACK);
        if (saveG2d != null) {
            g2d.setColor(saveG2d.getColor());
            g2d.drawImage(saveImage, 0, 0, this);
            saveG2d.dispose();
        }
    }
    public void clearPaint() {
        g2d.setBackground(Color.WHITE);
        g2d.clearRect(0, 0, getWidth(), getHeight());
        repaint();
        g2d.setColor(Color.BLACK);
    }


    private void initBrushPanel() {
        brushPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        brushPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2), "Brush Panel"));
        brushPanel.setBackground(Color.LIGHT_GRAY);

        actionHandler = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                String s = e.getActionCommand();
                if (s.equals(" ")) {
                    JButton button = (JButton) e.getSource();
                    System.out.println("COLOR = " + button.getBackground());
                    g2d.setColor(button.getBackground());
                } else if (s.equals("Clear")) {
                    clearPaint();
                } else if (s.equals("+")) {
                    UI_CONST.brushSize +=2;
                } else if (s.equals("-")){
                    UI_CONST.brushSize -=2;
                }
            }
        };

        JButton depth1 = new JButton();
        depth1.setBackground(Color.LIGHT_GRAY);
        depth1.setText(" ");
        depth1.addActionListener(actionHandler);
        brushPanel.add(depth1);

        JButton depth2 = new JButton();
        depth2.setBackground(Color.GRAY);
        depth2.setText(" ");
        depth2.addActionListener(actionHandler);
        brushPanel.add(depth2);

        JButton depth3 = new JButton();
        depth3.setBackground(Color.BLACK);
        depth3.setText(" ");
        depth3.addActionListener(actionHandler);
        brushPanel.add(depth3);

        JButton clear = new JButton();
        clear.setText("Clear");
        brushPanel.add(clear);
        clear.addActionListener(actionHandler);

        JButton increaseBrushSize = new JButton();
        increaseBrushSize.setText("+");
        increaseBrushSize.addActionListener(actionHandler);
        brushPanel.add(increaseBrushSize);

        JButton decreaseBrushSize = new JButton();
        decreaseBrushSize.setText("-");
        decreaseBrushSize.addActionListener(actionHandler);
        brushPanel.add(decreaseBrushSize);

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
                UI_CONST.cardLayout.show(UI_CONST.showPanel, UI_CONST.DRAW_SET_DATA_PANEL);
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
}
