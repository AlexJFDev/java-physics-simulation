package dev.alexjf.javaParticles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.Timer;

public class ParticlePanel extends JPanel implements ActionListener{
    public int particleNumber = 1;
    public int simulationResolution = 10;
    public int simulationResolutionUpdated = simulationResolution;
    public ArrayList<JavaParticle> javaParticleArrayList = new ArrayList<>();
    public final Random random = new Random();
    public JavaParticle javaParticle;
    public HashMap<Coordinate, JavaSprite> coordinateHashmap;
    public int callCount = 0;

    public void start(){
        for(int i = 0; i < particleNumber; i++){
            addParticle();
        }
        //javaParticleArrayList.add(new JavaParticle(new Coordinate(200, 200), 1, 1, new Color(0, 255, 0)));
        //javaParticleArrayList.add(new JavaParticle(new Coordinate(100, 100), -1, -1, new Color(255, 0, 255)));
        repaint();
        Timer timer = new Timer(0, this);
        timer.setInitialDelay(1000);
        timer.start();
    }

    public void addParticle(){
        javaParticleArrayList.add(new JavaParticle(new Coordinate(random.nextInt(getWidth()), random.nextInt(getHeight())), random.nextInt(360), random.nextInt(10), new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256))));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void paintComponent(Graphics graphics){
        callCount++;
        simulationResolution = simulationResolutionUpdated;
        coordinateHashmap = new HashMap<Coordinate, JavaSprite>();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, getWidth(), getHeight());
        for(JavaParticle javaParticle: javaParticleArrayList){
            javaParticle.updatePosition(simulationResolution, getWidth(), getHeight());
            graphics.setColor(javaParticle.color);
            graphics.fillRect((int)javaParticle.coordinate.xCoordinate, (int)javaParticle.coordinate.yCoordinate, 2, 2);
            if(coordinateHashmap.containsKey(javaParticle.coordinate)){
                Toolkit.getDefaultToolkit().beep();
                JavaSprite javaSprite = coordinateHashmap.get(javaParticle.coordinate);
                javaSprite.collide(javaParticle);
            } else {
                coordinateHashmap.put(javaParticle.coordinate, javaParticle);
            }
        }
    }
}