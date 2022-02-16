package dev.alexjf.javaParticles;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.Timer;

public class PhysicsManager implements ActionListener {
    public PhysicsPanel physicsPanel;
    public boolean pauseStatus;
    public int callCount = 0;

    public ArrayList<JavaParticle> javaParticleArrayList = new ArrayList<>();
    private double simulationResolution = 10;
    private HashMap<Integer, HashMap<Integer, JavaSprite>> coordinateHashmap;

    private final int particleNumber = 0;
    private final Random random = new Random();

    public PhysicsManager(PhysicsPanel physicsPanelArgument){
        physicsPanel = physicsPanelArgument;
    }

    public void start(){
        for(int i = 0; i < particleNumber; i++){
            addParticle();
        }
        Timer timer = new Timer(1, this);
        timer.setInitialDelay(1000);
        timer.start();
        javaParticleArrayList.add(new JavaParticle(new Coordinate(100, 200), 0, 1, new Color(255, 0, 0)));
        javaParticleArrayList.add(new JavaParticle(new Coordinate(200, 200), 0, 1, new Color(0, 255, 0)));
        javaParticleArrayList.add(new JavaParticle(new Coordinate(300, 200), 0, 1, new Color(0, 0, 255)));
    }

    public void addParticle(){
        javaParticleArrayList.add(new JavaParticle(new Coordinate(random.nextInt(physicsPanel.getWidth()), random.nextInt(physicsPanel.getHeight())), random.nextInt(360), random.nextInt(100) / 10, new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256))));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        callCount++;
        if(!pauseStatus){
            coordinateHashmap = new HashMap<Integer, HashMap<Integer, JavaSprite>>();
            for(JavaParticle javaParticle: javaParticleArrayList){
                javaParticle.updatePosition(simulationResolution, physicsPanel.getWidth(), physicsPanel.getHeight());
                if(!coordinateHashmap.containsKey((int) javaParticle.coordinate.xCoordinate)){
                    coordinateHashmap.put((int) javaParticle.coordinate.xCoordinate, new HashMap<Integer, JavaSprite>());
                    coordinateHashmap.get((int) javaParticle.coordinate.xCoordinate).put((int) javaParticle.coordinate.yCoordinate, javaParticle);
                } else if(coordinateHashmap.get((int) javaParticle.coordinate.xCoordinate).containsKey((int) javaParticle.coordinate.yCoordinate)){
                    Toolkit.getDefaultToolkit().beep();
                    JavaSprite javaSprite = coordinateHashmap.get((int) javaParticle.coordinate.xCoordinate).get((int) javaParticle.coordinate.yCoordinate);
                    javaSprite.collide(javaParticle);
                } else {
                    coordinateHashmap.get((int) javaParticle.coordinate.xCoordinate).put((int) javaParticle.coordinate.yCoordinate, javaParticle);
                }
            }
            physicsPanel.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
        }
    }
    
    public void setSimulationResolution(double simulationResolutionArgument){
        simulationResolution = simulationResolutionArgument;
    }
}
