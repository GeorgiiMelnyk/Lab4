package lab4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ShapeEditor extends JFrame{
    private Image icon = new ImageIcon("resources/images/Lab4Icon.png").getImage();
    private ImageIcon backArrowIcon = new ImageIcon(new ImageIcon("resources/images/BackArrowImage.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
    private ImageIcon pointIcon = new ImageIcon(new ImageIcon("resources/images/PointImage.png").getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
    private ImageIcon lineIcon = new ImageIcon(new ImageIcon("resources/images/LineImage.png").getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH));
    private ImageIcon lineWithCirclesIcon = new ImageIcon(new ImageIcon("resources/images/LineWithCirclesIcon.png").getImage().getScaledInstance(20,20, Image.SCALE_SMOOTH));
    private ImageIcon rectangleIcon = new ImageIcon(new ImageIcon("resources/images/RectangleImage.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
    private ImageIcon ellipseIcon = new ImageIcon(new ImageIcon("resources/images/EllipseImage.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
    private ImageIcon pencilIcon = new ImageIcon(new ImageIcon("resources/images/PencilImage.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
    private ImageIcon cubeIcon = new ImageIcon(new ImageIcon("resources/images/CubeImage.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
    private Toolkit toolkit = Toolkit.getDefaultToolkit();
    private Dimension dimension = toolkit.getScreenSize();
    private JMenuBar menuBar = new JMenuBar();
    private JToolBar toolbar = new JToolBar();
    private DrawingPanel drawingPanel = new DrawingPanel();
    private PointShape pointShape = new PointShape(drawingPanel);
    private LineShape lineShape = new LineShape(drawingPanel);
    private LineWithCircles lineWCirclesShape = new LineWithCircles(drawingPanel);
    private RectangleShape rectangleShape = new RectangleShape(drawingPanel);
    private EllipseShape ellipseShape =  new EllipseShape(drawingPanel);
    private PencilShape pencilShape = new PencilShape(drawingPanel);
    private CubeShape cubeShape = new CubeShape(drawingPanel);

    public ShapeEditor(){
        super("Lab 4");
        createFrame();
        createMenuBar();
        createToolBar();
        this.setVisible(true);
    }

    private void createFrame(){
        this.setBounds(dimension.width / 2 - 325, dimension.height / 2 - 275, 650, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(icon);
        this.add(drawingPanel);
    }

    private  void createMenuBar(){
        JMenu fileMenu = new JMenu("Файл");
        JMenu colorsMenu = new JMenu("Кольори(чорний)");
        JMenu helpMenu = new JMenu("Довідка");

        JMenuItem black = new JMenuItem("Чорний");
        JMenuItem empty = new JMenuItem("Порожній");
        JMenuItem white = new JMenuItem("Білий");
        JMenuItem red = new JMenuItem("Червоний");
        JMenuItem blue = new JMenuItem("Синій");
        JMenuItem green = new JMenuItem("Зелений");
        JMenuItem purple = new JMenuItem("Фіолетовий");
        JMenuItem pink = new JMenuItem("Рожевий");
        JMenuItem yellow = new JMenuItem("Жовтий");
        JMenuItem orange = new JMenuItem("Помаранчевий");

        black.addActionListener(e -> { drawingPanel.setColorOfFigure(Color.BLACK); colorsMenu.setText("Кольори(чорний)"); });
        empty.addActionListener(e -> { drawingPanel.setColorOfFigure(new Color(0,0,0,0)); colorsMenu.setText("Кольори(порожній)");});
        white.addActionListener(e -> { drawingPanel.setColorOfFigure(Color.WHITE); colorsMenu.setText("Кольори(білий)");} );
        red.addActionListener(e -> { drawingPanel.setColorOfFigure(Color.RED); colorsMenu.setText("Кольори(червоний)");});
        blue.addActionListener(e -> { drawingPanel.setColorOfFigure(Color.BLUE); colorsMenu.setText("Кольори(синій)");});
        green.addActionListener(e -> { drawingPanel.setColorOfFigure(new Color(0, 255, 127)); colorsMenu.setText("Кольори(зелений)");});
        purple.addActionListener(e -> { drawingPanel.setColorOfFigure(new Color(153, 17, 153)); colorsMenu.setText("Кольори(фіолетовий)");});
        pink.addActionListener(e -> { drawingPanel.setColorOfFigure(new Color(240, 118,139)); colorsMenu.setText("Кольори(рожевий)");});
        yellow.addActionListener(e -> { drawingPanel.setColorOfFigure(new Color(255, 255, 0)); colorsMenu.setText("Кольори(жовтий)");});
        orange.addActionListener(e -> { drawingPanel.setColorOfFigure(new Color(237, 145, 33)); colorsMenu.setText("Кольори(помаранчевий)");});


        colorsMenu.add(black);
        colorsMenu.add(empty);
        colorsMenu.add(white);
        colorsMenu.add(red);
        colorsMenu.add(blue);
        colorsMenu.add(green);
        colorsMenu.add(purple);
        colorsMenu.add(pink);
        colorsMenu.add(yellow);
        colorsMenu.add(orange);

        menuBar.add(fileMenu);
        menuBar.add(colorsMenu);
        menuBar.add(helpMenu);

        this.setJMenuBar(menuBar);
    }

    private void createToolBar(){

        JButton backArrowButton = new JButton(backArrowIcon);
        backArrowButton.setToolTipText("Прибрати останню фігуру");
        JButton pointButton = new JButton(pointIcon);
        pointButton.setToolTipText("Крапка");
        JButton lineButton = new JButton(lineIcon);
        lineButton.setToolTipText("Лінія");
        JButton lineWCirclesButton = new JButton(lineWithCirclesIcon);
        lineWCirclesButton.setToolTipText("Лінія з кругами");
        JButton rectangleButton = new JButton(rectangleIcon);
        rectangleButton.setToolTipText("Прямокутник");
        JButton ellipseButton = new JButton(ellipseIcon);
        ellipseButton.setToolTipText("Еліпс");
        JButton pencilButton = new JButton(pencilIcon);
        pencilButton.setToolTipText("Пензлик");
        JButton cubeButton = new JButton(cubeIcon);
        cubeButton.setToolTipText("Куб");

        JLabel label = new JLabel("Фігуру не обрано");

        backArrowButton.addActionListener(e -> drawingPanel.removeLastShape());
        pointButton.addActionListener(e -> { switchTo(pointShape);  label.setText("Крапка"); });
        lineButton.addActionListener(e -> { switchTo(lineShape); label.setText("Лінія"); });
        lineWCirclesButton.addActionListener(e -> { switchTo(lineWCirclesShape); label.setText("Лінія з кругами"); });
        rectangleButton.addActionListener(e -> { switchTo(rectangleShape); label.setText("Прямокутник"); });
        ellipseButton.addActionListener(e -> { switchTo(ellipseShape); label.setText("Еліпс"); });
        pencilButton.addActionListener(e -> { switchTo(pencilShape); label.setText("Пензлик"); });
        cubeButton.addActionListener(e -> { switchTo(cubeShape); label.setText("Куб"); });

        backArrowButton.setFocusPainted(false);
        pointButton.setFocusPainted(false);
        lineButton.setFocusPainted(false);
        lineWCirclesButton.setFocusPainted(false);
        rectangleButton.setFocusPainted(false);
        ellipseButton.setFocusPainted(false);
        pencilButton.setFocusPainted(false);
        cubeButton.setFocusPainted(false);

        toolbar.add(backArrowButton);
        toolbar.add(pointButton);
        toolbar.add(lineButton);
        toolbar.add(lineWCirclesButton);
        toolbar.add(rectangleButton);
        toolbar.add(ellipseButton);
        toolbar.add(pencilButton);
        toolbar.add(cubeButton);
        toolbar.addSeparator(new Dimension(15, 0));
        toolbar.add(label);

        toolbar.setFloatable(false);
        this.add(toolbar, BorderLayout.NORTH);
    }

    private void switchTo(MouseAdapter adapter) {
        for(MouseListener mouseListener : drawingPanel.getMouseListeners()){
            drawingPanel.removeMouseListener(mouseListener);
        }
        for(MouseMotionListener motionListener : drawingPanel.getMouseMotionListeners()){
            drawingPanel.removeMouseMotionListener(motionListener);
        }

        drawingPanel.addMouseListener(adapter);
        drawingPanel.addMouseMotionListener(adapter);
    }

}
