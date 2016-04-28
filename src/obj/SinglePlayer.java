/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package obj;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 *
 * @author USER
 */
public class SinglePlayer extends JFrame{
    private JFrame frame;

    public SinglePlayer() {
        frame = new JFrame();
        frame.setTitle("SINGLE PLAYER GAME");
        frame.setSize(300,300);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //panel added
        frame.add(new SingleP(this));
        //frame.pack();
        frame.setVisible(true);
    }
    
    
    public int getWidth()
    {
        return frame.getWidth();
    }

    public JFrame getFrame() {
        return frame;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                 new SinglePlayer();
            }       
        });     
    }
    
}
