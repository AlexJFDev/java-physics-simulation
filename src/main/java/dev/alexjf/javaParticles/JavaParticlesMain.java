package dev.alexjf.javaParticles;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public interface JavaParticlesMain {
    static ParticlePanel particlePanel = new ParticlePanel();
    static JButton addParticleButton = new JButton("Add Particle");
    static JCheckBox pauseCheckBox = new JCheckBox("Pause");

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName()
            );
        } 
        catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (InstantiationException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        JFrame frame = new JFrame("Java Particle Simulation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container pane = frame.getContentPane();
        pane.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        particlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        constraints.weightx = 0.8;
        constraints.weighty = 0.8;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        pane.add(particlePanel, constraints);

        JPanel controlPanel = new JPanel(new FlowLayout());
        controlPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        constraints.weightx = 0.8;
        constraints.weighty = 0.2;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 1;

        JSlider resolutionSlider = new JSlider(JSlider.HORIZONTAL, 0, 50, 10);
        resolutionSlider.setMajorTickSpacing(10);
        resolutionSlider.setMinorTickSpacing(5);
        resolutionSlider.setPaintTicks(true);
        resolutionSlider.setPaintLabels(true);
        resolutionSlider.addChangeListener(l -> {
            particlePanel.setSimulationResolution(Math.pow(10.0, (double) resolutionSlider.getValue() / 10));
            resolutionSlider.getValue();
        });
        controlPanel.add(resolutionSlider);
        
        addParticleButton.addActionListener(e -> particlePanel.addParticle());
        controlPanel.add(addParticleButton);

        pauseCheckBox.addActionListener(e -> particlePanel.pauseStatus = pauseCheckBox.isSelected());
        controlPanel.add(pauseCheckBox);

        pane.add(controlPanel, constraints);

        JPanel infoPanel = new JPanel(new FlowLayout());
        infoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        constraints.weightx = 0.2;
        constraints.weighty = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridheight = 2;
        pane.add(infoPanel, constraints);

        frame.pack();
        frame.validate();
        frame.setSize(500, 500);
        frame.setVisible(true);

        while(particlePanel.callCount < 2){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        particlePanel.start();
    }
}
