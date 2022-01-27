package dev.alexjf.javaParticles;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class JavaParticlesMain {
    static ParticlePanel particlePanel = new ParticlePanel();
    static JButton testButton = new JButton("test");

    public JPanel createControlPanel(){
        JPanel returnPanel = new JPanel(new FlowLayout());

        return returnPanel;
    }

    public static void main(String[] args) {
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

        JSlider resolutionSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, particlePanel.simulationResolution);
        resolutionSlider.setMajorTickSpacing(1);
        resolutionSlider.setMinorTickSpacing(1);
        resolutionSlider.setPaintTicks(true);
        resolutionSlider.setPaintLabels(true);
        resolutionSlider.addChangeListener(l -> {
            particlePanel.simulationResolutionUpdated = resolutionSlider.getValue();
        });
        controlPanel.add(resolutionSlider);
        
        testButton.setActionCommand("add particle");
        testButton.addActionListener(e -> particlePanel.addParticle());
        controlPanel.add(testButton);

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
        frame.setVisible(true);
        frame.setSize(400, 400);

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
