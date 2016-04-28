/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

//import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

/**
 *
 * @author USER
 */
public class paddle implements MouseListener {
    private int lx,ly,area;
    private Color c;
    JPanel j1;
    paddle(int x,int y,Color c1,JPanel j)
    {

        lx=x;
        ly=y;
        c=c1;
        area=20;
        j1=j;
        
    }
    public void move()
    {
        
    }
    public void paint(Graphics g)
    {
        Graphics2D g1 = (Graphics2D)g;
        g1.setColor(c);
        g1.fillRect(lx, ly, 5*area, area);
        
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        c=Color.YELLOW;
        System.out.println("fdjkl;");
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
}
