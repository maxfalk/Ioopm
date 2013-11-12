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
    
    public void moveBall(Field field){
        Position topLeftCorner = field.getTopLeftCorner();
        Position bottomRightCorner = field.getBottomRightCorner();
        xDirection xDir = field.ball.getXDirection();
        yDirection yDir = field.ball.getYDirection();
        Position ballPos = field.ball.getPosition();
        Player player1 = field.player1;
        Player player2 = field.player2;
        
        if(topLeftCorner.x < ballPos.x && bottomRightCorner.x > ballPos.x){
            moveBallX(xDir, ballPos);
        }else if(player1.contains(ballPos) == true){
            //träffade spelare1s bräde
            switchXPos(xDir, field);
            moveBallX(xDir, ballPos);
        }else if(player2.contains(ballPos) == true){
            //träffade spelare2s bräde
            switchXPos(xDir, field);
            moveBallX(xDir, ballPos);
        }else if(topLeftCorner.x >= ballPos.x){
            //KOlla om man träffar en spelar annrs poäng?
            player1.addPoint(1);
            Position pos = field.getMiddle();
            ballPos.x = pos.x;
            ballPos.y = pos.y;
            
        }else if(bottomRightCorner.x >= ballPos.x){
            //KOlla om man träffar en spelar annrs poäng?
            player2.addPoint(1);
            Position pos = field.getMiddle();
            ballPos.x = pos.x;
            ballPos.y = pos.y;
        }
        
        if(topLeftCorner.y > ballPos.y && bottomRightCorner.y < ballPos.y){
            moveBallY(yDir, ballPos);
        }else if(topLeftCorner.y <= ballPos.y || bottomRightCorner.y >= ballPos.y){
            switchYPos(yDir, field);
        }
        
        
        
    }
    
    private void switchYPos(yDirection yDir, Field field) {
        //Stutsa
        switch (yDir){
            case UP:
                field.ball.setYDirection(yDirection.DOWN);
                break;
            case DOWN:
                field.ball.setYDirection(yDirection.UP);
                break;
        }
    }
    
    private void switchXPos(xDirection xDir, Field field) {
        //Stutsa
        switch (xDir){
            case RIGHT:
                field.ball.setXDirection(xDirection.LEFT);
                break;
            case LEFT:
                field.ball.setXDirection(xDirection.RIGHT);
                break;
        }
    }
    
    private void moveBallY(yDirection yDir, Position pos) {
        switch (yDir) {
            case DOWN:
                pos.y -= 1;
                break;
            case UP:
                pos.y += 1;
                
        }
        
    }
    
    private void moveBallX(xDirection xDir, Position pos) {
        switch (xDir) {
            case LEFT:
                pos.x -= 1;
                break;
            case RIGHT:
                pos.x += 1;
                
        }
    }
    
}
