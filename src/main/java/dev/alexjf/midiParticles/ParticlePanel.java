package dev.alexjf.midiParticles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ParticlePanel extends JPanel implements ActionListener{
    public static int PARTICLE_NUMBER = 20;
    public ArrayList<MidiParticle> midiParticles = new ArrayList<>();
    public Random random = new Random();
    public MidiParticle midiParticle;
    public HashMap<Coordinate, MidiParticle> midiParticlesHashmap;
    public boolean shouldRepaint = true;

    public void start(){
        /*for(int i = 0; i < PARTICLE_NUMBER; i++){
            midiParticles.add(new MidiParticle(new Coordinate(random.nextInt(getWidth()), random.nextInt(getHeight())), random.nextInt(30) - 15, random.nextInt(30) - 15, random.nextInt(128), random.nextInt(128), new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256))));
        }*/
        midiParticles.add(new MidiParticle(new Coordinate(0, 0), 1, 1, random.nextInt(128), random.nextInt(128), new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256))));
        midiParticles.add(new MidiParticle(new Coordinate(100, 100), -1, -1, random.nextInt(128), random.nextInt(128), new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256))));

        Timer timer = new Timer(1, this);
        timer.setInitialDelay(1000);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(shouldRepaint) repaint();
    }

    @Override
    public void paintComponent(Graphics graphics){
        midiParticlesHashmap = new HashMap<Coordinate, MidiParticle>();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        for(int i = 0; i < midiParticles.size(); i++){
            midiParticle = midiParticles.get(i);
            midiParticle.updatePosition();
            if(midiParticle.coordinate.xCoordinate >= getWidth() || midiParticle.coordinate.xCoordinate <= 0){
                midiParticle.xVelocity = midiParticle.xVelocity * -1;
            }
            if(midiParticle.coordinate.yCoordinate >= getHeight() || midiParticle.coordinate.yCoordinate <= 0){
                midiParticle.yVelocity = midiParticle.yVelocity * -1;
            }
            graphics.setColor(midiParticle.color);
            graphics.fillRect((int)midiParticle.coordinate.xCoordinate, (int)midiParticle.coordinate.yCoordinate, 2, 2);
            if(midiParticlesHashmap.get(midiParticle.coordinate) == null){
                midiParticlesHashmap.put(midiParticle.coordinate, midiParticle);
            } else {
                MidiParticle midiParticle2 = midiParticlesHashmap.get(midiParticle.coordinate);
                int xVelocity2 = midiParticle.xVelocity;
                int yVelocity2 = midiParticle.yVelocity;
                midiParticle.xVelocity = midiParticle2.xVelocity;
                midiParticle.yVelocity = midiParticle2.yVelocity;
                midiParticle2.xVelocity = xVelocity2;
                midiParticle2.yVelocity = yVelocity2;
                midiParticles.add(new MidiParticle(new Coordinate(midiParticle.coordinate.xCoordinate, midiParticle.coordinate.yCoordinate), random.nextInt(30) - 15, random.nextInt(30) - 15, random.nextInt(128), random.nextInt(128), new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256))));
                //midiParticle.play(midiParticle2.instrumentInt, midiParticle2.noteInt);
                //System.out.println("collision");
                //System.out.println(midiParticles.size());
            }
        }
        if(midiParticles.size() >= 100000){
            shouldRepaint = false;
            System.out.println(midiParticles.size());
        }
    }
}