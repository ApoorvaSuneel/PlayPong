/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author USER
 */
//just a simple jframe initiating class
public class AnimatedBalls  {

    public static void main(String[] args) {
        new AnimatedBalls();
    }

    public AnimatedBalls()  {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                } catch (InstantiationException ex) {
                } catch (IllegalAccessException ex) {
                } catch (UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame();          //creates a  new jframe and initialises the balls class 
                                                      //which contains the balls class 
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               // frame.setLayout(new BorderLayout());
                
               
                
                
                JPanel jp=new JPanel();
                
               // jp.setVisible(true);
                //jp.setSize(400, 120);
                //jp.setBackground(Color.red);
                //paddle p=new paddle(400,540,Color.YELLOW);
               // jp.add(p,BorderLayout.CENTER);
              //  frame.add(jp,BorderLayout.SOUTH);
                frame.add(new Balls1());
               // paddle r=new paddle(200, 320, Color.red);
               // r.requestFocus();
               //frame.add(r);                
//ball class containing all the balls
                frame.setSize(400, 400);  //sets teh size of the jframe            
                //frame.pack();//pack the frame
                frame.setVisible(true);
            }
        });
    }}