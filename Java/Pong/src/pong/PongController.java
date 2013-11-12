
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package pong;

//~--- JDK imports ------------------------------------------------------------

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Max
 */
public class PongController<T> implements KeyListener {
    private T pongOutput;

    public PongController(T obj) {
        pongOutput = obj;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");    // To change body of generated methods, choose Tools | Templates.
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
