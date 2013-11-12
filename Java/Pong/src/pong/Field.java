/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package pong;

/**
 *
 * @author Max
 */
public class Field {
    
    private Position topLeftCorner;
    private Position bottomRightCorner;
    public Ball ball = new Ball(2);
    public Player player1 = new Player();
    public Player player2 = new Player();
    
    Field(Position topLeftCorner, Position bottomRightCorner){
        this.topLeftCorner = topLeftCorner;
        this.bottomRightCorner = bottomRightCorner;
    }
    
    public Position getTopLeftCorner(){
        return this.topLeftCorner;
    }
    
    public Position getBottomRightCorner(){
        return this.bottomRightCorner;
    }
    
    public Position getMiddle(){
        Position pos = new Position();
        pos.x = (topLeftCorner.x + bottomRightCorner.x)/2;
        pos.y = (topLeftCorner.y + bottomRightCorner.y)/2;
        return pos;
    }
    
    
}
