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

    public Racket(JFrame game, int y) {
        super((game.getWidth()- 60) / 2, y, 0, 0, 60, 10);
        this.game = game;
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
