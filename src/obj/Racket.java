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
     
    public void setCompu(Ball ball,int dir)
    {
        
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

    public void paint(Graphics g) {
        g.fillRect(getX(), getY(), getWidth(), getHeight());
    }
    
}
