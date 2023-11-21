package lab4;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class EllipseShape extends MouseAdapter {
    private DrawingPanel drawingPanel;
    private Point startPoint;

    public EllipseShape(DrawingPanel drawingPanel){
        this.drawingPanel = drawingPanel;
    }
    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(startPoint != null){
            drawingPanel.setTempEllipse(
                    Math.min(startPoint.x, e.getX()),
                    Math.min(startPoint.y, e.getY()),
                    Math.abs(startPoint.x - e.getX()),
                    Math.abs(startPoint.y - e.getY()
                    ));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(startPoint != null){
            drawingPanel.addShape(true, new Ellipse2D.Double(
                    Math.min(startPoint.x, e.getX()),
                    Math.min(startPoint.y, e.getY()),
                    Math.abs(startPoint.x - e.getX()),
                    Math.abs(startPoint.y - e.getY())
            ), true, drawingPanel.getColorOfFigure());
            drawingPanel.addShape(false, new Ellipse2D.Double(
                    Math.min(startPoint.x, e.getX()),
                    Math.min(startPoint.y, e.getY()),
                    Math.abs(startPoint.x - e.getX()),
                    Math.abs(startPoint.y - e.getY())
            ), false, Color.BLACK);
        }
        drawingPanel.clearTempEllipse();
        startPoint = null;
    }

}
