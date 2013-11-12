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
public class MovableObject extends Object {
    
    private int speed;
    private Direction direction;

    
    public int getSpeed(){
        return speed;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }
    
        
    public void setXDirection(xDirection xDir){  
        this.direction.setXDirection(xDir);        
    }
    
     public void setYDirection(yDirection yDir){  
        this.direction.setYDirection(yDir);         
    }
     
    public xDirection getXDirection(){
        return this.direction.getXDirection();
    }
     public yDirection getYDirection(){
        return this.direction.getYDirection();
    }   
    
    

       
    
}
