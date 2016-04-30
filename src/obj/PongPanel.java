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
//contains the two components ball and paddle

public class PongPanel extends JPanel{
private Racket racket,racket2,racket3,racket4;
private Ball ball;
private int a=0,b=0,c=0,d=0;
private String[] ee;
private static ArrayList<InetAddress> ip;
private static int[] freq;
private static ArrayList<Integer> portnum;
private JLabel scoreLabel;  //----------------------------------
private int score = 0,score1=0,score2=0,score3=0;
//networking variables
static DatagramSocket DataSoc;
static byte[] rdata,sdata;
static InetAddress IPAddress;
static int port;
static Thread t1,t2;
public PongPanel(Pong game) {
   
    int u1=game.getFrame().getWidth();
    int u2=game.getFrame().getHeight();
    ip=new ArrayList<InetAddress>();
    portnum=new ArrayList<Integer>();
    racket = new Racket((u1/2)-35,u2-50,1,1,70,10,game.getFrame(),1);
    racket2 = new Racket((u1/2)-35,0,0,0,70,10,game.getFrame(),1);
    racket3=new Racket(0,(u2/2)-35,0,0,10,70,game.getFrame(),0);
    racket4=new Racket(u1-30,(u2/2)-35,0,0,10,70,game.getFrame(),0);
    ball = new Ball(u1/2,u2/2);
    
    
    
    
    a=1;                           //-------------------------------------------------------------
    b=0;
    scoreLabel = new JLabel(Integer.toString(score));
    scoreLabel.setFont(new Font("sansserif", Font.PLAIN, 30));  //----------------------------------
    
        try {
            DataSoc=new DatagramSocket(5151);
        } catch (SocketException ex) {
            Logger.getLogger(PongPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
         rdata=new byte[1024];
         sdata=new byte[1024];
      
    addKeyListener((KeyListener) new KeyHandler());
    setFocusable(true);
      //wait for client to shake
        
    
    
    //get all the data of the clients to be playing the game in a loop and make a list.
    int number=Integer.parseInt(ServerC1.num);
<<<<<<< HEAD
    freq=new int[number];
    System.out.println(number);
    int j=0;
    while(j<number)
=======
    for(int i=0;i<number;i++)
>>>>>>> c9aa9f2cdc3ac74ef4cf978cc857b6139f242aec
    {
        System.out.println(j);
        DatagramPacket receivePacket = new DatagramPacket(rdata, rdata.length); 
               
        try {
            DataSoc.receive(receivePacket);
        } catch (IOException ex) {
            Logger.getLogger(PongPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
            
           String modifiedSentence = new String(receivePacket.getData()); 
           //get proper data and set the rackets
           System.out.println("FROM CLIENT:" + modifiedSentence); 
           //get ip add
           IPAddress=receivePacket.getAddress();
           System.out.println(IPAddress.toString());
           ip.add(IPAddress);
           System.out.println(ip.size());
           //get port
           port=receivePacket.getPort();
           portnum.add(port);
           j++;
           System.out.println(j);
         }
    System.out.println("for loop complete");
    
    try {
        //add your data in the list
        IPAddress=InetAddress.getByName(ServerC1.ipaddress);
    } catch (UnknownHostException ex) {
        Logger.getLogger(PongPanel.class.getName()).log(Level.SEVERE, null, ex);
    }
    ip.add(IPAddress);
    port=5151;
    portnum.add(port);
    
    
    //send the data of all players to all the clients
    
    
    //read all the ipaddresses as stings
    String ipadd="";
    for(InetAddress d:ip)
    {
        ipadd=ipadd+d.toString()+",";
    }
    ipadd=ipadd.substring(0,ipadd.length()-1);
    
    
    //read all the ports
     String ports="";
    for(Object d:portnum)
    {
        ports=ports+d.toString()+",";
    }
    ports=ports.substring(0,ports.length()-1);
    
    
    //send above strings to clients
    
    for(int y=0;y<number;y++)
    {
        sdata = ipadd.getBytes();  //ipaddresses
        byte[] sdata1=ports.getBytes(); //ports
        String str=Integer.toString(y);
        byte[] sdata2=str.getBytes(); //player number
        //send to server only
         DatagramPacket sendPacket;
         DatagramPacket sendPacket1;   
         DatagramPacket sendPacket2;
        
        //send to each client
        
            sendPacket = new DatagramPacket(sdata, sdata.length,(InetAddress)ip.get(y), (int)portnum.get(y));  
            sendPacket1=new DatagramPacket(sdata1, sdata1.length, (InetAddress)ip.get(y), (int)portnum.get(y));
            sendPacket2=new DatagramPacket(sdata2, sdata2.length, (InetAddress)ip.get(y), (int)portnum.get(y));
        
            int u=0;
                    while(u<3)
                    {
                        if(u==0)
                        {   try {
                            DataSoc.send(sendPacket);
                                    System.out.println("port");
                            } catch (IOException ex) {
                                Logger.getLogger(PongPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
}else if(u==1){             try {
    DataSoc.send(sendPacket1);
                            System.out.println("sent ip");
                            } catch (IOException ex) {
                                Logger.getLogger(PongPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
}else{                      try {
    DataSoc.send(sendPacket2);
                            System.out.println(sdata2.toString());
                            System.out.println("sent number");
                            } catch (IOException ex) {
                                Logger.getLogger(PongPanel.class.getName()).log(Level.SEVERE, null, ex);
                            }
}
                        
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(PongPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            u++;
                    }
       
        System.out.println("sent all the packets");
        
    }
    
    //remove yourself from list
    ip.remove(ip.size()-1);
    portnum.remove(portnum.size()-1);
    
    
    
    Timer timer = new Timer(5, new TimerHandler());
    timer.setRepeats(false);
    timer.setInitialDelay(5000);
    //timer.start();
    
    
}

private void update() {//--------------------------------
    racket3.updatePosition();
    racket4.updatePosition();
    racket2.updatePosition();
    racket.updatePosition();
    ball.updatePosition();
    checkCollisionBallSides();
    checkCollisionBallRacket();
    checkCollsionBallRacket2();
    checkCollsionBallRacket3();
    checkCollsionBallRacket4();
    checkCollisionRacketRacket();
    repaint();
}
//------------------------------------------------check collisions
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
        Toolkit.getDefaultToolkit().beep();
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
        if(a==1 && b==0 && c==0 && d==0)
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
    }

    @Override
    public void keyTyped(KeyEvent e) {          
    }   
}

private class TimerHandler implements ActionListener {
    
    
    private void send(String s)
    {
        sdata = s.getBytes();  
        
        //send in loop to each client
        for(int y1=0;y1<ip.size();y1++)
    { 
        IPAddress=(InetAddress)ip.get(y1);
        port=(int) portnum.get(y1);
        
         DatagramPacket sendPacket;
        
        //send to each client
        
            sendPacket = new DatagramPacket(sdata, sdata.length,IPAddress, port);  
        try {
            DataSoc.send(sendPacket);
        } catch (IOException ex) {
            Logger.getLogger(PongPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    }
    
    private String[] receive()
    {       
        
        //simulateaneously check if u-you can receive packet from all the clients
        
        
        
        
        
        DatagramPacket receivePacket = new DatagramPacket(rdata, rdata.length); 
               
        try {
            DataSoc.receive(receivePacket);
        } catch (IOException ex) {
            Logger.getLogger(PongPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
            
           String modifiedSentence = new String(receivePacket.getData());  
           System.out.println("FROM CLIENT:" + modifiedSentence); 
           IPAddress = receivePacket.getAddress();
            port = receivePacket.getPort(); 
            
            //check if port present in arraylist
            //if yes then add some freq in new array at that point
            //check the numbers in the array after every 5sec 
            //if 0 at some point then remove that particular index
            //change the freq to zero again
            if(portnum.contains(port))
            {
            int mw=portnum.indexOf(port);
            freq[mw]=freq[mw]+1;
            }
            //run a process parallely after every 5 secs
            
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
             //removal of data
             
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
                          String[] s=receive();
                            System.out.println("something");
                          System.out.println(s);   
            ee = s[0].split(",");//split from zeroes
            racket.setX(Integer.parseInt(ee[4]));//set racket position
            racket2.setX(Integer.parseInt(ee[8]));
            racket3.setY(Integer.parseInt(ee[13]));
            racket4.setY(Integer.parseInt(ee[17]));
            int c,d=0;
            //ball posiitons
            int a1=Integer.parseInt(ee[0]);
            int b1=Integer.parseInt(ee[1]); 
            //set ball speeds
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
            
            //updates for AI from number of players left....hence -----------------------------------
            //remove the player from the list if no response
            int f=ip.size();
            if(f==1)
            {
             racket3.setCompu(ball, 0,3);
            racket4.setCompu(ball, 0,4);  
            }
            else if(f==2)
                    {
                    racket4.setCompu(ball, 0,4);  
                    }
            else
            {
             racket2.setCompu(ball, 1,2);
             racket3.setCompu(ball, 0,3);
            racket4.setCompu(ball, 0,4);  
            }
            
           
            
            update();
                        }
                     
             }
             });
             t1.start();
             
                       

         
    }
}
    
}
