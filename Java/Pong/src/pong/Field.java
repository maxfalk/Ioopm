
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
    public Ball      ball;
    public Player    player1;
    public Player    player2;
    private Position bottomRightCorner;
    private Position topLeftCorner;

    Field(Position topLeftCorner, Position bottomRightCorner) {
        this.topLeftCorner     = topLeftCorner;
        this.bottomRightCorner = bottomRightCorner;
        
        Position middePos = new Position();
        middePos.x = (topLeftCorner.x + bottomRightCorner.x) / 2;
        middePos.y = (topLeftCorner.y + bottomRightCorner.y) / 2;
        this.ball = new Ball(4,middePos);
        
        Position p1Pos = new Position();
        p1Pos.x = topLeftCorner.x;
        p1Pos.y = middePos.y;
        player1 = new Player(p1Pos);
        
        Position p2Pos = new Position();
        p2Pos.x = bottomRightCorner.x;
        p2Pos.y = middePos.y;
        player2 = new Player(p2Pos);
        
        
    }

    public Position getTopLeftCorner() {
        return this.topLeftCorner;
    }

    public Position getBottomRightCorner() {
        return this.bottomRightCorner;
    }

    public Position getMiddle() {
        Position pos = new Position();

        pos.x = (topLeftCorner.x + bottomRightCorner.x) / 2;
        pos.y = (topLeftCorner.y + bottomRightCorner.y) / 2;

        return pos;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
