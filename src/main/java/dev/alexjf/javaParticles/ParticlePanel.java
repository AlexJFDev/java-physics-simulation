package dev.alexjf.javaParticles;

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
    public int particleNumber = 1000;
    public int simulationResolution = 1;
    public int simulationResolutionUpdated = 1;
    public ArrayList<JavaParticle> javaParticles = new ArrayList<>();
    public final Random random = new Random();
    public JavaParticle javaParticle;
    public HashMap<Coordinate, JavaParticle> javaParticlesHashmap;
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
        timer.start();
    }

    public void addParticle(){
        javaParticles.add(new JavaParticle(new Coordinate(random.nextInt(getWidth()), random.nextInt(getHeight())), random.nextInt(30) - 15, random.nextInt(30) - 15, new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256))));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void paintComponent(Graphics graphics){
        callCount++;
        simulationResolution = simulationResolutionUpdated;
        javaParticlesHashmap = new HashMap<Coordinate, JavaParticle>();
        for(JavaParticle javaParticle: javaParticles){
            javaParticle.updatePosition(simulationResolution);
            if(javaParticle.coordinate.xCoordinate >= getWidth() || javaParticle.coordinate.xCoordinate <= 0){
                javaParticle.xVelocity = javaParticle.xVelocity * -1;
            }
            if(javaParticle.coordinate.yCoordinate >= getHeight() || javaParticle.coordinate.yCoordinate <= 0){
                javaParticle.yVelocity = javaParticle.yVelocity * -1;
            }
            graphics.setColor(javaParticle.color);
            graphics.fillRect((int)javaParticle.coordinate.xCoordinate, (int)javaParticle.coordinate.yCoordinate, 2, 2);
            if(javaParticlesHashmap.get(javaParticle.coordinate) == null){
                javaParticlesHashmap.put(javaParticle.coordinate, javaParticle);
            } else {
                JavaParticle javaParticle2 = javaParticlesHashmap.get(javaParticle.coordinate);
                double xVelocity2 = javaParticle.xVelocity;
                double yVelocity2 = javaParticle.yVelocity;
                javaParticle.xVelocity = javaParticle2.xVelocity;
                javaParticle.yVelocity = javaParticle2.yVelocity;
                javaParticle2.xVelocity = xVelocity2;
                javaParticle2.yVelocity = yVelocity2;
            }
        }
    }
}