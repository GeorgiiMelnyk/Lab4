package lab4;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;

public class LineShape extends MouseAdapter {
    private DrawingPanel drawingPanel;
    private Point startPoint;

    public LineShape(DrawingPanel drawingPanel){
        this.drawingPanel = drawingPanel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(startPoint != null){
            drawingPanel.setTempLine(startPoint, e.getPoint());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(startPoint != null){
            drawingPanel.addShape(true, new Line2D.Float(startPoint, e.getPoint()), false, drawingPanel.getColorOfFigure());
        }
        drawingPanel.clearTempLine();
        startPoint = null;
    }

}
