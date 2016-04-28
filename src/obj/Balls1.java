/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.PopupMenu;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author USER
 */
//class which contains all the balls and initialises their data...jpanel with ball components
public class Balls1 extends JPanel {
        private paddle p;
        private Ball2 b1,b2;
       /* protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            int x = getX();
            int y = getY();

            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
           // g.setColor(p.getcolor());
            g.setColor(b1.getcolor());
            g.setColor(b2.getcolor());
            g.fillOval(0, 30, 30, 30); //adds color to circle
            g.setColor(Color.black);
            // g2.fill(new Rectangle(p.getx(),p.gety(), 5*20, 20));
            g2.drawOval(0, 0, 30, 30); //draws circle
        }*/

        public Balls1() {
           // p=new paddle(400,540,Color.YELLOW);
            b1=new Ball2("red", 10 - (int) Math.round((Math.random() * 20)), 10 - (int) Math.round((Math.random() * 20)));
            b2=new Ball2("red", 10 - (int) Math.round((Math.random() * 20)), 10 - (int) Math.round((Math.random() * 20)));
            setLayout(null);
            // Randomize the speed and direction...
            //(color,xvelocity,yvelocity)
             // add(p);
            add(b1);
           
           //add(new paddle(400,540,Color.BLUE));
           add(b2);
           
            //add(new Ball2("green", 10 - (int) Math.round((Math.random() * 20)), 10 - (int) Math.round((Math.random() * 20))));
        }

    
    }