
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
public class Player extends MovableObject {
    private int points = 0;
    private int length = 20;
    private int width = 10;

    Player(Position pos){
        super.setXDirection(xDirection.NONE);
        super.setYDirection(yDirection.NONE);
        super.setPositinon(pos);
    }
    
    
    public int getArea() {
        return length * width;
    }

    public int getWidth(){
        return width;
    }
    public int getLength(){
        return length;
    }
    public Position getTopLeftCorner() {
        Position playerPos  = this.getPosition();
        Position newPos = new Position();
        int      halfWidth  = this.width / 2;
        int      halflength = this.length / 2;
        newPos.x = playerPos.x - halfWidth;
        newPos.y = playerPos.y - halflength; 
        return newPos;
    }
    
    public boolean contains(Position pos) {
        //Fixa kordinater för höger måste vara -- inte ++.
        Position playerPos  = getTopLeftCorner();
        int      left       = playerPos.x;
        int      top        = playerPos.y;
        int      right      = (playerPos.x + this.width);
        int      bottom     = (playerPos.y + this.length);
        //System.out.println("Rect Right: " + right);
        //System.out.println("Rect Left: " + left);
        //        System.out.println("Rect top: " + top);
        //System.out.println("Rect bottom: " + bottom);
        return (pos.x >= left) && (pos.x <= right) && (pos.y >= top) && (pos.y <= bottom);
    }

    public void addPoint(int p) {
        this.points += p;
    }

    public void removePoint(int p) {
        this.points -= p;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
