package lab4;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class CubeShape extends MouseAdapter {
    private DrawingPanel drawingPanel;
    private Point startPoint;
    public CubeShape(DrawingPanel drawingPanel){
        this.drawingPanel = drawingPanel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (startPoint != null){
           drawingPanel.setTempCube(getCurrentCube(e.getPoint()));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(startPoint != null){
            List<Shape> finalCube = getCurrentCube(e.getPoint());
            drawingPanel.addShape(true, finalCube.get(0), false, drawingPanel.getColorOfFigure());
            drawingPanel.addShape(false, finalCube.get(1), false, drawingPanel.getColorOfFigure());
            drawingPanel.addShape(false, finalCube.get(2), false, drawingPanel.getColorOfFigure());
            drawingPanel.addShape(false, finalCube.get(3), false, drawingPanel.getColorOfFigure());
            drawingPanel.addShape(false, finalCube.get(4), false, drawingPanel.getColorOfFigure());
            drawingPanel.addShape(false, finalCube.get(5), false, drawingPanel.getColorOfFigure());
            finalCube.clear();
        }
        drawingPanel.clearTempCube();
        startPoint = null;
    }

    private List<Shape> getCurrentCube(Point currentP) {
         List<Shape> cubeComponents = new ArrayList<>();

         Rectangle2D frontRectangle = new Rectangle2D.Double();
         Rectangle2D backRectangle = new Rectangle2D.Double();
         Line2D topLeftLine = new Line2D.Double();
         Line2D bottomLeftLine = new Line2D.Double();
         Line2D topRightLine = new Line2D.Double();
         Line2D bottomRightLine = new Line2D.Double();

         double width = (Math.abs(startPoint.x - currentP.getX())) * 0.7;
         double height = (Math.abs(startPoint.y - currentP.getY())) * 0.7;

         if (startPoint.x < currentP.getX() && startPoint.y < currentP.getY()) {
            backRectangle.setRect(startPoint.x, startPoint.y, width, height);
            frontRectangle.setRect((currentP.getX() - width), (currentP.getY() - height), width, height);
            topLeftLine.setLine(startPoint.x, startPoint.y, (currentP.getX() - width), (currentP.getY() - height));
            topRightLine.setLine(startPoint.x + width, startPoint.y, currentP.getX(), (currentP.getY() - height));
            bottomLeftLine.setLine(startPoint.x, startPoint.y + height, (currentP.getX() - width), currentP.getY());
            bottomRightLine.setLine(startPoint.x + width, startPoint.y + height, currentP.getX(), currentP.getY());
        }

         if (startPoint.x > currentP.getX() && startPoint.y < currentP.getY()) {
            backRectangle.setRect(startPoint.x - width, startPoint.y, width, height);
            frontRectangle.setRect(currentP.getX(), (currentP.getY() - height), width, height);
            topLeftLine.setLine(startPoint.x - width, startPoint.y, currentP.getX(), (currentP.getY() - height));
            topRightLine.setLine(startPoint.x, startPoint.y, (currentP.getX() + width), (currentP.getY() - height));
            bottomLeftLine.setLine((startPoint.x - width), (startPoint.y + height), currentP.getX(), currentP.getY());
            bottomRightLine.setLine(startPoint.x, startPoint.y + height, (currentP.getX() + width), currentP.getY());
         }

         if (startPoint.x > currentP.getX() && startPoint.y > currentP.getY()) {
            backRectangle.setRect(startPoint.x - width, startPoint.y - height, width, height);
            frontRectangle.setRect(currentP.getX(), currentP.getY(), width, height);
            topLeftLine.setLine((startPoint.x - width), (startPoint.y - height), currentP.getX(), currentP.getY());
            topRightLine.setLine(startPoint.x, startPoint.y - height, (currentP.getX() + width), currentP.getY());
            bottomLeftLine.setLine((startPoint.x - width), startPoint.y, currentP.getX(), currentP.getY() + height);
            bottomRightLine.setLine(startPoint.x, startPoint.y, (currentP.getX() + width), (currentP.getY() + height));
         }

         if (startPoint.x < currentP.getX() && startPoint.y > currentP.getY()) {
            backRectangle.setRect(startPoint.x, startPoint.y - height, width, height);
            frontRectangle.setRect(currentP.getX() - width, currentP.getY(), width, height);
            topLeftLine.setLine(startPoint.x, (startPoint.y - height), (currentP.getX() - width), currentP.getY());
            topRightLine.setLine((startPoint.x + width), (startPoint.y - height), currentP.getX(), currentP.getY());
            bottomLeftLine.setLine(startPoint.x, startPoint.y, (currentP.getX() - width), (currentP.getY() + height));
            bottomRightLine.setLine(startPoint.x + width, startPoint.y, currentP.getX(), (currentP.getY() + height));
         }

         if (startPoint.x == currentP.getX() || startPoint.y == currentP.getY()) {
            backRectangle.setRect(startPoint.x, startPoint.y, width, height);
            frontRectangle.setRect(currentP.getX(), currentP.getY(), width, height);
            topLeftLine.setLine(startPoint.x, startPoint.y, currentP.getX(), currentP.getY());
            topRightLine.setLine(startPoint.x, startPoint.y, currentP.getX(), currentP.getY());
            bottomLeftLine.setLine(startPoint.x, startPoint.y, currentP.getX(), currentP.getY());
            bottomRightLine.setLine(startPoint.x, startPoint.y, currentP.getX(), currentP.getY());
         }

         cubeComponents.add(backRectangle);
         cubeComponents.add(frontRectangle);
         cubeComponents.add(topLeftLine);
         cubeComponents.add(topRightLine);
         cubeComponents.add(bottomLeftLine);
         cubeComponents.add(bottomRightLine);

         return cubeComponents;
    }
}
