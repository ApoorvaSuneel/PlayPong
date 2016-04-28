
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

//import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author USER
 */
public class paddle extends JPanel implements ActionListener,KeyListener{
    private int lx,ly,area;
    Timer t=new Timer(5,this);
    private int vx=0,vy=0;
    private Color c;
    
    paddle(int x,int y,Color c1)
    {
        
        lx=x;
        ly=y;
        c=c1;
        System.out.println("paddle create hua");
        area=20;
        //t.start();
        
       
        
    }
    public int getx()
    {
        return this.lx;
    }
     public int gety()
    {
        return this.ly;
    }
     public Color getcolor()
     {
         return this.c;
     }
   
    public void released()
    {
        vx=0;
        vy=0;
    }
    public void paintComponent(Graphics g)
    {
        System.out.println("paddle paint hua"+c);
        super.paintComponent(g);
        Graphics2D g1 = (Graphics2D)g;
        g1.setColor(c);
        g1.fill(new Rectangle(lx, ly, 5*area, area));
        
        
    }
    public void update()
    {
        if(lx+vx>=0 && lx+vx<=getWidth()-area)
        {
            lx+=vx;
            ly+=vy;
            repaint();
        }
    }

   

    @Override
    public void actionPerformed(ActionEvent e) {
        //repaint();
       // long it=System.currentTimeMillis();
       // this.update();
        
        lx+=vx;
        ly+=vy;
        //System.out.println("position updated");
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void up()
    {
     vy=2;
     vx=0;
     
    }
    public void down()
    {
        vy=-2;
        vx=0;
    }
    public void left()
    {
        vx=-2;
        vy=0;
    }
    public void right()
    {
        vx=2;
        vy=0;
    }


    @Override
    public void keyTyped(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code=e.getKeyCode();
        
        if(code==KeyEvent.VK_LEFT)
        {
            left();
            //System.out.println("fhdsjkal");
        }
        else if(code==KeyEvent.VK_RIGHT)
        {
            right();
        }
        else
        {
            
        }
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
