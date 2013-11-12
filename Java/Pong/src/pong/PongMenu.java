/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package pong;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Max
 */
public class PongMenu extends JPanel{
    
    private int toggled = 0;
    private JFrame f;
    
    public PongMenu(JFrame f){
        this.f = f;
        f.addKeyListener(new Control());
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        g.setColor(Color.WHITE);
        g.drawString("Welcome to Pong", 200, 100);
        write(g,"Start", 230, 120,0);
        write(g,"Exit", 230, 140,1);
          
        
    }

    private void runPong() {
        Position bottomRight = new Position();
        Position topLeft = new Position();
        bottomRight.x = f.getSize().height-20;
        bottomRight.y = f.getSize().width-20;
        topLeft.x = 20;
        topLeft.y = 20;
        Field field = new Field(topLeft,bottomRight);
        PongView pV = new PongView(f,field);
        f.removeAll();
        f.add(pV);
        f.repaint();
    }
    
    private void write(Graphics g, String text, int x, int y, int key){
        if(toggled == key){
            g.setColor(Color.RED);
        }
        g.drawString(text, x, y);
        g.setColor(Color.WHITE);
        
    }
    
    private void keyPressedEvent(KeyEvent event){
    
        if(event.getKeyCode() == KeyEvent.VK_UP){
            if(toggled == 0) toggled =1;
            else if(toggled == 1) toggled =0;
            f.repaint();
        }else if(event.getKeyCode() == KeyEvent.VK_DOWN){
            if(toggled == 0) toggled =1;
            else if(toggled == 1) toggled =0;
            f.repaint();
        }else if(event.getKeyCode() == KeyEvent.VK_ENTER){
            if(toggled == 0){
            runPong();
               
            }else if(toggled == 1){
                System.exit(0);
            }
        }   
        
        
    }
    
 

    
    public class Control implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
            
        }

        @Override
        public void keyPressed(KeyEvent e) {
            keyPressedEvent(e);
          
        }

        @Override
        public void keyReleased(KeyEvent e) {
            
        }
        
        } 
    
    
    
}
