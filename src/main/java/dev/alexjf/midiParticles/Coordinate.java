package dev.alexjf.midiParticles;

public class Coordinate {
    public int xCoordinate;
    public int yCoordinate;

    public Coordinate(int xCoordinate2, int yCoordinate2){
        xCoordinate = xCoordinate2;
        yCoordinate = yCoordinate2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (this.getClass() != o.getClass()) return false;
        Coordinate coordinate2 = (Coordinate) o;
        return xCoordinate == coordinate2.xCoordinate && yCoordinate == coordinate2.yCoordinate;
    }

    @Override
    public int hashCode(){
        return xCoordinate * 31 + yCoordinate;
    }
}
