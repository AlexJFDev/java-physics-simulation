package dev.alexjf.javaParticles;

import java.awt.Color;

public class JavaParticle {
    public Coordinate coordinate;
    public double xVelocity;
    public double yVelocity;

    public Color color;

    public JavaParticle(Coordinate coordinate2, int xVelocity2, int yVelocity2, Color color2){
        coordinate = coordinate2;
        xVelocity = xVelocity2;
        yVelocity = yVelocity2;
        color = color2;
    }

    public void updatePosition(int resolution){
        coordinate.xCoordinate = coordinate.xCoordinate + xVelocity/resolution;
        coordinate.yCoordinate = coordinate.yCoordinate + yVelocity/resolution;
    }
}
