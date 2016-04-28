/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 *
 * @author USER
 */
public class SingleP extends JPanel {
private Racket racket,racket2,racket3,racket4;
private Ball ball;
private int a=0,b=0,a2=1,b2=0;
private JLabel scoreLabel;
private int score = 0,score1=0;
//networking variables
static Thread t1,t2;

public SingleP(SinglePlayer game)
{     //x,y,v1,v2,w,h,gaem.getFraem()
    int u1=game.getFrame().getWidth();
    int u2=game.getFrame().getHeight();
    ball = new Ball(0,0);
    racket = new Racket((u1/2)-35,u2-50,1,1,70,10,game.getFrame(),1);
    racket2 = new Racket((u1/2)-35,0,0,0,70,10,game.getFrame(),1);
    racket2.setCompu(ball,1);
    racket3=new Racket(0,(u2/2)-35,0,0,10,70,game.getFrame(),0);
    racket.setCompu(ball,0);
     racket4=new Racket(u1-30,(u2/2)-35,0,0,10,70,game.getFrame(),0);
     racket4.setCompu(ball,0);
    
    scoreLabel = new JLabel(Integer.toString(score));
    scoreLabel.setFont(new Font("sansserif", Font.PLAIN, 30));
    //add(scoreLabel);


    Timer timer = new Timer(10, new TimerHandler());
    timer.start();
    
    addKeyListener((KeyListener) new KeyHandler());
    setFocusable(true);
}

private void update() {
    racket2.updatePosition();
    racket3.updatePosition();
    racket4.updatePosition();
    racket.updatePosition();
    ball.updatePosition();
    checkCollisionBallSides();
    checkCollisionBallRacket();
    checkCollsionBallRacket2();
    repaint();
}

private void checkCollisionBallSides() 
{
    if (ball.getX() < 0 || ball.getX() > getWidth() - ball.getWidth() - (getInsets().left + getInsets().right))
        ball.setXA(-ball.getXA());  //neg value go opposite
    else if (ball.getY() < 0)
        {
        JOptionPane.showMessageDialog(null,"Player1 : "+Integer.toString(score)+"\n"+"Player2 :"+Integer.toString(score1) , "Pong", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
        }
        //ball.setYA(-ball.getYA());   //go back
    else if (ball.getY() > getHeight() - ball.getHeight()) {
        JOptionPane.showMessageDialog(null, "Player1 : "+Integer.toString(score)+"\n"+"Player2 : "+Integer.toString(score1), "Pong", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }
}

private void checkCollisionBallRacket()
{
    if (ball.getBounds().y + ball.getHeight() == racket.getBounds().y &&
        ball.getBounds().x + ball.getWidth() > racket.getBounds().x &&
        racket.getBounds().x + racket.getWidth() > ball.getBounds().x) {
        ball.setYA(-ball.getYA());
        score++;
        scoreLabel.setText("Player1 : "+Integer.toString(score)+"\n"+"Player2 :"+Integer.toString(score1));
        //scoreLabel.setText(Integer.toString(score));
    }
}

private void checkCollsionBallRacket2() {
    if (ball.getBounds().y == racket2.getBounds().y+ + racket2.getHeight() &&
        ball.getBounds().x + ball.getWidth() > racket2.getBounds().x &&
        racket2.getBounds().x + racket2.getWidth() > ball.getBounds().x) {
        ball.setYA(-ball.getYA());
        score1++;
        scoreLabel.setText("Player1 : "+Integer.toString(score)+"\n"+"Player2 :"+Integer.toString(score1));
    }    
    }
@Override
public void paint(Graphics g) {
    super.paint(g);
    racket2.paint(g);
    racket3.paint(g);
    racket4.paint(g);
    racket.paint(g);
    ball.paint(g);
}


private class KeyHandler implements KeyListener {
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("key pressed");
       
        racket.pressed(e);    
        
       /*else
        {
            racket2.pressed(e);
        }*/      
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        racket.released(e);
        racket2.released(e);
    }

    @Override
    public void keyTyped(KeyEvent e) {          
    }   
}

private class TimerHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) { 
         update();        
    }
}
    

    
    
}
