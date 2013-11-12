
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
public class Ball extends MovableObject {
    private double radius;

    public Ball(double radius,Position pos) {
        this.radius = radius;
        super.setXDirection(xDirection.LEFT);
        super.setYDirection(yDirection.NONE);
        super.setPositinon(pos);
    }

    public double getArea() {
        return (Math.PI * Math.pow(radius, 2));
    }

    public double getRadius() {
        return radius;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
