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
    public int particleNumber = 100;
    public int simulationResolution = 1;
    public int simulationResolutionUpdated = 1;
    public ArrayList<MidiParticle> midiParticles = new ArrayList<>();
    public final Random random = new Random();
    public MidiParticle midiParticle;
    public HashMap<Coordinate, MidiParticle> midiParticlesHashmap;
    public int callCount = 0;

    public void start(){
        for(int i = 0; i < particleNumber; i++){
            addParticle();
        }
        repaint();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Timer timer = new Timer(10, this);
        timer.setInitialDelay(0000);
        timer.start();
    }

    public void addParticle(){
        midiParticles.add(new MidiParticle(new Coordinate(random.nextInt(getWidth()), random.nextInt(getHeight())), random.nextInt(30) - 15, random.nextInt(30) - 15, new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256))));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void paintComponent(Graphics graphics){
        callCount++;
        simulationResolution = simulationResolutionUpdated;
        midiParticlesHashmap = new HashMap<Coordinate, MidiParticle>();
        for(int i = 0; i < midiParticles.size(); i++){
            midiParticle = midiParticles.get(i);
            midiParticle.updatePosition(simulationResolution);
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
                double xVelocity2 = midiParticle.xVelocity;
                double yVelocity2 = midiParticle.yVelocity;
                midiParticle.xVelocity = midiParticle2.xVelocity;
                midiParticle.yVelocity = midiParticle2.yVelocity;
                midiParticle2.xVelocity = xVelocity2;
                midiParticle2.yVelocity = yVelocity2;
            }
        }
    }
}