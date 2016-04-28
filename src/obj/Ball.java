/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.awt.Graphics;

/**
 *
 * @author USER
 */
public class Ball extends Sprite{
    
    public Ball(int x,int y) {
        super(x, y, 1, 1, 20, 20);
    }

    public void updatePosition() {
        setX(getX() + getXA());
        setY(getY() + getYA());
    }

    public void paint(Graphics g) {
        g.fillOval(getX(), getY(), getWidth(), getHeight());
    }

}
