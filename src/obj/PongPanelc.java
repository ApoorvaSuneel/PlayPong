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
import java.awt.Toolkit;
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
    private Racket racket,racket2,racket3,racket4;
    static DatagramSocket DataSocC;
    static byte[] rdata,sdata;
    private Ball ball;
    static InetAddress IPAddress;
    static int port;
    private int a=0,b=0,c=0,d=0;
    ArrayList iplist,portlist;
    private JLabel scoreLabel;//-----------------------------------------------------------
    private int score = 0,score1=0,score2=0,score3=0;
    String[] ee;
    static Thread t1,t2;
    
    
public PongPanelc(Pongc game) {
    
      int u1=game.getFrame().getWidth();
    int u2=game.getFrame().getHeight();
    iplist=new ArrayList<InetAddress>();
    portlist=new ArrayList<Integer>();  
    racket = new Racket((u1/2)-35,u2-50,1,1,70,10,game.getFrame(),1);

    racket2=new Racket((u1/2)-35,0,0,0,70,10,game.getFrame(),1);
    racket3=new Racket(0,(u2/2)-35,0,0,10,70,game.getFrame(),0);
    racket4=new Racket(u1-30,(u2/2)-35,0,0,10,70,game.getFrame(),0);
    ball = new Ball(u1/2,u2/2);
    //--------------------------------------------------------------------------set a,b,c,d on receiving data
       
        try {
            //code of networking
            //after every interval t you create a socket and wait for data
            //CREATE SOCKET0------------------------------------------------
            DataSocC=new DatagramSocket();
        } catch (SocketException ex) {
            Logger.getLogger(PongPanelc.class.getName()).log(Level.SEVERE, null, ex);
        }
        rdata=new byte[1024];
        sdata=new byte[1024];
    scoreLabel = new JLabel(Integer.toString(score));
    scoreLabel.setFont(new Font("sansserif", Font.PLAIN, 30));
    
    
    //initially ping the server
     try {
            //add(scoreLabel);
            IPAddress=InetAddress.getByName(ClientC.ipserver);
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
            DataSocC.send(sendPacket1);
        } catch (IOException ex) {
            Logger.getLogger(PongPanelc.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    
    
    
    
    //recive data from server and store in arralists
    String m1,m2,m3="";
    for(int m=0;m<3;m++)
    {
        DatagramPacket receivePacket = new DatagramPacket(rdata, rdata.length); 
               
       try {
            DataSocC.receive(receivePacket);
        } catch (IOException ex) {
            Logger.getLogger(PongPanelc.class.getName()).log(Level.SEVERE, null, ex);
        }
            //get data 
            System.out.println(DataSocC.getLocalPort());
           String modifiedSentence = new String(receivePacket.getData()); 
           if(m==0){m1=modifiedSentence;}
           else if(m==1){m2=modifiedSentence;}
          if(m==2){m3=modifiedSentence;
               System.out.println("got m3");}
           System.out.println("FROM SERVER:" + modifiedSentence); 
        
    }
    System.out.println(m3+"this is m3");
    int m4=Integer.parseInt(m3);
    System.out.println(m4);
     if(m4==0)
     {
     a=0;
     b=1;
     c=0;
     d=0;
     }
     else if (m4==1)
     {
     a=0;
     b=0;
     c=1;
     d=0;
     }
     else
     
     {
     a=0;
     b=0;
     c=0;
     d=1;
     
     }
    
       
    
   
        
        
        
       // -----------------------assuming it does come

       Timer timer = new Timer(5, new TimerHandler());
        //timer.setInitialDelay(10000);
        timer.setRepeats(false);
        //timer.start();
        addKeyListener((KeyListener) new KeyHandler());
        setFocusable(true);
    
    
    
}


private void update() {
    racket.updatePosition();
    racket3.updatePosition1();
    racket4.updatePosition1();
    racket2.updatePosition();
    ball.updatePosition();
    checkCollisionBallSides();
    checkCollisionBallRacket();
    checkCollisionBallRacket2();
    checkCollsionBallRacket3();
    checkCollsionBallRacket4();
    checkCollisionRacketRacket();
    repaint();
}
//----------------------------------------------------------------extra collisions

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
        Toolkit.getDefaultToolkit().beep();
        score++;
        scoreLabel.setText(Integer.toString(score));
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

    private void checkCollisionBallRacket2() {
        if (ball.getBounds().y == racket2.getBounds().y+ + racket2.getHeight() &&
        ball.getBounds().x + ball.getWidth() > racket2.getBounds().x &&
        racket2.getBounds().x + racket2.getWidth() > ball.getBounds().x) {
        ball.setYA(-ball.getYA());
        Toolkit.getDefaultToolkit().beep();
        score1++;
        scoreLabel.setText("Player1 : "+Integer.toString(score)+"\n"+"Player2 :"+Integer.toString(score1));
    }    
    }
    private void checkCollsionBallRacket3() {
    if (ball.getBounds().x == racket3.getBounds().x+ + racket3.getWidth()&&
        ball.getBounds().y + ball.getWidth() > racket3.getBounds().y &&
        racket3.getBounds().y + racket3.getHeight()> ball.getBounds().y) {
        ball.setYA(-ball.getYA());
        Toolkit.getDefaultToolkit().beep();
        score2++;
        scoreLabel.setText("Player1 : "+Integer.toString(score)+"\n"+"\n"+"Player2 :"+Integer.toString(score1)+"\n"+"\n"+"Player3 :"+Integer.toString(score2)+"\n"+"\n"+"Player4 :"+Integer.toString(score3));
    }    
    }
private void checkCollsionBallRacket4() {
    if (ball.getBounds().x +ball.getWidth()== racket4.getBounds().x &&
        ball.getBounds().y + ball.getWidth() > racket4.getBounds().y &&
        racket4.getBounds().y + racket4.getHeight()> ball.getBounds().y) {
        ball.setYA(-ball.getYA());
        Toolkit.getDefaultToolkit().beep();
        score3++;
        scoreLabel.setText("Player1 : "+Integer.toString(score)+"\n"+"\n"+"Player2 :"+Integer.toString(score1)+"\n"+"\n"+"Player3 :"+Integer.toString(score2)+"\n"+"\n"+"Player4 :"+Integer.toString(score3));
        //scoreLabel.setText("Player1 : "+Integer.toString(score)+"\n"+"Player2 :"+Integer.toString(score1));
    }    
    }
private void checkCollisionRacketRacket()
{
    if(racket2.getBounds().x+racket2.getWidth()>=racket4.getBounds().x && racket2.getBounds().y+racket2.getHeight()<racket4.getBounds().y)
    {
        racket2.setXA(0);
    }
    if(racket2.getBounds().x<=racket3.getBounds().x+racket3.getWidth() && racket2.getBounds().y+racket2.getHeight()<=racket3.getBounds().y+racket3.getWidth())
    {
        racket2.setXA(0);
    }
    if(racket3.getBounds().y+racket3.getHeight()>=racket.getBounds().y && racket3.getBounds().x+racket3.getWidth()>=racket.getBounds().x)
    {
        racket3.setYA(0);
    }
    if(racket4.getBounds().y+racket4.getHeight()>=racket.getY() && racket4.getBounds().x>=racket.getX()+racket.getWidth())
    {
        racket4.setYA(0);
    }
    
}


    
    private class KeyHandler implements KeyListener {
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("key pressed");
        if(a==0 && b==1 && c==0 && d==0 )
        {
        racket2.pressed(e);    
        }
        else if(a==0 && b==0 && c==1 && d==0 )
        {
        racket3.pressed(e);    
        }
        else
        {
             racket4.pressed(e);       
        }
        
            
            
        
        
       
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if(a==0 && b==1 && c==0 && d==0 )
        {
        racket2.released(e);    
        }
        else if(a==0 && b==0 && c==1 && d==0 )
        {
        racket3.released(e);    
        }
        else
        {
             racket4.released(e);       
        }
        
    }
    @Override
    public void keyTyped(KeyEvent e) {          
    }   
}


    
    
    private class TimerHandler implements ActionListener {
      //some extra functions
        private void send(String s)
        {
            sdata = s.getBytes();  
        
        //send in loop to each client
        for(int y1=0;y1<iplist.size();y1++)
    { 
        IPAddress=(InetAddress)iplist.get(y1);
        port=(int) portlist.get(y1);
        
         DatagramPacket sendPacket;
        
        //send to each client
        
            sendPacket = new DatagramPacket(sdata, sdata.length,IPAddress, port);  
        try {
            DataSocC.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(PongPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }  
        }
        
        private String receive()
        {
             //RECEIVE DATA---------------------------------------------------
            
            DatagramPacket receivePacket = new DatagramPacket(rdata, rdata.length); 
               
       try {
            DataSocC.receive(receivePacket);
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
                                                   System.out.println("t2 strated");
                        while(true)
                        {
             update();
                         //racket data 
         int x1=racket.getX();
         int y1=racket.getY();
         int vx1=racket.getXA();
         int vy1=racket.getYA();
         //racket2 data
         int x2=racket2.getX();
         int y2=racket2.getY();
         int vx2=racket2.getXA();
         int vy2=racket2.getYA();
         //racket3 data
         int x3=racket3.getX();
         int y3=racket3.getY();
         int vx3=racket3.getXA();
         int vy3=racket3.getYA();
         //racket4 data
         int x4=racket4.getX();
         int y4=racket4.getY();
         int vx4=racket4.getXA();
         int vy4=racket4.getYA();
         
          //ball data
         int x=ball.getX();
         int y=ball.getY();
         int vx=ball.getXA();
         int vy=ball.getYA();
        //create a string in order ball,r1,r2,r3,r4
         String capitalizedSentence = x+","+y+","+vx+","+vy+","+x1+","+y1+","+vx1+","+vy1+","+x2+","+y2+","+vx2+","+vy2+","+x3+","+y3+","+vx3+","+vy3+","+x4+","+y4+","+vx4+","+vy4;  
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
                          String s=receive();
                            System.out.println("something");
                          System.out.println(s);   
            ee = s.split(",");//split from zeroes
            racket.setX(Integer.parseInt(ee[4]));//set racket position
            racket2.setX(Integer.parseInt(ee[8]));
            racket3.setY(Integer.parseInt(ee[13]));
            racket4.setY(Integer.parseInt(ee[17]));
            int c,d=0;
            //chance
            //a2=Integer.parseInt(ee[12]);
            //b2=Integer.parseInt(ee[13].trim());
            //ball posiitons
            int a1=Integer.parseInt(ee[0]);
            int b1=Integer.parseInt(ee[1]);           
            if(ee[2].charAt(0)=='-')
            {    
                c=(-1)*Integer.parseInt(ee[2].substring(1));
            }
            else
            {
                c=Integer.parseInt(ee[2]);
            }
            
             if(ee[3].trim().charAt(0)=='-')
            {                   
                d=(-1)*Integer.parseInt(ee[3].trim().substring(1));
            }
            else
            {
                d=Integer.parseInt(ee[3].trim());
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

