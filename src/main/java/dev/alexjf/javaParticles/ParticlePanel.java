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
    public int simulationResolution = 10;
    public int simulationResolutionUpdated = 1;
    public ArrayList<JavaParticle> javaParticleArrayList = new ArrayList<>();
    public final Random random = new Random();
    public JavaParticle javaParticle;
    public HashMap<Coordinate, JavaParticle> coordinateHashmap;
    public int callCount = 0;

    public void start(){
        for(int i = 0; i < particleNumber; i++){
            addParticle();
        }
        repaint();
        Timer timer = new Timer(0, this);
        timer.setInitialDelay(1000);
        timer.start();
    }

    public void addParticle(){
        javaParticleArrayList.add(new JavaParticle(new Coordinate(random.nextInt(getWidth()), random.nextInt(getHeight())), random.nextInt(30) - 15, random.nextInt(30) - 15, new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256))));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void paintComponent(Graphics graphics){
        callCount++;
        simulationResolution = (int) Math.pow(10.0, (double) simulationResolutionUpdated / 10);
        coordinateHashmap = new HashMap<Coordinate, JavaParticle>();
        for(JavaParticle javaParticle: javaParticleArrayList){
            javaParticle.updatePosition(simulationResolution, getWidth(), getHeight());
            //System.out.println((int)(javaParticle.coordinate.xCoordinate) + ", " + (int)(javaParticle.coordinate.yCoordinate));
            //System.out.println(javaParticle.coordinate.hashCode());
            graphics.setColor(javaParticle.color);
            graphics.fillRect((int)javaParticle.coordinate.xCoordinate, (int)javaParticle.coordinate.yCoordinate, 2, 2);
            if(coordinateHashmap.containsKey(javaParticle.coordinate)){
                JavaParticle javaParticle2 = coordinateHashmap.get(javaParticle.coordinate);
                double xVelocity2 = javaParticle.xVelocity;
                double yVelocity2 = javaParticle.yVelocity;
                javaParticle.xVelocity = javaParticle2.xVelocity;
                javaParticle.yVelocity = javaParticle2.yVelocity;
                javaParticle2.xVelocity = xVelocity2;
                javaParticle2.yVelocity = yVelocity2;
            } else {
                coordinateHashmap.put(javaParticle.coordinate, javaParticle);
            }
        }
        //System.out.println("");
    }
}