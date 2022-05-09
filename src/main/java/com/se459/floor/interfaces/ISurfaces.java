package com.se459.floor.interfaces;


public interface ISurfaces {

    String getSurfaceType();

    boolean hasDirt();

    boolean hasChargeStation();

    void updateCleanStatus();

    int getXCoord();

    int getYCoord();

    String getNorthEdge();

    String getEastEdge();

    String getSouthEdge();

    String getWestEdge();

}
