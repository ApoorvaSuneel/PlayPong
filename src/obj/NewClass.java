/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author USER
 */
public class NewClass extends JPanel {
    paddle s;
   Timer t;
    public NewClass() {
        JFrame f=new JFrame();
        t=new Timer(1,new TimerHandler());
        
        s=new paddle(400,540,Color.YELLOW);
        // paddle s1=new paddle(400,440,Color.RED);
        f.add(s);
        
        //JPanel jp=new JPanel();
        //jp.setBackground(Color.red);
        //jp.add(s);
        //System.out.println("main ek hi baar run hua");
        //f.add(s1,BorderLayout.SOUTH);
        //f.add(jp,BorderLayout.NORTH);
         addKeyListener(new KeyHandler());
        setFocusable(true);
       // setFocusTraversalKeysEnabled(false);
       
       
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(800,600);
         f.setVisible(true);
        t.start();
         f.add(new Balls1());
        
        
        
    }
    
    public static void main(String args[])
    {
       
        new NewClass();
        
  }

   


    
    private class KeyHandler implements KeyListener {
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("key pressed");
        int code=e.getKeyCode();
        
        if(code==KeyEvent.VK_LEFT)
        {
            s.left();
            System.out.println("fhdsjkal");
        }
        else if(code==KeyEvent.VK_RIGHT)
        {
            s.right();
        }
        else
        {
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        s.released();
    }

    @Override
    public void keyTyped(KeyEvent e) {          
    }   
}

private class TimerHandler implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        s.update();
    }
}
    
}
