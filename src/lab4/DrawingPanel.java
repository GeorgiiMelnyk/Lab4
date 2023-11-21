package lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class DrawingPanel extends JPanel {
    private List<Boolean> newShapeObjects = new ArrayList<>();
    private List<Shape> shapes = new ArrayList<>();
    private List<Boolean> fillStyles = new ArrayList<>();
    private List<Color> colors = new ArrayList<>();
    private Line2D tempLine;
    private List<Shape> tempLineWithCircles = new ArrayList<>();
    private Rectangle2D tempRectangle;
    private Ellipse2D tempEllipse;
    private List<Ellipse2D> tempPencilLine = new ArrayList<>();
    private List<Shape> tempCube = new ArrayList<>();
    private Color colorOfFigure = Color.BLACK;


    public void addShape(boolean newShapeObject, Shape shape, boolean fillStyle, Color color){
        newShapeObjects.add(newShapeObject);
        shapes.add(shape);
        fillStyles.add(fillStyle);
        colors.add(color);
    }
    public void setTempLine(Point start, Point end) {
        this.tempLine = new Line2D.Float(start, end);
        repaint();
    }
    public void setTempLineWithCircles(List<Shape> currentLineWithCircles){
        tempLineWithCircles.clear();
        this.tempLineWithCircles.addAll(currentLineWithCircles);
        repaint();
    }
    public void setTempRectangle(int x, int y, int width, int height) {
        this.tempRectangle = new Rectangle2D.Double(x, y, width, height);
        repaint();
    }

    public void setTempEllipse(int x, int y, int width, int height) {
        this.tempEllipse = new Ellipse2D.Double(x, y, width, height);
        repaint();
    }

    public void setTempPencilLine(Ellipse2D point){
        this.tempPencilLine.add(point);
        repaint();
    }

    public void setTempCube(List<Shape> currentCube){
        tempCube.clear();
        this.tempCube.addAll(currentCube);
        repaint();
    }

    public void clearTempLine() {
        this.tempLine = null;
        repaint();
    }

    public void clearTempLineWithCircles(){
        this.tempLineWithCircles.clear();
        repaint();
    }
    public void clearTempRectangle(){
        this.tempRectangle = null;
        repaint();
    }

    public void clearTempEllipse(){
        this.tempEllipse = null;
        repaint();
    }

    public void clearTempPencilLine(){
        this.tempPencilLine.clear();
        repaint();
    }

    public void clearTempCube(){
        this.tempCube.clear();
        repaint();
    }
    public void setColorOfFigure(Color color){
        this.colorOfFigure = color;
    }

    public Color getColorOfFigure(){
        return this.colorOfFigure;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Stroke thickerStroke = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
        g2.setStroke(thickerStroke);

        for (int i = 0; i < shapes.size(); i++){
            g2.setColor(colors.get(i));
            if(fillStyles.get(i)){
                g2.fill(shapes.get(i));
            } else {
                g2.draw(shapes.get(i));
            }
        }

        if (tempLine != null) {
            g2.setColor(colorOfFigure);
            Stroke dashedStroke = new BasicStroke(2, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0);
            g2.setStroke(dashedStroke);
            g2.draw(tempLine);
        }

        if(tempLineWithCircles != null) {
            g2.setColor(colorOfFigure);
            Stroke dashedStroke = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                    0, new float[]{2}, 0);
            g2.setStroke(dashedStroke);
            for (Shape element : tempLineWithCircles) {
                g2.draw(element);
            }
        }

        if(tempRectangle != null){
            Stroke dashedStroke = new BasicStroke(2, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0);
            g2.setStroke(dashedStroke);
            g2.setColor(colorOfFigure);
            g2.fill(tempRectangle);
            g2.setColor(Color.BLACK);
            g2.draw(tempRectangle);
        }

        if (tempEllipse != null){
            Stroke dashedStroke = new BasicStroke(2, BasicStroke.CAP_BUTT,
                    BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0);
            g2.setStroke(dashedStroke);
            g2.setColor(colorOfFigure);
            g2.fill(tempEllipse);
            g2.setColor(Color.BLACK);
            g2.draw(tempEllipse);
        }

        if (tempPencilLine != null) {
            g2.setStroke(thickerStroke);
            g2.setColor(colorOfFigure);
            for (Ellipse2D point : tempPencilLine) {
                g2.fill(point);
            }
        }

        if (tempCube != null){
            g2.setColor(colorOfFigure);
            Stroke dashedStroke = new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                    0, new float[]{5}, 0);
            g2.setStroke(dashedStroke);
            for (Shape cubeElement : tempCube){
                g2.draw(cubeElement);
            }
        }
    }

    public void removeLastShape(){
        int shapesSize = shapes.size();
        for (int i = (shapesSize - 1); i >= 0; i--){
            if(newShapeObjects.get(i)){
                for(int j = i; i < shapesSize; i++) {
                    colors.remove(j);
                    fillStyles.remove(j);
                    newShapeObjects.remove(j);
                    shapes.remove(j);
                }
                repaint();
                break;
            }
        }
    }

}
