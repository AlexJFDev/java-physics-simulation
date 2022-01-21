package dev.alexjf.midiParticles;

import java.awt.Color;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;
import javax.sound.midi.Sequencer;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.InvalidMidiDataException;

public class MidiParticle {
    public Coordinate coordinate;
    public int xVelocity;
    public int yVelocity;
    public int instrumentInt;
    public int noteInt;
    public Color color;

    public MidiParticle(Coordinate coordinate2, int xVelocity2, int yVelocity2, int instrumentInt2, int noteInt2, Color color2){
        coordinate = coordinate2;
        xVelocity = xVelocity2;
        yVelocity = yVelocity2;
        instrumentInt = instrumentInt2;
        noteInt = noteInt2;
        color = color2;
    }

    public void updatePosition(){
       coordinate.xCoordinate = coordinate.xCoordinate + xVelocity;
       coordinate.yCoordinate = coordinate.yCoordinate + yVelocity;
    }

    public void play(int instrumentInt2, int noteInt2){
        try {
            Sequencer sequencer = MidiSystem.getSequencer();
            sequencer.open();
            Sequence sequence = new Sequence(Sequence.PPQ, 4);
            Track track = sequence.createTrack();

            ShortMessage a = new ShortMessage();
            a.setMessage(144, 1, (noteInt + noteInt2)/2, 100);
            MidiEvent noteOn = new MidiEvent(a, 1);
            track.add(noteOn);

            ShortMessage b = new ShortMessage();
            b.setMessage(192, 1, (instrumentInt + instrumentInt2)/2, 100);
            MidiEvent noteOff = new MidiEvent(b, 1);
            track.add(noteOff);

            sequencer.setSequence(sequence);
            sequencer.start();
        } catch(MidiUnavailableException ex){
            System.out.println(ex);
        } catch(InvalidMidiDataException ex){
            System.out.println(ex);
        }
    }
}
