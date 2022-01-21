package dev.alexjf.midiParticles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ParticlePanel extends JPanel implements ActionListener{
    public static int PARTICLE_NUMBER = 50;
    public ArrayList<MidiParticle> midiParticles = new ArrayList<>();
    public Random random = new Random();
    public MidiParticle midiParticle;

    public void start(){
        int[] velocityArray = new int[3];
        velocityArray[0] = -1;
        velocityArray[1] = 0;
        velocityArray[2] = 1;
        for(int i = 0; i < PARTICLE_NUMBER; i++){
            midiParticles.add(new MidiParticle(random.nextInt(getWidth()), random.nextInt(getHeight()), velocityArray[random.nextInt(3)], velocityArray[random.nextInt(3)], 50, 50, new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256))));
        }

        Timer timer = new Timer(1, this);
        timer.setInitialDelay(1000);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void paintComponent(Graphics graphics){
        //graphics.setColor(Color.WHITE);
        //graphics.fillRect(0, 0, getWidth(), getHeight());
        for(int i = 0; i < midiParticles.size(); i++){
            midiParticle = midiParticles.get(i);
            midiParticle.updatePosition();
            if(midiParticle.xCoordinate >= getWidth() || midiParticle.xCoordinate <= 0){
                midiParticle.xVelocity = midiParticle.xVelocity * -1;
            }
            if(midiParticle.yCoordinate >= getHeight() || midiParticle.yCoordinate <= 0){
                midiParticle.yVelocity = midiParticle.yVelocity * -1;
            }
            graphics.setColor(midiParticle.color);
            graphics.fillRect((int)midiParticle.xCoordinate, (int)midiParticle.yCoordinate, 2, 2);
        }
    }
}
