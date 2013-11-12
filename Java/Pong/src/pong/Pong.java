
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pong;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Max
 */
public class Pong {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        
        JFrame f = new JFrame("Pong");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // PongMenu g = new PongMenu(f);
        f.setFocusable(true);
       // f.add(g);
        f.setSize(500, 500);
        f.setVisible(true);
    
   
        Position bottomRight = new Position();
        Position topLeft = new Position();
        bottomRight.x = f.getSize().height-50;
        bottomRight.y = f.getSize().width-50;
        topLeft.x = 50;
        topLeft.y = 50;
        Field field = new Field(topLeft,bottomRight);
        PongView pV = new PongView(f,field);
        f.add(pV);
        //f.repaint();
        f.setSize(500, 500);
        f.setVisible(true);
        Engine e = new Engine(field);
        while(true){
            e.moveBall();
            Thread.sleep(10);
            pV.repaint();
        }
     }
}


//~ Formatted by Jindent --- http://www.jindent.com
