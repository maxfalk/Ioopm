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
    
    public Ball(double radius){
        this.radius = radius;
    }
    
    public double getArea(){
        return (Math.PI*Math.pow(radius,2));
        
    }
   
    
}
