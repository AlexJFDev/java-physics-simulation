package dev.alexjf.javaParticles;

import java.awt.Color;

public class JavaParticle {
    public Coordinate coordinate;
    public double xVelocity;
    public double yVelocity;

    public Color color;

    public JavaParticle(Coordinate coordinate2, double xVelocity2, double yVelocity2, Color color2){
        coordinate = coordinate2;
        xVelocity = xVelocity2;
        yVelocity = yVelocity2;
        color = color2;
    }

    public void updatePosition(int resolution, int width, int height){
        coordinate.xCoordinate = coordinate.xCoordinate + xVelocity/resolution;
        coordinate.yCoordinate = coordinate.yCoordinate + yVelocity/resolution;

        if(coordinate.xCoordinate >= width || coordinate.xCoordinate <= 0){
            xVelocity = xVelocity * -1;
            if(coordinate.xCoordinate > width){
                coordinate.xCoordinate = width;
            }
        }
        if(coordinate.yCoordinate >= height || coordinate.yCoordinate <= 0){
            yVelocity = yVelocity * -1;
            if(coordinate.yCoordinate > height){
                coordinate.yCoordinate = height;
            }
        }
    }
}
