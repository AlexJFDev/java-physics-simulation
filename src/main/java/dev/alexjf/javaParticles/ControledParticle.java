package dev.alexjf.javaParticles;

import java.awt.Color;

public class ControledParticle {
    public Coordinate coordinate = new Coordinate(100, 100);
    private double xVelocity;
    private double yVelocity;
    public double velocity = 1;
    public double angle = 0;
    public double sliderAngle = 0;

    public Color color = new Color(0, 0, 0);

    public void updatePosition(double resolution, int width, int height){
        angle = sliderAngle;

        updateVelocity();

        coordinate.xCoordinate = coordinate.xCoordinate + xVelocity/resolution;
        coordinate.yCoordinate = coordinate.yCoordinate + yVelocity/resolution;

        if(coordinate.xCoordinate > width){
            sliderAngle = (90 - (angle - 90)) % 360;
            xVelocity = xVelocity * -1;
            coordinate.xCoordinate = width;
        } else if (coordinate.xCoordinate < 0){
            sliderAngle = (90 - (angle - 90)) % 360;
            xVelocity = xVelocity * -1;
            coordinate.xCoordinate = 0;
        }
        if(coordinate.yCoordinate > height){
            sliderAngle = (0 - (angle - 0)) % 360;
            yVelocity = yVelocity * -1;
            coordinate.yCoordinate = height;
        } else if(coordinate.yCoordinate > height || coordinate.yCoordinate < 0){
            sliderAngle = (0 - (angle - 0)) % 360;
            yVelocity = yVelocity * -1;
            coordinate.yCoordinate = 0;
        }
    }

    public void updateVelocity(){
        xVelocity = velocity * Math.cos(Math.toRadians(angle));
        yVelocity = velocity * Math.sin(Math.toRadians(angle));
    }
}
