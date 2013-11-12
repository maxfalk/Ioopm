
/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
 */
package pong;

//~--- JDK imports ------------------------------------------------------------

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author mani9271
 */
public class PongView extends JPanel {
    private JFrame   f;
    private Field    field;

    PongView(JFrame f, Field field) {
        this.f     = f;
        this.field = field;
        f.addKeyListener(new Control());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.BLACK);
        g.setColor(Color.WHITE);
        
        writeField(g);
        writeBall(g);
        writePlayers(g);
    }

    private void writeField(Graphics g) {
        Position topLeftCorner     = field.getTopLeftCorner();
        Position bottomRightCorner = field.getBottomRightCorner();
        g.setColor(Color.WHITE);
        g.drawLine(topLeftCorner.x, topLeftCorner.y, bottomRightCorner.x, topLeftCorner.y);
        g.drawLine(topLeftCorner.x, bottomRightCorner.y, bottomRightCorner.x, bottomRightCorner.y);
    }

    private void writeBall(Graphics g) {
        Ball     b            = field.ball;
        Position ballPos      = b.getPosition();
        int      ballDiameter = ((int) b.getRadius()) * 2;
        g.setColor(Color.WHITE);
        g.fillOval(ballPos.x, ballPos.y, ballDiameter, ballDiameter);
    }
    
    private void writePlayers(Graphics g){
        Player p1 = field.player1;
        Player p2 = field.player2;
        //p1
        Position playerPos = p1.getTopLeftCorner();
        int      left       = playerPos.x;
        int      top        = playerPos.y;

        g.fillRect(left, top, p1.getWidth(), p1.getLength());
        //p2
        playerPos = p2.getTopLeftCorner();
        left       = playerPos.x;
        top        = playerPos.y;     
        g.fillRect(left, top, p2.getWidth(), p2.getLength());      
    
    }
   
    private void keyPressedEvent(KeyEvent event){
    
        if(event.getKeyCode() == KeyEvent.VK_UP){
            Position oldPos = field.player1.getPosition();
            Position newPos = new Position();
            newPos.x = oldPos.x;
            newPos.y = oldPos.y-1;
            field.player1.setPositinon(newPos);
        }else if(event.getKeyCode() == KeyEvent.VK_DOWN){
            Position oldPos = field.player1.getPosition();
            Position newPos = new Position();
            newPos.x = oldPos.x;
            newPos.y = oldPos.y+1;
            field.player1.setPositinon(newPos);
        } 
  
        if(event.getKeyCode() == KeyEvent.VK_F1){
            Position oldPos = field.player2.getPosition();
            Position newPos = new Position();
            newPos.x = oldPos.x;
            newPos.y = oldPos.y-1;
            field.player2.setPositinon(newPos);
        }else if(event.getKeyCode() == KeyEvent.VK_F2){
            Position oldPos = field.player2.getPosition();
            Position newPos = new Position();
            newPos.x = oldPos.x;
            newPos.y = oldPos.y+1;
            field.player2.setPositinon(newPos);
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


//~ Formatted by Jindent --- http://www.jindent.com
