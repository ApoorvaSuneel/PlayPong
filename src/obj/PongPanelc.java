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
import java.util.ArrayList;
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

public class PongPanelc extends JPanel {
    private Racket racket,racket2;
    static DatagramSocket pc;
    static byte[] rdata,sdata;
    private Ball ball;
    static InetAddress IPAddress;
    static int port;
    private int a=0,b=0,a2=0,b2=1;
    private JLabel scoreLabel;
    private int score = 0,score1=0;
    String[] ee;
    static Thread t1,t2;
    
    
public PongPanelc(Pongc game) {
    
      int u1=game.getFrame().getWidth();
    int u2=game.getFrame().getHeight();
    racket = new Racket((u1/2)-35,u2-50,1,1,70,10,game.getFrame(),1);
    racket2 = new Racket((u1/2)-35,0,0,0,70,10,game.getFrame(),1);
      ball = new Ball(0,0);
      a=0;
      b=1;
       
        try {
            //code of networking
            //after every interval t you create a socket and wait for data
            //CREATE SOCKET0------------------------------------------------
            pc=new DatagramSocket(5150);
        } catch (SocketException ex) {
            Logger.getLogger(PongPanelc.class.getName()).log(Level.SEVERE, null, ex);
        }
        rdata=new byte[1024];
        sdata=new byte[1024];
    scoreLabel = new JLabel(Integer.toString(score));
    scoreLabel.setFont(new Font("sansserif", Font.PLAIN, 30));
       
    
    try {
            //add(scoreLabel);
            IPAddress=InetAddress.getByName("127.0.0.1");
            port=5151;
        } catch (UnknownHostException ex) {
            Logger.getLogger(PongPanelc.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //send initial data
        String s1="hey there";
        sdata=s1.getBytes();  
            //send to server only for sending
        DatagramPacket sendPacket1;
        sendPacket1 = new DatagramPacket(sdata, sdata.length,IPAddress  ,port);  
        try {
            pc.send(sendPacket1);
        } catch (IOException ex) {
            Logger.getLogger(PongPanelc.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Timer timer = new Timer(5, new TimerHandler());
        //timer.setInitialDelay(10000);
        timer.setRepeats(false);
        timer.start();
        addKeyListener((KeyListener) new KeyHandler());
        setFocusable(true);
    
    
    
}


private void update() {
    racket.updatePosition();
    racket2.updatePosition();
    ball.updatePosition();
    this.a=a2;
    this.b=b2;
    checkCollisionBallSides();
    checkCollisionBallRacket();
    checkCollisionBallRacket2();
    repaint();
}


private void checkCollisionBallSides() {
    if (ball.getX() < 0 || ball.getX() > getWidth() - ball.getWidth() - (getInsets().left + getInsets().right))
        ball.setXA(-ball.getXA());
    else if (ball.getY() < 0)
        {
        JOptionPane.showMessageDialog(null,"Player1 : "+Integer.toString(score)+"\n"+"Player2 :"+Integer.toString(score1) , "Pong", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
        }
        
    else if (ball.getY() > getHeight() - ball.getHeight()) {
        JOptionPane.showMessageDialog(null, "Player1 : "+Integer.toString(score)+"\n"+"Player2 : "+Integer.toString(score1), "Pong", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);}
}

private void checkCollisionBallRacket() {
    if (ball.getBounds().y + ball.getHeight() == racket.getBounds().y &&
        ball.getBounds().x + ball.getWidth() > racket.getBounds().x &&
        racket.getBounds().x + racket.getWidth() > ball.getBounds().x) {
        ball.setYA(-ball.getYA());
        a=0;
        b=1;
        score++;
        scoreLabel.setText(Integer.toString(score));
    }
}

@Override
public void paint(Graphics g) {
    super.paint(g);
    racket2.paint(g);
    racket.paint(g);
    ball.paint(g);
}

    private void checkCollisionBallRacket2() {
        if (ball.getBounds().y == racket2.getBounds().y+ + racket2.getHeight() &&
        ball.getBounds().x + ball.getWidth() > racket2.getBounds().x &&
        racket2.getBounds().x + racket2.getWidth() > ball.getBounds().x) {
        ball.setYA(-ball.getYA());
        score1++;
        a=1;
        b=0;
        scoreLabel.setText("Player1 : "+Integer.toString(score)+"\n"+"Player2 :"+Integer.toString(score1));
    }    
    }


    
    private class KeyHandler implements KeyListener {
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("key pressed");
         if(a==0 && b==1)
        {
            
            System.out.println("condition true");    
        racket2.pressed(e);    
        }
       
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
      //some extra functions
        private void send(String s)
        {
             //SEND DATA      
           sdata=s.getBytes();  
            //send to server only for sending
        DatagramPacket sendPacket;
        sendPacket = new DatagramPacket(sdata, sdata.length,IPAddress  ,port);  
        try {
            pc.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(PongPanelc.class.getName()).log(Level.SEVERE, null, ex);
        }}
        
        private String receive()
        {
             //RECEIVE DATA---------------------------------------------------
            
            DatagramPacket receivePacket = new DatagramPacket(rdata, rdata.length); 
               
       try {
            pc.receive(receivePacket);
        } catch (IOException ex) {
            Logger.getLogger(PongPanelc.class.getName()).log(Level.SEVERE, null, ex);
        }
            //get data 
           String modifiedSentence = new String(receivePacket.getData());  
           System.out.println("FROM SERVER:" + modifiedSentence.trim()); 
           return modifiedSentence;
        }
        
    @Override
    public void actionPerformed(ActionEvent e) {
             //send thread
             t1=new Thread(new Runnable() {
                 @Override
                 public void run()
                       {
                           t2.start();
                        while(true)
                        {
         update();
                         //racket data 
         int x=racket.getX();
         int y=racket.getY();
         int vx=racket.getXA();
         int vy=racket.getYA();
         //racket2 data
         int x2=racket2.getX();
         int y2=racket2.getY();
         int vx2=racket2.getXA();
         int vy2=racket2.getYA();
          //ball data
         int x1=ball.getX();
         int y1=ball.getY();
         int vx1=ball.getXA();
         int vy1=ball.getYA();
         //chance data
         int a1r=a;
         int b1r=b;
        //create a string in order r,ball,r2,chance
         String capitalizedSentence = x+","+y+","+vx+","+vy+","+x1+","+y1+","+vx1+","+vy1+","+x2+","+y2+","+vx2+","+vy2+","+a1r+","+b1r;  
        send(capitalizedSentence);
                            System.out.println("sent from pongc");
                            try {
                                Thread.sleep(50);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(PongPanelc.class.getName()).log(Level.SEVERE, null, ex);
                            }
                       }
                       }
             });
             
             //receive thread
             t2=new Thread(new Runnable() 
             {
                 @Override
                 public void run() 
             {
                        while(true)
                        {
                            System.out.println("not yet");
                         //receive then update
                          String s=receive();
                          System.out.println("something");
                          System.out.println(s);   
            ee = s.split(",");//split from zeroes
            racket.setX(Integer.parseInt(ee[0]));//set racket position
            racket2.setX(Integer.parseInt(ee[8]));
            System.out.println(a2+"dljhf");
            System.out.println(b2);
            int c,d=0;
            //chance
            a2=Integer.parseInt(ee[12]);
            b2=Integer.parseInt(ee[13].charAt(0)+"");
            //ball posiitons
            int a1=Integer.parseInt(ee[4]);
            int b1=Integer.parseInt(ee[5]);           
            if(ee[6].charAt(0)=='-')
            {    
                c=(-1)*Integer.parseInt(ee[6].substring(1));
            }
            else
            {
                c=Integer.parseInt(ee[6]);
            }
            
             if(ee[7].trim().charAt(0)=='-')
            {                   
                d=(-1)*Integer.parseInt(ee[7].trim().substring(1));
            }
            else
            {
                d=Integer.parseInt(ee[7].trim());
            }
             
            ball.setX(a1);
            ball.setY(b1);
            ball.setXA(c);
            ball.setYA(d);
            update();
                        }
                     
             }
             });
             t1.start();
             
                       
         
        
    }
    
}
    
}

