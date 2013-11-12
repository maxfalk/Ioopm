/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;

/**
 *
 * @author Max
 */
public class PongMenu extends JPanel{
    
    public int toggled = 0;
    
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        g.setColor(Color.WHITE);
        g.drawString("Welcome to Pong", 200, 100);
        write(g,"Start", 230, 120,0);
        write(g,"Exit", 230, 140,1);
          
        
    }
    
    private void write(Graphics g, String text, int x, int y, int key){
        if(toggled == key){
            g.setColor(Color.RED);
        }
        g.drawString(text, x, y);
        g.setColor(Color.WHITE);
        
    }
    
    public void keyTyped(KeyEvent event){
    
        if(event.getKeyCode() == KeyEvent.VK_UP){
         toggled += 1;
        }else if(event.getKeyCode() == KeyEvent.VK_DOWN){
            toggled +=1;
        }
    
    
    }
    
}
