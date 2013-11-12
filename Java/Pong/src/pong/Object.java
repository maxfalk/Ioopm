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
public class Object {
    
    private Position position;

    public Position getPosition(){
        Position positionCopy = new Position();
        positionCopy.x = position.x;
        positionCopy.y = position.y;
        
        return positionCopy;
    }
    
    public void setPositinon(Position newPosition){
        position.x = newPosition.x;
        position.y = newPosition.y;
    
    }
    
}
