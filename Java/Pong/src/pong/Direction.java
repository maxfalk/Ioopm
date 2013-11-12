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
public class Direction {
    
    private yDirection yDir;
    private xDirection xDir;
    
    public xDirection getXDirection(){
        return this.xDir;
    }
    public void setXDirection(xDirection xDir){
        this.xDir = xDir;
    }
    
    public yDirection getYDirection(){
        return this.yDir;
    }
    
    public void setYDirection(yDirection yDir){
        this.yDir = yDir;
    }
    
    
    
    
    
    
}
