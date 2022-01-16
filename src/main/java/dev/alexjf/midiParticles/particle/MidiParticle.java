package dev.alexjf.midiParticles.particle;

import java.awt.Color;
import java.util.Random;

import javax.swing.JPanel;

import dev.alexjf.midiParticles.MidiParticlesMain;

public class MidiParticle extends JPanel{
    
    public int x_pos;
    public int y_pos;
    public int x_velocity;
    public int y_velocity;
    public int instrumentInt;
    public int noteInt;
    public Color color;

    static Random random = new Random();

    public MidiParticle(){
        x_pos = random.nextInt(MidiParticlesMain.MAX_X);
        x_pos = random.nextInt(MidiParticlesMain.MAX_Y);
        x_velocity = 1;
        y_velocity = 1;
        instrumentInt = random.nextInt(128);
        noteInt = random.nextInt(128);
        color = new Color(instrumentInt * 2, noteInt * 2, 100);
    }

    public void update(){
        x_pos = x_pos + x_velocity;
        y_pos = y_pos + y_velocity;
    }
}
