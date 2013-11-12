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
public class Player extends MovableObject{
    
    private int length;
    private int width;
    private int points = 0;
    
    public int getArea(){
        return length*width;
    }
    
    public boolean contains(Position pos){
        
        Position playerPos = this.getPosition();
        int halfWidth = this.width/2;
        int halflength = this.length/2;  
        int left = (playerPos.x-halflength);
        int top = (playerPos.y-halfWidth);
        int right = (playerPos.x+halflength);
        int bottom = (playerPos.y+halfWidth);
         
       return pos.x >= left && pos.x <= right && pos.y <= top && pos.y >= bottom;
    }
    
    public void addPoint(int p){
        this.points += p;
    
    }
    
    public void removePoint(int p){
    
        this.points -= p;
    }
    
}
