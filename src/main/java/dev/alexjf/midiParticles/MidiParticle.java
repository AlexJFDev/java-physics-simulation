package dev.alexjf.midiParticles;
import java.awt.Color;

public class MidiParticle {
    public int xCoordinate;
    public int yCoordinate;
    public int xVelocity;
    public int yVelocity; 
    public int instrumentInt;
    public int noteInt;
    public Color color;

    public MidiParticle(int xCoordinate2, int yCoordinate2, int xVelocity2, int yVelocity2, int instrumentInt2, int noteInt2, Color color2){
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
