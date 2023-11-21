package lab4;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class PencilShape extends MouseAdapter {
    private DrawingPanel drawingPanel;
    private List<Ellipse2D> pencilLine = new ArrayList<>();
    private Boolean paintStatus = false;

    public PencilShape(DrawingPanel drawingPanel){
        this.drawingPanel = drawingPanel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
         this.paintStatus = true;
         pencilLine.add(new Ellipse2D.Double(e.getX() - 2, e.getY() - 2, 4, 4 ));
         drawingPanel.setTempPencilLine(new Ellipse2D.Double(e.getX() - 2, e.getY() - 2, 4, 4));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(paintStatus){
            pencilLine.add(new Ellipse2D.Double(e.getX() - 2, e.getY() - 2, 4, 4 ));
            drawingPanel.setTempPencilLine(new Ellipse2D.Double(e.getX() - 2, e.getY() - 2, 4, 4));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(paintStatus){
            drawingPanel.addShape(true, (new Ellipse2D.Double(e.getX() - 2, e.getY() - 2, 4, 4)), true, drawingPanel.getColorOfFigure());
            for(int i = 1; i < pencilLine.size(); i++){
                drawingPanel.addShape(false, pencilLine.get(i), true, drawingPanel.getColorOfFigure());
            }
        }
        pencilLine.clear();
        drawingPanel.clearTempPencilLine();
        this.paintStatus = false;
    }
}
