package dev.alexjf.javaParticles;

import java.awt.Color;
import java.lang.Math;

public class JavaParticle extends JavaSprite{
    public Coordinate coordinate;
    private double xVelocity;
    private double yVelocity;
    private double velocity;
    private double angle;

    public Color color;

    public JavaParticle(Coordinate coordinateArgument, double angleArgument, double velocityArgument, Color colorArgument){
        coordinate = coordinateArgument;
        angle = angleArgument;
        velocity = velocityArgument;

        color = colorArgument;

        updateVelocity();
    }

    public void updatePosition(double resolution, int width, int height){
        coordinate.xCoordinate = coordinate.xCoordinate + xVelocity/resolution;
        coordinate.yCoordinate = coordinate.yCoordinate + yVelocity/resolution;

        if(coordinate.xCoordinate > width){
            angle = (90 - (angle - 90)) % 360;
            xVelocity = xVelocity * -1;
            xVelocity = velocity * Math.cos(Math.toRadians(angle));
            coordinate.xCoordinate = width;
        } else if (coordinate.xCoordinate < 0){
            angle = (90 - (angle - 90)) % 360;
            xVelocity = xVelocity * -1;
            xVelocity = velocity * Math.cos(Math.toRadians(angle));
            coordinate.xCoordinate = 0;
        }
        if(coordinate.yCoordinate > height){
            angle = (0 - (angle - 0)) % 360;
            yVelocity = yVelocity * -1;
            yVelocity = velocity * Math.sin(Math.toRadians(angle));
            coordinate.yCoordinate = height;
        } else if(coordinate.yCoordinate < 0){
            angle = (0 - (angle - 0)) % 360;
            yVelocity = yVelocity * -1;
            yVelocity = velocity * Math.sin(Math.toRadians(angle));
            coordinate.yCoordinate = 0;
        }
    }

    @Override
    public void collide(JavaSprite javaSprite){
        if(javaSprite.getClass() == this.getClass()){
            JavaParticle javaParticle2 = (JavaParticle) javaSprite;
            double velocity2 = velocity;
            double angle2 = angle;
            velocity = javaParticle2.velocity;
            angle = javaParticle2.angle;
            javaParticle2.velocity = velocity2;
            javaParticle2.angle = angle2;
            updateVelocity();
            javaParticle2.updateVelocity();
        }
    }

    public void updateVelocity(){
        xVelocity = velocity * Math.cos(Math.toRadians(angle));
        yVelocity = velocity * Math.sin(Math.toRadians(angle));
    }
}
