package lab4;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

public class LineWithCircles extends MouseAdapter {

    private DrawingPanel drawingPanel;
    private Point startPoint;
    private final int MAXRADIUSOFCIRCLES = 8;
    public LineWithCircles(DrawingPanel drawingPanel){
        this.drawingPanel = drawingPanel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(startPoint != null){
            drawingPanel.setTempLineWithCircles(getCurrentLWithCircles(e.getPoint()));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(startPoint != null) {
            List<Shape> finalLWithEllipses = getCurrentLWithCircles(e.getPoint());
            drawingPanel.addShape(true, finalLWithEllipses.get(0), false, drawingPanel.getColorOfFigure());
            drawingPanel.addShape(false, finalLWithEllipses.get(1), false, drawingPanel.getColorOfFigure());
            drawingPanel.addShape(false, finalLWithEllipses.get(2), false, drawingPanel.getColorOfFigure());
        }
        drawingPanel.clearTempLineWithCircles();
        startPoint = null;
    }

    private List<Shape> getCurrentLWithCircles(Point currentP){
        List<Shape> lineWEllipsesComponents = new ArrayList<>();

        Ellipse2D firstCircle = new Ellipse2D.Double();
        Line2D finalLine = new Line2D.Double();
        Ellipse2D secondCircle = new Ellipse2D.Double();

        double distance = startPoint.distance(currentP);
        double currentRadius = distance / 4;

        // Ограничиваем радиус максимальным значением
        double limitedRadius = Math.min(currentRadius, MAXRADIUSOFCIRCLES);

        // Рисуем круги


        firstCircle.setFrame(startPoint.x - limitedRadius, startPoint.y - limitedRadius, 2 * limitedRadius, 2 * limitedRadius);

        secondCircle.setFrame(currentP.x - limitedRadius, currentP.y - limitedRadius, 2 * limitedRadius, 2 * limitedRadius);

        Line2D line = new Line2D.Double(startPoint.x, startPoint.y, currentP.x, currentP.y);
        // Узнаем координаты первой точки для линии
        double deltaX = (line.getX2() - line.getX1()) / distance;
        double deltaY = (line.getY2() - line.getY1()) / distance;

        double xFifthPixel = line.getX1() + limitedRadius * deltaX;
        double yFifthPixel = line.getY1() + limitedRadius * deltaY;

        // Узнаем координаты второй точки для линии
        double deltaX2 = (line.getX1() - line.getX2()) / distance;
        double deltaY2 = (line.getY1() - line.getY2()) / distance;

        double xFifthPixel2 = line.getX2() + limitedRadius * deltaX2;
        double yFifthPixel2 = line.getY2() + limitedRadius * deltaY2;

        finalLine.setLine(xFifthPixel, yFifthPixel, xFifthPixel2, yFifthPixel2);

        lineWEllipsesComponents.add(firstCircle);
        lineWEllipsesComponents.add(finalLine);
        lineWEllipsesComponents.add(secondCircle);

        return lineWEllipsesComponents;
    }
}
