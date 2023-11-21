package lab4;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class RectangleShape extends MouseAdapter {
    private DrawingPanel drawingPanel;
    private Point startPoint;

    public RectangleShape(DrawingPanel panel) {
        this.drawingPanel = panel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (startPoint != null) {
            drawingPanel.setTempRectangle(Math.min(startPoint.x, e.getX()),
                    Math.min(startPoint.y, e.getY()),
                    Math.abs(startPoint.x - e.getX()),
                    Math.abs(startPoint.y - e.getY()));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (startPoint != null) {
            drawingPanel.addShape(true, new Rectangle2D.Float(
                    Math.min(startPoint.x, e.getX()),
                    Math.min(startPoint.y, e.getY()),
                    Math.abs(startPoint.x - e.getX()),
                    Math.abs(startPoint.y - e.getY())
            ), true, drawingPanel.getColorOfFigure());
            drawingPanel.addShape(false, new Rectangle2D.Float(
                    Math.min(startPoint.x, e.getX()),
                    Math.min(startPoint.y, e.getY()),
                    Math.abs(startPoint.x - e.getX()),
                    Math.abs(startPoint.y - e.getY())
            ), false, Color.BLACK);
        }
        drawingPanel.clearTempRectangle();
        startPoint = null;
    }

}
