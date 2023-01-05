package ui.image;

import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseInputAdapter {
    private final DrawingPanel drawingPanel;
    private Point lastPoint;

    MouseHandler(final DrawingPanel drawingPanel) {
        this.drawingPanel = drawingPanel;
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        lastPoint = e.getPoint();
        draw(lastPoint);
    }

    @Override
    public void mouseDragged(final MouseEvent e) {
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

    private void draw(final Point start) {
        int brushSize = drawingPanel.getBrushSize();
        int x = start.x - (brushSize / 2) + 1;
        int y = start.y - (brushSize / 2) + 1;
        drawingPanel.getG2d().fillOval(x, y, brushSize, brushSize);
        drawingPanel.repaint(x, y, brushSize, brushSize);
    }
}
