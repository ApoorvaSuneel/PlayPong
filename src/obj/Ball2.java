/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author USER
 */
//class for each ball a jpanel
public class Ball2 extends JPanel implements Runnable {

        Color color;  //color of the ball
        int diameter; //diametre of teh ball can vary
        long delay; //delay ??
        private int vx;  //velocities
        private int vy;

        public Ball2(String ballcolor, int xvelocity, int yvelocity) {
            if (ballcolor == "red") {
                color = Color.red;
            } else if (ballcolor == "blue") {
                color = Color.blue;
            } else if (ballcolor == "black") {
                color = Color.black;
            } else if (ballcolor == "cyan") {
                color = Color.cyan;
            } else if (ballcolor == "darkGray") {
                color = Color.darkGray;
            } else if (ballcolor == "gray") {
                color = Color.gray;
            } else if (ballcolor == "green") {
                color = Color.green;
            } else if (ballcolor == "yellow") {
                color = Color.yellow;
            } else if (ballcolor == "lightGray") {
                color = Color.lightGray;
            } else if (ballcolor == "magenta") {
                color = Color.magenta;
            } else if (ballcolor == "orange") {
                color = Color.orange;
            } else if (ballcolor == "pink") {
                color = Color.pink;
            } else if (ballcolor == "white") {
                color = Color.white;
            }
            diameter = 30;
            delay = 10;

            vx = xvelocity;
            vy = yvelocity;

            new Thread(this).start();

        }
         public int getx()
    {
        return this.getX();
    }
     public int gety()
    {
        return this.getY();
    }
     public Color getcolor()
     {
         return this.color;
     }
   
   

     
     protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            int x = getX();
            int y = getY();
            System.out.println("ball repainted");
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g.setColor(color);
            g.fillOval(0, 0, 30, 30); //adds color to circle
            g.setColor(Color.black);
            g2.drawOval(0, 0, 30, 30); //draws circle
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(30, 30);
        }
        @Override
        public void run() {
           //part 1 of run1 for position initially
            try {
                // Randamize the location...
                SwingUtilities.invokeAndWait(new Runnable() {
                    @Override
                    public void run() {
                        //fo runnable implementation
                        int x = (int) (Math.round(Math.random() * getParent().getWidth()));
                        int y = (int) (Math.round(Math.random() * getParent().getHeight()));
                       //setting location of what??
                        setLocation(x, y);
                    }
                });
            } catch (InterruptedException exp) {
                exp.printStackTrace();
            } catch (InvocationTargetException exp) {
                exp.printStackTrace();
            }
            //part2 of run1 for speed for remaining part of the game
            while (true) {
                //to change the speed...delay
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException e) {
                    System.out.println("interrupted");
                }
                //paint the ball at new pos and change it randomly
                try {
                    SwingUtilities.invokeAndWait(new Runnable() {
                        @Override
                        public void run() {
                            move();
                            repaint();
                        }
                    });
                } catch (InterruptedException exp) 
                {
                    exp.printStackTrace();
                }
                catch (InvocationTargetException exp) 
                {
                    exp.printStackTrace();
                }
            }
        }

        public void move() {

            int x = getX();
            int y = getY();

            if (x + vx < 0 || x + diameter + vx > getParent().getWidth()) {
                vx *= -1;
                //System.out.println(getParent().getWidth());
                //System.out.println(getParent().getHeight()+"fdhskjl");
            }
            if (y + vy < 0 || y + diameter + vy > getParent().getHeight()) {
                vy *= -1;
            }
            x += vx;
            y += vy;

            // Update the size and location...
            setSize(getPreferredSize());
            setLocation(x, y);

        }
    }