package dev.alexjf.midiParticles;

import javax.swing.JFrame;
import javax.swing.Timer;

public class MidiParticlesMain{
    
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Midi Particle Simulation");
        ParticlePanel particlePanel = new ParticlePanel();

        frame.add(particlePanel);
        particlePanel.setVisible(true);
    }
}
