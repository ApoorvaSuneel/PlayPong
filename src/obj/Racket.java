/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;

/**
 *
 * @author USER
 */
public class Racket extends Sprite {
    private final JFrame game;
    int direction;
    public Racket(int x, int y,int vx,int vy,int w,int h,JFrame game,int motion) {
        super(x, y, vx, vy, w, h);
        direction=motion;
        this.game = game;
    }
     //for each racket
    public void setCompu(Ball ball,int dir,int rac)
    {
        //how to set compu?
        if(dir==1)
        {
            //horizontal
            int xb=ball.getX();
            int xr=this.getX();
            int xyb=ball.getXA();
            int xyr=this.getXA();
            if(xr+70<xb || xb<xr-ball.getWidth())
            {
             this.setYA(0);
             this.setXA(ball.getXA());    
            }
            else
            {
             this.setYA(0);
             this.setXA(ball.getXA());     
            }   
            
            
        }
        else
        {
            //vertical
             int yb=ball.getY();
            int yr=this.getY();
            int vyb=ball.getYA();
            int vxb=ball.getXA();
            int vyr=this.getYA();
            if(rac==3)
            {
                if(vxb<0)
                {
                 if(yr+70<yb || yb<yr-ball.getWidth())
            {
             this.setXA(0);
             this.setYA(ball.getYA());    
            }
            else
            {
             this.setXA(0);
             this.setYA(ball.getYA());     
            }      
                }
                
            }
            else if(rac==4)
            {
             if(vxb>0)
                {
                 if(yr+70<yb || yb<yr-ball.getWidth())
            {
             this.setXA(0);
             this.setYA(ball.getYA());    
            }
                 else 
            {
             this.setXA(0);
             this.setYA(ball.getYA());     
            }      
                }   
                
            }
            else
            {
                
            }
           
            
            
        }
    }
    public int getdir()
    {
        return direction;
    }
    public void pressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT)
            setXA(-2);
        else if (key == KeyEvent.VK_RIGHT)
            setXA(2);
    }

    public void released(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_RIGHT)
            setXA(0);
    }

    public void updatePosition() {
        if (getX() + getXA() >= 0 && getX() + getXA() < game.getWidth() - getWidth())
            setX(getX() + getXA());
    }
     public void updatePosition1() {
        if (getY() + getYA() >= 0 && getY() + getYA() < game.getHeight()- getHeight())
            setY(getY() + getYA());
    }


    public void paint(Graphics g) {
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
    
}
