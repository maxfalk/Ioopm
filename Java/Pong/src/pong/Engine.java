
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
public class Engine {
    
    private Field field;
    
    Engine(Field field) {
        this.field = field;
    }
    
    public void moveBall() {
        Position   topLeftCorner     = field.getTopLeftCorner();
        Position   bottomRightCorner = field.getBottomRightCorner();
        xDirection xDir              = field.ball.getXDirection();
        yDirection yDir              = field.ball.getYDirection();
        Position   ballPos           = field.ball.getPosition();
        Player     player1           = field.player1;
        Player     player2           = field.player2;
        
        if (player1.contains(ballPos) == true) {
            // träffade spelare1s bräde
            switchXPos(xDir, field);
            moveBallX(field.ball.getXDirection(), ballPos, field.ball);
        } else if (player2.contains(ballPos) == true) {
            // träffade spelare2s bräde
            switchXPos(xDir, field);
            moveBallX(field.ball.getXDirection(), ballPos, field.ball);
        }else if ((topLeftCorner.x < ballPos.x) && (bottomRightCorner.x > ballPos.x)) {
            moveBallX(xDir, ballPos, field.ball);

        } else if (topLeftCorner.x < ballPos.x) {
            // KOlla om man träffar en spelar annrs poäng?
            player1.addPoint(1);

            Position pos = field.getMiddle();

            Position newPos = new Position();
            newPos.x =pos.x;
            newPos.y =pos.y;
            field.ball.setPositinon(newPos);
        } else if (bottomRightCorner.x > ballPos.x) {
            System.out.println("point p2");
            // KOlla om man träffar en spelar annrs poäng?
            player2.addPoint(1);

            Position pos = field.getMiddle();
            Position newPos = new Position();
            newPos.x =pos.x;
            newPos.y =pos.y;
            field.ball.setPositinon(newPos);

        } 

        if ((topLeftCorner.y > ballPos.y) && (bottomRightCorner.y < ballPos.y)) {
            moveBallY(yDir, ballPos, field.ball);
        } else if ((topLeftCorner.y <= ballPos.y) || (bottomRightCorner.y >= ballPos.y)) {
            switchYPos(yDir, field);
        }
    }

    private void switchYPos(yDirection yDir, Field field) {

        // Stutsa
        switch (yDir) {
        case UP :
            field.ball.setYDirection(yDirection.DOWN);

            break;

        case DOWN :
            field.ball.setYDirection(yDirection.UP);

            break;
        }
    }

    private void switchXPos(xDirection xDir, Field field) {
        // Stutsa
        switch (xDir) {
        case RIGHT :
            field.ball.setXDirection(xDirection.LEFT);
            break;
        case LEFT :
            field.ball.setXDirection(xDirection.RIGHT);

            break;
        }
    }

    private void moveBallY(yDirection yDir, Position pos, Ball ball) {
        Position newPos = new Position();
        switch (yDir) {
        case DOWN :
            newPos.x = pos.x;
            newPos.y = pos.y+1;
            ball.setPositinon(newPos);

            break;

        case UP :
            newPos.x = pos.x ;
            newPos.y = pos.y+1;
            ball.setPositinon(newPos);
        }
    }

    private void moveBallX(xDirection xDir, Position pos, Ball ball) {
        Position newPos = new Position();
        switch (xDir) {
        case LEFT :
           
            newPos.x = pos.x -1;
            newPos.y = pos.y;
            ball.setPositinon(newPos);

            break;

        case RIGHT :
            newPos.x = pos.x +1;
            newPos.y = pos.y;
            ball.setPositinon(newPos);
        }
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
