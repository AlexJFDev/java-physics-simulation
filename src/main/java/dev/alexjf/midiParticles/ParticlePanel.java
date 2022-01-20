package dev.alexjf.midiParticles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ParticlePanel extends JPanel implements ActionListener{
    public static int PARTICLE_NUMBER = 1;
    public MidiParticle midiParticle = new MidiParticle(0, 0, 1, 1, 50, 50, new Color(100, 100, 100));

    public ParticlePanel(){
        Timer timer = new Timer(1000, this);
        timer.setInitialDelay(1000);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("action");
        repaint();
    }

    public void paintComponent(Graphics graphics){
        midiParticle.updatePosition();

    }
}
