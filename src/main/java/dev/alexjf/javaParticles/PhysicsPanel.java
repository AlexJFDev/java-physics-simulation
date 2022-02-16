package dev.alexjf.javaParticles;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class PhysicsPanel extends JPanel implements ActionListener{
    private PhysicsManager physicsManager;
    public boolean safeToPaint;

    public void start(){
        //Timer timer = new Timer(1, this);
        //timer.setInitialDelay(1000);
        //timer.start();
    }

    public void setPhysicsManager(PhysicsManager physicsManagerArgument){
        physicsManager = physicsManagerArgument;
        safeToPaint = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public void paintComponent(Graphics graphics){
        if(safeToPaint){
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, getWidth(), getHeight());
            for(JavaParticle javaParticle: physicsManager.javaParticleArrayList){
                graphics.setColor(javaParticle.color);
                graphics.fillRect((int)javaParticle.coordinate.xCoordinate, (int)javaParticle.coordinate.yCoordinate, 2, 2);
            }
        }
    }
}