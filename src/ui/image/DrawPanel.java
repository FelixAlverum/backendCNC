package ui.image;

import jankovicsandras.imagetracer.ImageTracer;
import ui.res.UI_CONST;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import ui.image.DrawSetDataPanel;

/**
 * https://wiki.byte-welt.net/wiki/Malen_in_Swing_Teil_2:_ein_einfaches_Malprogramm
 * https://stackoverflow.com/questions/5621338/how-to-add-jtable-in-jpanel-with-null-layout/5630271#5630271
 * https://xmlgraphics.apache.org/batik/using/svg-generator.html
 */
public class DrawPanel extends JPanel {

    private JPanel drawPanel;
    private GridBagConstraints gbc;

    private Point lastPoint;
    public static Image image;
    private Graphics2D g2d;


    public DrawPanel() {
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        initDrawPanel();
        initBrushPanel();
        initFooterPanel();
    }

    private void initDrawPanel() {
        drawPanel = new JPanel(new BorderLayout()) {
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
        drawPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2), "Zeichenoberfläche"));
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
        gbc.weightx = 0.8;
        gbc.weighty = 0.9;
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
        g2d.setColor(new Color(158, 158, 158));
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
        g2d.setColor(new Color(158, 158, 158));
    }


    private void initBrushPanel() {
        JPanel brushPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        brushPanel.setBorder(new TitledBorder(new LineBorder(Color.BLACK, 2), "Pinsel"));
        brushPanel.setBackground(Color.LIGHT_GRAY);

        ActionListener actionHandler = new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                String s = e.getActionCommand();
                if (s.equals(" ")) {
                    JButton button = (JButton) e.getSource();
                    System.out.println("COLOR = " + button.getBackground());
                    g2d.setColor(button.getBackground());
                } else if (s.equals("Löschen")) {
                    clearPaint();
                } else if (s.equals("+")) {
                    System.out.println(UI_CONST.brushSize);
                    if (UI_CONST.brushSize < 68) {
                        UI_CONST.brushSize += 2;

                    }
                } else if (s.equals("-")) {
                    System.out.println(UI_CONST.brushSize);
                    if (UI_CONST.brushSize > 2) {
                        UI_CONST.brushSize -= 2;
                    }
                }
            }
        };

        JButton depth1 = new JButton();
        depth1.setBackground(new Color(158, 158, 158));
        depth1.setText(" ");
        depth1.addActionListener(actionHandler);
        depth1.setToolTipText("Tiefeneinstellung 1");
        brushPanel.add(depth1);

        JButton depth2 = new JButton();
        depth2.setBackground(new Color(91, 91, 91));
        depth2.setText(" ");
        depth2.addActionListener(actionHandler);
        depth2.setToolTipText("Tiefeneinstellung 2");
        brushPanel.add(depth2);

        JButton depth3 = new JButton();
        depth3.setBackground(new Color(0, 0, 0));
        depth3.setText(" ");
        depth3.addActionListener(actionHandler);
        depth3.setToolTipText("Tiefeneinstellung 3");
        brushPanel.add(depth3);

        JButton clear = new JButton();
        clear.setText("Löschen");
        brushPanel.add(clear);
        clear.addActionListener(actionHandler);

        JButton increaseBrushSize = new JButton();
        increaseBrushSize.setText("+");
        increaseBrushSize.setToolTipText("Pinsel vergrößern");
        increaseBrushSize.addActionListener(actionHandler);
        brushPanel.add(increaseBrushSize);

        JButton decreaseBrushSize = new JButton();
        decreaseBrushSize.setText("-");
        decreaseBrushSize.setToolTipText("Pinsel verkleinern");
        decreaseBrushSize.addActionListener(actionHandler);
        brushPanel.add(decreaseBrushSize);

        gbc.gridx = 8;
        gbc.gridy = 0;
        gbc.gridheight = 7;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 0.2;
        gbc.weighty = 0.9;
        add(brushPanel, gbc);
    }

    private void initFooterPanel() {
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.LINE_AXIS));
        footerPanel.add(Box.createHorizontalBox());
        footerPanel.setBackground(Color.GRAY);
        footerPanel.setBorder(new LineBorder(Color.GRAY, 5));

        JButton back = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UI_CONST.cardLayout.show(UI_CONST.showPanel, UI_CONST.MAIN_PANEL);
            }
        });
        back.setText("Zurück");
        back.setMaximumSize(new Dimension(200, 200));
        footerPanel.add(back);

        JButton saveSVG = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //ImageIO.write((RenderedImage) image, "png", new File("./src/Test/DrawPanelPNG.png"));

                    // TODO Twaek Options
                    HashMap<String, Float> options = new HashMap<String, Float>();

                    // Tracing
                    options.put("ltres", 1f);
                    options.put("qtres", 1f);
                    options.put("pathomit", 8f);

                    // Color quantization
                    options.put("colorsampling", 1f); // 1f means true ; 0f means false: starting with generated palette
                    options.put("numberofcolors", 8f);
                    options.put("mincolorratio", 0.02f);
                    options.put("colorquantcycles", 8f);

                    // SVG rendering
                    options.put("scale", 1f);
                    options.put("roundcoords", 1f); // 1f means rounded to 1 decimal places, like 7.3 ; 3f means rounded to 3 places, like 7.356 ; etc.
                    options.put("lcpr", 0f);
                    options.put("qcpr", 0f);
                    options.put("desc", 0f); // 1f means true ; 0f means false: SVG descriptions deactivated
                    options.put("viewbox", 0f); // 1f means true ; 0f means false: fixed width and height

                    // Selective Gauss Blur
                    options.put("blurradius", 0f); // 0f means deactivated; 1f .. 5f : blur with this radius
                    options.put("blurdelta", 20f); // smaller than this RGB difference will be blurred

                    // Palette TODO für import
                    // This is an example of a grayscale palette
                    // please note that signed byte values [ -128 .. 127 ] will be converted to [ 0 .. 255 ] in the getsvgstring function
//                    byte[][] palette = new byte[8][4];
//                    for (int colorcnt = 0; colorcnt < 8; colorcnt++) {
//                        palette[colorcnt][0] = (byte) (-128 + colorcnt * 32); // R
//                        palette[colorcnt][1] = (byte) (-128 + colorcnt * 32); // G
//                        palette[colorcnt][2] = (byte) (-128 + colorcnt * 32); // B
//                        palette[colorcnt][3] = (byte) 127;              // A
//                    }

                    ImageTracer.saveString("./src/Test/DrawPanelSVG.svg", ImageTracer.imageToSVG("./src/Test/DrawPanelPNG.png", options, null));
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        saveSVG.setText("Als SVG speichern");
        saveSVG.setMaximumSize(new Dimension(200, 200));
        footerPanel.add(saveSVG);

        JButton savePNG = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    if (ImageIO.write((RenderedImage) image, "png", new File("./src/Test/DrawPanelPNG.png"))) {
                        System.out.println("-- gespeichert");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        savePNG.setText("Als PNG speichern");
        savePNG.setMaximumSize(new Dimension(200, 200));
        footerPanel.add(savePNG);

        JButton next = new JButton(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ImageIO.write((RenderedImage) image, "png", new File("./src/Test/testguy.png"));
                    TimeUnit.SECONDS.sleep(1);
                    DrawSetDataPanel testo = new DrawSetDataPanel<>();
                    testo.initDrawSetDataPanel();
                    UI_CONST.cardLayout.show(UI_CONST.showPanel, UI_CONST.DRAW_SET_DATA_PANEL);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        next.setText("Weiter");
        next.setMaximumSize(new Dimension(200, 200));
        footerPanel.add(next);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        add(footerPanel, gbc);
    }
}
