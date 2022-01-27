package dev.alexjf.midiParticles;

public class Coordinate {
    public double xCoordinate;
    public double yCoordinate;

    public Coordinate(double xCoordinate2, double yCoordinate2){
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
        return ((Double) xCoordinate).hashCode() + ((Double) yCoordinate).hashCode();
    }
}
