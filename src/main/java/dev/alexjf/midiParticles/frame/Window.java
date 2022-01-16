package dev.alexjf.midiParticles.frame;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.border.Border;

import dev.alexjf.midiParticles.MidiParticlesMain;
import dev.alexjf.midiParticles.particle.MidiParticle;

public class Window {
    public Window(){
        JFrame frame = new JFrame();
        Field field = new Field();
        UserInterface userInterface = new UserInterface();
        Border fieldBorder = BorderFactory.createLineBorder(Color.BLACK);
        MidiParticle particle = new MidiParticle();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        
        field.setBackground(Color.WHITE);
        field.setBorder(fieldBorder);
        field.setBounds(10, 10, MidiParticlesMain.MAX_X, MidiParticlesMain.MAX_Y);

        frame.add(field);
        frame.add(userInterface);

        frame.setVisible(true);
    }
}
