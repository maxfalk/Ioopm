/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package pong;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;

/**
 *
 * @author Max
 */
public class Pong  {
    
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        JFrame f = new JFrame("Pong");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel l = new JLabel("");
        PongMenu g = new PongMenu();
        l.setFocusable(true);
        l.add(g);
        l.setSize(500,500);

        l.setVisible(true);
        
        
        
        
        
        
        
        
        
    }
    
    
    
    
    
    
}
