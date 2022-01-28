package dev.alexjf.javaParticles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.lang.Math;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ParticlePanel extends JPanel implements ActionListener{
    public int particleNumber = 00;
    public int simulationResolution = 1;
    public int simulationResolutionUpdated = 1;
    public ArrayList<JavaParticle> javaParticles = new ArrayList<>();
    public final Random random = new Random();
    public JavaParticle javaParticle;
    public HashMap<Coordinate, JavaParticle> javaParticlesHashmap;
    public int callCount = 0;
    public Timer timer;

    public void start(){
        javaParticles.add(new JavaParticle(new Coordinate(0, 0), 1, 1, new Color(0, 0, 255), false));
        javaParticles.add(new JavaParticle(new Coordinate(100, 100), -1, -1, new Color(255, 0, 0), false));
        for(int i = 0; i < particleNumber; i++){
            addParticle(false);
        }
        repaint();
        timer = new Timer(100, this);
        timer.setInitialDelay(1000);
        timer.start();
    }

    public void addParticle(boolean isSick){
        if(isSick){
            javaParticles.add(new JavaParticle(new Coordinate(random.nextInt(getWidth()), random.nextInt(getHeight())), random.nextInt(30) - 15, random.nextInt(30) - 15, new Color(255, 0, 0), true));
        } else {
            javaParticles.add(new JavaParticle(new Coordinate(random.nextInt(getWidth()), random.nextInt(getHeight())), random.nextInt(30) - 15, random.nextInt(30) - 15, new Color(0, 0, 255), false));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println((int)Math.pow(1, simulationResolutionUpdated));
        
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
                if(javaParticle.isSick || javaParticle2.isSick){
                    javaParticle.isSick = true;
                    javaParticle.color = new Color(255, 0, 0);
                    javaParticle2.isSick = true;
                    javaParticle2.color = new Color(255, 0, 0);
                }
            }
        }
    }
}