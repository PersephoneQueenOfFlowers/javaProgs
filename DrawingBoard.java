/******************************
* created by Seth Schoenfeld
* 4/25/13 last modified 5/6/13
* drawing board application
* inputs commands for various shapes
* process builds shapes for painting
* outputs shapes to screen
********************************/ 


import java.io.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import java.io.Serializable;
import java.util.Collection;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;

//**********************DRAWINGBOARD CLASS BEGINS*******************************************
public class DrawingBoard extends JFrame implements MouseListener, MouseMotionListener
{
//****************Array Lists to hold paint objects()****************************//
    private ArrayList<dbPaintObjects> brushStroke = new ArrayList<dbPaintObjects>(); 
    private ArrayList<dbPaintObjects> eraseStroke = new ArrayList<dbPaintObjects>();
    private ArrayList<dbPaintObjects> drawShapes = new ArrayList<dbPaintObjects>(); 
    private ArrayList<dbPaintObjects> drawRectangles = new ArrayList<dbPaintObjects>();
    private ArrayList<dbPaintObjects> drawOvals = new ArrayList<dbPaintObjects>();
    private ArrayList<dbPaintObjects> drawPolygons = new ArrayList<dbPaintObjects>();
    private ArrayList<ArrayList<dbPaintObjects>> masterArray = new ArrayList<ArrayList<dbPaintObjects>>();
    
    
    
    private int[] linePointsX = null; //for polygon drawing
    private int[] linePointsY = null; // ''   ''        ''
    
    JPanel leftPanel = new JPanel(new GridLayout(1,1));
    JPanel rightPanel = new JPanel(new GridLayout(11,1));
    JPanel buttonPanel = new JPanel(new GridLayout(1,1));
    JPanel graphicsPanel = new JPanel(new GridLayout(1,1));
    
    public JButton draw = new JButton("draw");  
    private JButton clear = new JButton("clear");
    private JButton erase = new JButton("erase");
    private JButton color = new JButton("color");
    private JButton save = new JButton("save");
    private JButton load = new JButton("open");
    private JButton myBrush = new JButton("brush");
    private JButton lines = new JButton("line");
    private JButton rectangles = new JButton("rectangle");
    private JButton ovals = new JButton("oval");
    private JButton polygons = new JButton("polygon");
    
    JLabel drawPush = new JLabel("draw", JLabel.CENTER);
    JLabel erasePush = new JLabel("erase", JLabel.CENTER);
    JLabel colorPush = new JLabel("color", JLabel.CENTER);
    JLabel savePush = new JLabel("save", JLabel.CENTER);
    JLabel openPush = new JLabel("open", JLabel.CENTER);
    JLabel brushPush = new JLabel("brush", JLabel.CENTER);
    JLabel jTitleLabel;
    JColorChooser tcc = new JColorChooser(Color.BLACK);
          

 //************************initialize global variables********************************** 
   
   int mx = 0; int my = 10, linePointCounter = 0;
   public static final int FRAME_WIDTH = 800;
   public static final int FRAME_HEIGHT = 500;
   boolean mySetForeground = false;
   boolean mySetColor = false, mouseClicked = false;
   String brushChar = "asdf";
   public Color newBrushColor = Color.BLUE, lineColor, newLineColor, newRectColor, newPolygonColor;
   public static dbPaintObjects drawStuff = null;
   public static dbPaintObjects eraseStuff = null;
   public static dbPaintObjects drawALine = null;
   public static dbPaintObjects drawARectangle = null;
   public static dbPaintObjects drawAnOval = null;
   public static dbPaintObjects drawAPolygon = null;
   int brushPlace, rectangleHeight, rectangleWidth, polygonSides, eraserSize; //polygonPoints holds number of sides for polygon
   Point mouseClick, p1Line, p2Line, rectangleStart;
   boolean drawLine = false, drawRectangle = false, drawOval = false, drawPolygon = false,
           eraser = false; 
   Point[] arrayCoordinates;
   
    //**********************************************mouse listeners() begins*********
     
    //************************mouseMoved() --empty implement***********************************/     
   public void mouseMoved(MouseEvent me){}
   public void mouseEntered(MouseEvent me){}
   public void mouseExited(MouseEvent me){}
   public void mousePressed(MouseEvent me){}
   public void mouseReleased(MouseEvent me){} 
   public void mouseClicked(MouseEvent me)
   {  
       mouseClicked = true;
       
       
       if(drawLine == true)
       { 
            mx = me.getX(); my = me.getY();
           Point p = new Point(mx,my);

           drawALine = new dbPaintObjects(newLineColor, p);
           drawShapes.add(drawALine);
           repaint();         
       }
       if(drawRectangle == true)
       {
           mx = me.getX(); my = me.getY();
           Point p = new Point(mx,my);

           rectangleHeight = Integer.parseInt(JOptionPane.showInputDialog(null, 
                   "input height of rectangle in pixels:"));
           rectangleWidth = Integer.parseInt(JOptionPane.showInputDialog(null, 
                   "input width of rectangle in pixels:"));
            newRectColor = JColorChooser.showDialog(buttonPanel, 
                        "Color Chooser", Color.CYAN);
           drawARectangle = new dbPaintObjects(newBrushColor, p, rectangleHeight, rectangleWidth);
           drawRectangles.add(drawARectangle);
           
           repaint();             
       }
       if(drawOval == true)
       {
           mx = me.getX(); my = me.getY();
           Point p = new Point(mx,my);

           rectangleHeight = Integer.parseInt(JOptionPane.showInputDialog(null, 
                   "input height of oval in pixels:"));
           rectangleWidth = Integer.parseInt(JOptionPane.showInputDialog(null, 
                   "input width of oval in pixels:"));
           newRectColor = JColorChooser.showDialog(buttonPanel, 
                        "Color Chooser", Color.CYAN);
           drawAnOval = new dbPaintObjects(newRectColor, p, rectangleHeight, rectangleWidth);
           drawOvals.add(drawAnOval);
           repaint();             
       }
       if(drawPolygon == true)
       {
                  
           linePointsX[linePointCounter] = me.getX();
           linePointsY[linePointCounter] = me.getY();
           linePointCounter++;        
           
           if(linePointCounter == polygonSides)
           {
               drawAPolygon = new dbPaintObjects(newPolygonColor, linePointsX, linePointsY, 
                       polygonSides); 
               drawPolygons.add(drawAPolygon);
               linePointCounter = 0; polygonSides = 0;
               repaint();
           }
       }
   }
   public void mouseDragged(MouseEvent me)
   {           
      mx = me.getX() - 8; my = me.getY() - 20;
      Point p = new Point(mx,my);
      if(drawLine == true)
      {
          drawStuff = new dbPaintObjects(newBrushColor, brushChar, p);    
          brushStroke.add(drawStuff);
          repaint();
      }
      else
      {
          eraseStuff = new dbPaintObjects(graphicsPanel.getBackground(), mx, my, eraserSize, eraserSize);    
          eraseStroke.add(eraseStuff);
          repaint();
      }    
   }   
   
 //**************constructor begins()***********************//  
   
   public DrawingBoard()
   {
        
        setTitle("Drawing Board");
        setSize(700,700);
        
        jTitleLabel = new JLabel("Drawing Board", JLabel.CENTER);
        jTitleLabel.setFont(new Font("Garamond", Font.BOLD, 36));
        buttonPanel.setBackground(Color.DARK_GRAY);
        
        rightPanel.add(save);        
        rightPanel.add(load);
        rightPanel.add(draw);
        rightPanel.add(clear);
        rightPanel.add(erase);
        rightPanel.add(color);
        rightPanel.add(myBrush);
        rightPanel.add(lines);
        rightPanel.add(rectangles);
        rightPanel.add(ovals);
        rightPanel.add(polygons);
                
        save.setActionCommand("save");
        load.setActionCommand("load");
        draw.setActionCommand("draw");
        clear.setActionCommand("clear");
        erase.setActionCommand("erase");
        color.setActionCommand("color");
        
        draw.setToolTipText("click to draw");
        load.setToolTipText("click to open a saved file");
        save.setToolTipText("click to save current drawing board");
        clear.setToolTipText("click to clear screen");
        erase.setToolTipText("click to use eraser");
        color.setToolTipText("click to open color selection menu");
        myBrush.setToolTipText("click to choose characters for brush");
        rectangles.setToolTipText("click to create rectangle and"
                + " follow on-screen instructions");
        ovals.setToolTipText("click to create oval and"
                + " follow on-screen instructions");
        polygons.setToolTipText("Click number of times\n " +
                        "contiguously on-screen to place polygon");        
        
        
           
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());
        pane.setSize(700, 700);
        pane.add(graphicsPanel, BorderLayout.CENTER);
        pane.add(buttonPanel, BorderLayout.SOUTH);
        pane.add(rightPanel, BorderLayout.EAST);
        addMouseMotionListener(this);
        addMouseListener(this);
       
        setSize (FRAME_WIDTH, FRAME_HEIGHT);
        setTitle ("Drawing Board");
        GraphicsPanel gp = new GraphicsPanel(); 
        graphicsPanel.add(gp); 
        
 //************actionListeners()*****************************//  
     
      clear.addActionListener(new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {  
                
                System.out.println("erase pushed");
                mySetForeground = true;
                brushStroke.clear();
                drawShapes.clear();
                drawRectangles.clear();
                drawOvals.clear();
                drawPolygons.clear();
                repaint();                
            }
        });
      erase.addActionListener(new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {  
                eraser = true;
                drawLine = false;
                eraserSize = Integer.parseInt(JOptionPane.showInputDialog(
                        "Type in size for eraser"));
                
                repaint();                
            }
        });
      draw.addActionListener(new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {  
                mySetForeground = false;
                eraser = false;
                drawLine = true;
                drawRectangle = false;
                drawOval = false;
            }
        }); 
      color.addActionListener(new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {  
                mySetColor = true;
                newBrushColor = JColorChooser.showDialog(buttonPanel, 
                        "Color Chooser", Color.BLUE);
            }
        }); 
      myBrush.addActionListener(new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {  
                brushChar = JOptionPane.showInputDialog(
                        "Type any characters for painting");
                repaint();
            }
        }); 
      lines.addActionListener(new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {  
                
                drawLine = true;
                drawRectangle = false;
                drawOval = false;
                drawPolygon = false;
                JOptionPane.showMessageDialog(null, "click on screen twice" +
                        " where you want end points and line will appear");
                newLineColor = JColorChooser.showDialog(buttonPanel, 
                        "Color Chooser", Color.BLUE);
                repaint();                
            }
        }); 
      rectangles.addActionListener(new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {  
                
                drawRectangle = true;
                drawLine = false;
                drawOval = false;
                drawPolygon = false;
                JOptionPane.showMessageDialog(null, "click on screen where " +
                        "you want upper left corner of rectange");
            }
        }); 
      ovals.addActionListener(new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {  
                
                drawOval = true;
                drawLine = false;
                drawRectangle = false;
                drawPolygon = false;
                JOptionPane.showMessageDialog(null, "click on screen where " +
                        "you want outside left of oval");
            }
        }); 
      polygons.addActionListener(new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {  
                
                drawPolygon = true;
                drawLine = false;
                drawRectangle = false;
                drawOval = false;
           
           polygonSides = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "input number of sides for polygon:"));
                linePointsX = new int[polygonSides];
                linePointsY = new int[polygonSides];

           
                     newPolygonColor = JColorChooser.showDialog(buttonPanel, 
                        "Color Chooser", Color.CYAN);
           
                JOptionPane.showMessageDialog(null, "Click number of times " +
                        "contiguously on-screen to place polygon");
                
            }
        }); 
      save.addActionListener(new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {               
                masterArray.add(drawShapes);
                masterArray.add(drawRectangles);
                masterArray.add(drawPolygons);
                masterArray.add(brushStroke);
                masterArray.add(eraseStroke);
                masterArray.add(drawOvals);
             JFileChooser jfc = new JFileChooser();
               jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
               if(ae.getSource() == save)
               {
                   int returnValue = jfc.showSaveDialog(graphicsPanel);
                   if(returnValue == JFileChooser.APPROVE_OPTION)
                   {
                       File f = jfc.getSelectedFile();
                       if(f == null || f.getName().equals(" "))
                       {
                           JOptionPane.showMessageDialog(null, "error");
                       }
                       else
                       {
                           try
                           {
                               FileOutputStream fos = new FileOutputStream(f);  
                               ObjectOutputStream outputStream =
                                       new ObjectOutputStream(
                                       new FileOutputStream(f));
                               outputStream.writeObject(brushStroke);
//                               outputStream.writeObject(drawShapes);
//                               outputStream.writeObject(drawRectangles);
                               outputStream.flush();
                               outputStream.close();
                           }
                           catch(IOException e)
                           {
                               JOptionPane.showMessageDialog(null, "File unloadable: "
                                       + e.getMessage());
                           }
                       } 
                   }
               }
            }
        }); 
         
       load.addActionListener(new ActionListener()
          {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {  
               if(ae.getSource() == load)
               {
                   JFileChooser jfc = new JFileChooser();
                   jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                   int returnValue = jfc.showOpenDialog(graphicsPanel);
                   
                   if(returnValue == JFileChooser.APPROVE_OPTION)
                   {
                       File f = jfc.getSelectedFile();
                       if(f == null || f.getName().equals(" "))
                       {
                           JOptionPane.showMessageDialog(null, "error");
                       }
                       else
                       {
                           try
                           {
                               ObjectInputStream inputStream =
                                       new ObjectInputStream(new FileInputStream(f));
                               brushStroke.addAll((ArrayList<dbPaintObjects>)
                                       inputStream.readObject());
                               
                             
                             repaint();
                           }
                           catch(ClassNotFoundException e)
                           {
                               JOptionPane.showMessageDialog(null, "Cannot find " +
                                       "file: " + e.getMessage());
                           }
                           catch(IOException e)
                           {
                               JOptionPane.showMessageDialog(null, "File unloadable: "
                                       + e.getMessage());
                           }                         
                       } 
                   }
               }
            }
        }); 
    }
//*****************************************************Constructor() ends**********//
    public void cls(){}
//***********************graphics panel class begins()***********************************//
    public class GraphicsPanel extends JPanel 
    {
//**************************paintComponent()*********************************************//
        @Override
        @SuppressWarnings("empty-statement")
       public void paintComponent(Graphics g)
       {
          super.paintComponent(g); 
          setBackground(Color.black);
          
          if (mySetForeground == true)
          {
             brushStroke.clear();
          }
          if(mySetColor == true)
          { 
              drawStuff.setColor(newBrushColor); 
          }
          if(drawShapes.size() > 0) 
          {  
             int j = 0, disX = 0, disY = 0, datX = 0, datY = 0;
             for(dbPaintObjects p : drawShapes) 
              {
                  j++;
                 if(j % 2 != 0)
                 {
                     disX = p.getX();
                     disY = p.getY();
                     
                 }
                 else if (j % 2 == 0)
                 {
                     datX = p.getX();
                     datY = p.getY();
                     lineColor = p.getLineColor();
                     g.setColor(lineColor);
                     g.drawLine(disX, disY, datX, datY);
                 }
              }
          }
          if(drawRectangles.size() > 0) 
          {
             
              int X1 = 0, Y1 = 0, rH = 0, rW = 0; 
              for(dbPaintObjects p : drawRectangles)
              {
                  X1 = p.getX(); Y1 = p.getY();
                  rH = p.getRectHeight(); rW = p.getRectWidth();
              
              g.setColor(p.getRectColor());        
              g.drawRect(X1, Y1, rW, rH);
              }
          }
          if(drawOvals.size() > 0) 
          {
             
              int X1 = 0, Y1 = 0, rH = 0, rW = 0; 
              for(dbPaintObjects p : drawOvals)
              {
                  X1 = p.getX(); Y1 = p.getY();
                  rH = p.getRectHeight(); rW = p.getRectWidth();
              g.setColor(p.getRectColor());
                      
              g.drawOval(X1, Y1, rW, rH);
              }
          }
          if(drawPolygons.size() > 0) 
          {
              for(dbPaintObjects p : drawPolygons)
              {
                  
                  g.setColor(p.getPolygonColor());
                  linePointsX = p.getPolygonX();
                  linePointsY = p.getPolygonY();
                  polygonSides = p.getSides();
              g.drawPolygon(linePointsX, linePointsY, polygonSides);
              }
          }
          
          if(brushStroke.size() > 0)
          {
    	      for(dbPaintObjects p : brushStroke) 
              {
                 g.setColor(p.getColor()); 
                 int disX = p.getX();
                 int disY = p.getY();
                 g.drawString(p.getBrush(), disX, disY);
                 
              }
          }
          if(eraseStroke.size() > 0)
          { 
    	      for(dbPaintObjects p : eraseStroke) 
              {
                 g.setColor(p.getEraserColor()); 
                 int disX = p.getEraseX();
                 int disY = p.getEraseY();
                 g.fillOval(disX, disY, eraserSize, eraserSize);
                 
              }
          }
          
          
          
          
       }
       
//*****************************update()*******************************************************       
        @Override
       public void update(Graphics g)
       {
       }
    }
//************************graphics panel class ends******************************************    
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override 
            public void run()
            {
              DrawingBoard dB = new DrawingBoard();
              dB.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              dB.setVisible(true);
            }
        });
        
       
    }    
}
