package lab4;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

public class PointShape extends MouseAdapter{
    private DrawingPanel drawingPanel;

    public PointShape(DrawingPanel drawingPanel){
        this.drawingPanel = drawingPanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        drawingPanel.addShape(true, new Ellipse2D.Double(e.getX() - 2, e.getY() - 2, 4, 4), true, drawingPanel.getColorOfFigure());
        drawingPanel.repaint();
    }

}
