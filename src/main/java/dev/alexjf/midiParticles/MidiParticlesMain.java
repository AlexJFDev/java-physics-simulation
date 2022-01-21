package dev.alexjf.midiParticles;

import javax.swing.JFrame;

public class MidiParticlesMain{
    
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Midi Particle Simulation");
        ParticlePanel particlePanel = new ParticlePanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.add(particlePanel);
        frame.setVisible(true);
        particlePanel.start();
    }
}
