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
//contains the two components ball and paddle

public class PongPanel extends JPanel{
private Racket racket,racket2;
private Ball ball;
private int a=0,b=0,a2=1,b2=0;
private String[] ee;
private JLabel scoreLabel;
private int score = 0,score1=0;
//networking variables
static DatagramSocket ps;
static byte[] rdata,sdata;
static InetAddress IPAddress;
static int port;
static Thread t1,t2;
public PongPanel(Pong game) {
   
    int u1=game.getFrame().getWidth();
    int u2=game.getFrame().getHeight();
    racket = new Racket((u1/2)-35,u2-50,1,1,70,10,game.getFrame(),1);
    racket2 = new Racket((u1/2)-35,0,0,0,70,10,game.getFrame(),1);
    ball = new Ball(0,0);
    a=1;
    b=0;
    scoreLabel = new JLabel(Integer.toString(score));
    scoreLabel.setFont(new Font("sansserif", Font.PLAIN, 30));
    
        try {
            ps=new DatagramSocket(5151);
        } catch (SocketException ex) {
            Logger.getLogger(PongPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
         rdata=new byte[1024];
         sdata=new byte[1024];
      
    addKeyListener((KeyListener) new KeyHandler());
    setFocusable(true);
      //wait for client to shake
        DatagramPacket receivePacket = new DatagramPacket(rdata, rdata.length); 
               
        try {
            ps.receive(receivePacket);
        } catch (IOException ex) {
            Logger.getLogger(PongPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
            
           String modifiedSentence = new String(receivePacket.getData());  
           System.out.println("FROM CLIENT:" + modifiedSentence); 
        
    //add(scoreLabel);
   


    Timer timer = new Timer(5, new TimerHandler());
    timer.setRepeats(false);
    timer.setInitialDelay(5000);
    timer.start();
    
    
}

private void update() {
    racket2.updatePosition();
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
    racket.paint(g);
    ball.paint(g);
}

    

   

private class KeyHandler implements KeyListener {
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("key pressed");
        if(a==1 && b==0)
        {
            System.out.println("cond true");
        racket.pressed(e);    
        }
      /* else
        {
            racket2.pressed(e);
        }     */ 
        
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
    
    
    private void send(String s)
    {
        sdata = s.getBytes();  
        //send to server only
         DatagramPacket sendPacket;
       
        try {
            IPAddress=InetAddress.getByName("10.192.42.119");
                    
        } catch (UnknownHostException ex) {
            Logger.getLogger(PongPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
            sendPacket = new DatagramPacket(sdata, sdata.length,IPAddress  ,5150);  
        
        try {
            ps.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(PongPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String[] receive()
    {
        DatagramPacket receivePacket = new DatagramPacket(rdata, rdata.length); 
               
        try {
            ps.receive(receivePacket);
        } catch (IOException ex) {
            Logger.getLogger(PongPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
            
           String modifiedSentence = new String(receivePacket.getData());  
           System.out.println("FROM CLIENT:" + modifiedSentence); 
           IPAddress = receivePacket.getAddress();
            port = receivePacket.getPort(); 
            String[] h =new String[3];
            h[0]=modifiedSentence;
            h[1]=IPAddress.toString();
            h[2]=""+port;
            return h;       
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //networking part to be sent every 5 seconds 
                   
               
                 t1=new Thread(new Runnable() {
                 @Override
                 public void run()
                       {
                           t2.start();
                           System.out.println("t2 strated");
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
                            System.out.println("sent data to client"+capitalizedSentence);
                            try {
                                Thread.sleep(5);
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
                 System.out.println("run function executed");
                        while(true)
                        {
                            System.out.println("not yet");
                         //receive then update
                          String[] s=receive();
                            System.out.println("something");
                          System.out.println(s);   
            ee = s[0].split(",");//split from zeroes
            racket.setX(Integer.parseInt(ee[0]));//set racket position
            racket2.setX(Integer.parseInt(ee[8]));
            System.out.println(a2+"dljhf");
            System.out.println(b2);
            int c,d=0;
            //chance
            //a2=Integer.parseInt(ee[12]);
            //b2=Integer.parseInt(ee[13].trim());
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
