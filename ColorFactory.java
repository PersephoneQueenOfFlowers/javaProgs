/* created by Seth Schoenfeld
 * sschoen1@mail.ccsf.edu
 * purpose: to display any color combination
 * and its respective numeric value when
 * chosen from 3 slider values between 1-255
 * input: user grabs and pushes sliders with mouse
 * process: slider values are passed to color constructor
 * in oval for display 
 * output: quantities of respective colors 
 * (red, green, blue) are shown with bar graph
 * and respective numeric quantities are displayed 
 * in button chosen format (decimal, octal, hex, binary)
 * created 4/8/13 modified 4/10/13
 */


import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;



public class ColorFactory extends JFrame implements ItemListener
{

    private JLabel jTitleLabel;
    private JRadioButton decimalRBtn, octalRBtn, hexDecRBtn, binaryRBtn;
    JSlider sliderRed, sliderGreen, sliderBlue;
    JLabel scrollBarLabelRed, scrollBarLabelGreen, scrollBarLabelBlue;
    private int rSliderValue, gSliderValue, bSliderValue; // linked to slider values    

    JTextField redAmount, greenAmount, blueAmount;
    
    int redHeight=1;
    int greenHeight=1;
    int blueHeight=1;
   
    int redYvalue=480;
    int greenYvalue=480;
    int blueYvalue=480;
   
    JPanel titlePanel = new JPanel();
    JPanel leftPanel = new JPanel(new GridLayout(1,1));
    JPanel rightPanel = new JPanel(new GridLayout(1,1));
    JPanel buttonPanel = new JPanel(new GridLayout(16,1));
    JPanel labelPanel = new JPanel();
    JPanel graphicsPanel = new JPanel(new GridLayout(1,1));
    
   
   //*********number strings for conversion()*****************//
    String messageRed = "0";
    String messageGreen ="0";
    String messageBlue = "0";
    String title = "Color Factory";
   
   //*********for new color values passed to shape()*********//
    Color blendedColor = new Color(rSliderValue,gSliderValue,bSliderValue);
   
   //*******************Class Constructor()*****************//    
    public ColorFactory()
    {  
        setTitle("Color Factory");
        setSize(700,700);
        
        //***********program title for NORTH border()********//
        jTitleLabel = new JLabel("Color Factory", JLabel.CENTER);
        jTitleLabel.setFont(new Font("Garamond", Font.BOLD, 86));
        jTitleLabel.setForeground(Color.white);

        
        
        
        
        
        
        
        
        titlePanel.add(jTitleLabel);
        
        
   BorderFactory.createLineBorder(Color.black, 5);
//title.setTitlePosition(TitledBorder.ABOVE_TOP);
//jTitleLabel.setBorder(LineBorder);


                
        //***********set layout for pane()******************//
        Container pane = getContentPane();
        pane.setLayout(new BorderLayout());
        pane.setSize(800, 800);
//        setBorder(new LineBorder(Color.red));
        pane.add(leftPanel, BorderLayout.WEST);
//        leftPanel.setForeground(Color.BLACK);
        pane.add(rightPanel, BorderLayout.EAST);
        pane.add(titlePanel, BorderLayout.NORTH);
        pane.add(labelPanel, BorderLayout.SOUTH);
        pane.add(graphicsPanel, BorderLayout.CENTER);

        
        //************create sliders()*********************//
         sliderRed = new JSlider(JSlider.HORIZONTAL,0, 255, 0);
         sliderRed.setMajorTickSpacing(50);
         sliderRed.setMinorTickSpacing(5);
         sliderRed.setPaintTicks(true);
         sliderRed.setPaintLabels(true);
         
         sliderGreen = new JSlider(JSlider.HORIZONTAL,0, 255, 0);
         sliderGreen.setMajorTickSpacing(50);
         sliderGreen.setMinorTickSpacing(5);
         sliderGreen.setPaintTicks(true);
         sliderGreen.setPaintLabels(true);
    
         sliderBlue = new JSlider(JSlider.HORIZONTAL,0, 255, 0);
         sliderBlue.setMajorTickSpacing(50);
         sliderBlue.setMinorTickSpacing(5);
         sliderBlue.setPaintTicks(true);
         sliderBlue.setPaintLabels(true);

  
         
         //**********to hear events from buttons()****//
        ChangeListener s = new ChangeListener(){
            @Override
        public void stateChanged(ChangeEvent ce) 
        {
                
           rSliderValue= sliderRed.getValue();
           gSliderValue = sliderBlue.getValue();
           bSliderValue = sliderGreen.getValue();
             
           blendedColor = new Color(rSliderValue,gSliderValue,bSliderValue);
         
                sliderChange(rSliderValue, gSliderValue, bSliderValue);

         }
        };

        sliderRed.addChangeListener(s);
        sliderGreen.addChangeListener(s);
        sliderBlue.addChangeListener(s);
       
        //*******create buttons and group()**********//
        decimalRBtn = new JRadioButton("Decimal", true);
        octalRBtn = new JRadioButton("Octal");
        binaryRBtn = new JRadioButton("Binary");
        hexDecRBtn = new JRadioButton("Hex");
        
        ButtonGroup myButtonGroup = new ButtonGroup();
        myButtonGroup.add(decimalRBtn);
        myButtonGroup.add(octalRBtn);
        myButtonGroup.add(hexDecRBtn);
        myButtonGroup.add(binaryRBtn);
        
        rightPanel.add(buttonPanel);
        rightPanel.setBackground(Color.black);
//        myButtonGroup.setBackground(Color.BLACK);
         
        //***empty labels for extra grid spacing()***//
        labelPanel.add(new JLabel("               "));
        labelPanel.add(new JLabel("               "));
        labelPanel.add(new JLabel("               "));
        labelPanel.add(new JLabel("               "));
        buttonPanel.add(new JLabel("              "));
        buttonPanel.add(new JLabel("              "));
    
        //*buttons added to grid()****/
        buttonPanel.add(decimalRBtn);
        buttonPanel.add(octalRBtn);
        buttonPanel.add(hexDecRBtn);
        buttonPanel.add(binaryRBtn);
 
        //*****extra grid space()****//
        buttonPanel.add(new JLabel(""));
        buttonPanel.add(new JLabel(""));
         
        
   
        //*********sliders added()*****//
        buttonPanel.add(sliderRed);
        buttonPanel.add(sliderGreen);
        buttonPanel.add(sliderBlue);
     
        //***private inner class for graphics in CENTER grid cell()*****//
        BrightOval bOval = new BrightOval(); 
        graphicsPanel.add(bOval);             
     
    }


    
  private class BrightOval extends JPanel
  {
   
     
      @Override
      public void paintComponent(Graphics g)
      {
         setOpaque(true);
         super.paintComponents(g);

         Graphics2D g2d = (Graphics2D) g;
         FontRenderContext fontRendContext = g2d.getFontRenderContext();
         Font font = new Font("Garamond", 2, 50);
         String st = new String("Color Factory");
         
         int w = getSize().width;
         int h = getSize().height;
 /**        
         TextLayout text = new TextLayout(st, font, fontRendContext);
         
         AffineTransform affineTransform = new AffineTransform();
         
         Shape shape = text.getOutline(null);
         
         Rectangle rect = shape.getBounds();
         
         affineTransform = g2d.getTransform();
         
         affineTransform.translate(w / 2 - rect.width / 2, h / 2
                 + (rect.height / 2));
         
         g2d.transform(affineTransform);
         
         g2d.setColor(Color.red);
         
         g2d.draw(shape);
         
         g2d.setClip(shape);
*/         
         g.setColor(blendedColor);
         g.fillOval(150, 100, 250, 80);
         g.setColor(Color.RED);
         g.fillRect(155, redYvalue, 60, redHeight);
         g.setColor(Color.GREEN);
         g.fillRect(250, greenYvalue, 60, greenHeight );
         g.setColor(Color.BLUE);
         g.fillRect(355, blueYvalue, 60, blueHeight );
 //        g.setColor(Color.black);
 
         g.drawString(messageRed, 150,500);
         g.drawString(messageGreen, 250, 500);
         g.drawString(messageBlue, 350, 500);
         
         int fontSize = 85;
 
         g.setColor(Color.blue);
         g.setFont(new Font("Garamond", Font.ROMAN_BASELINE, fontSize));
         setVisible(true);
         repaint();
      }
  }
  
    
    @Override
    public void itemStateChanged(ItemEvent ie) 
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    //*****to pick up changes in sliders and act()*****//
    public class Slid implements ChangeListener 
    { 
       int rSliderValue;
       int gSliderValue;
       int bSliderValue;
      
        @Override
        public void stateChanged(ChangeEvent e)
        {
           rSliderValue= sliderRed.getValue();
            gSliderValue = sliderGreen.getValue();
            bSliderValue = sliderBlue.getValue();
            sliderChange(rSliderValue, gSliderValue, bSliderValue);
        }
    }
    
    public void sliderChange(int r, int g, int b)
    {
    //strings for output conversion()******//    
        String outPutR;
        String outPutG;
        String outPutB;
       
    //************output to binary()*******//
        if(binaryRBtn.isSelected() == true)
        {   
            outPutR = Integer.toBinaryString(r);
            outPutG = Integer.toBinaryString(g);
            outPutB = Integer.toBinaryString(b);

            messageRed = ("" + outPutR);  
            messageGreen = ("" + outPutG);
            messageBlue = ("" + outPutB);
        }
        //********output to hex()************//
        else if(hexDecRBtn.isSelected() == true)
        {
            outPutR = Integer.toHexString(r);
            outPutG = Integer.toHexString(g);
            outPutB = Integer.toHexString(b);
     
          
            messageRed = ("" + outPutR);
            messageGreen = ("" + outPutG);
            messageBlue = ("" + outPutB);
        }
        //**********output to Octal()********//
        else if(octalRBtn.isSelected() == true)
        {
            outPutR = Integer.toOctalString(r);
            outPutG = Integer.toOctalString(g);
            outPutB = Integer.toOctalString(b);
          
           
            messageRed = ("" + outPutR);
            messageGreen = ("" + outPutG);
            messageBlue = ("" + outPutB);
        }
        //*********output to decimal()********//
        else if(decimalRBtn.isSelected() == true)
        {
            outPutR = Integer.toString(r);
            outPutG = Integer.toString(g);
            outPutB = Integer.toString(b);
          
           
            messageRed = ("" + outPutR);
            messageGreen = ("" + outPutG);
            messageBlue = ("" + outPutB);
        }
      
        //******Bar Display rise and fall()******//
        redHeight=1; 
        greenHeight=1; 
        blueHeight=1;
      
        redHeight += rSliderValue;
        greenHeight += gSliderValue;
        blueHeight += bSliderValue;
      
        redYvalue = 475;
        redYvalue -=rSliderValue;
      
        greenYvalue = 475;
        greenYvalue -=gSliderValue;
      
        blueYvalue = 475;
        blueYvalue -=bSliderValue;
      
        repaint();
      
    } 

    public static void main(String args[]) 
    {
        ColorFactory cf = new ColorFactory();
        cf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cf.setVisible(true);
        cf.setBackground(Color.black);
    }


}
