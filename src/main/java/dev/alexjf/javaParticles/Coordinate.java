package dev.alexjf.javaParticles;

import java.io.Serializable;

public class Coordinate implements Serializable{
    public double xCoordinate;
    public double yCoordinate;

    public Coordinate(double xCoordinate2, double yCoordinate2){
        xCoordinate = xCoordinate2;
        yCoordinate = yCoordinate2;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null) return false;
        if (this.getClass() != object.getClass()) return false;
        Coordinate coordinate2 = (Coordinate) object;
        return (int) xCoordinate == (int) coordinate2.xCoordinate && (int) yCoordinate == (int) coordinate2.yCoordinate;
    }

    @Override
    public int hashCode(){
        return ((Double) xCoordinate).hashCode() * 31 + ((Double) yCoordinate).hashCode();
    }

    public double getXCoordinate(){
        return xCoordinate;
    }

    public double getYCoordinate(){
        return yCoordinate;
    }

    public void setXCoordinate(double xCoordinateArgument){
        xCoordinate = xCoordinateArgument;
    }

    public void setYCoordinate(double yCoordinateArgument){
        yCoordinate = yCoordinateArgument;
    }
}
