package dev.alexjf.midiParticles;
import java.awt.Color;

public class MidiParticle {
    public double xCoordinate;
    public double yCoordinate;
    public double xVelocity;
    public double yVelocity; 
    public int instrumentInt;
    public int noteInt;
    public Color color;

    public MidiParticle(double xCoordinate2, double yCoordinate2, double xVelocity2, double yVelocity2, int instrumentInt2, int noteInt2, Color color2){
        xCoordinate = xCoordinate2;
        yCoordinate = yCoordinate2;
        xVelocity = xVelocity2;
        yVelocity = yVelocity2;
        instrumentInt = instrumentInt2;
        noteInt = noteInt2;
        color = color2;
    }

    public void updatePosition(){
       xCoordinate = xCoordinate + xVelocity;
       yCoordinate = yCoordinate + yVelocity;
   }
}
