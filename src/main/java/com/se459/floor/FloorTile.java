package com.se459.floor;


import com.se459.floor.interfaces.ISurfaces;

public class FloorTile implements ISurfaces {

    private final String surfaceType;
    private int dirtAmt; // cannot show this since the CS has no sensor to detect dirt amount. Only if clean/unclean.
    private boolean isDirty;
    private boolean hasChargeStation;
    private final int xCoord;
    private final int yCoord;
    private final String northEdge;
    private final String eastEdge;
    private final String southEdge;
    private final String westEdge;

    public FloorTile (String surfaceName, int dirtAmt, boolean hasChargeStation, int x, int y,
                        String northEdge, String eastEdge, String southEdge, String westEdge) {
        this.surfaceType = surfaceName;
        this.dirtAmt = dirtAmt;
        this.isDirty = true;
        this.hasChargeStation = hasChargeStation;
        this.xCoord = x;
        this.yCoord = y;
        this.northEdge = northEdge;
        this.eastEdge = eastEdge;
        this.southEdge = southEdge;
        this.westEdge = westEdge;

        checkIfDirty();
    }

    private void checkIfDirty() {
        if (dirtAmt <= 0) {
            this.dirtAmt = 0;
            this.isDirty = false;
        } else {
            this.isDirty = true;
        }
    }

    @Override
    public String getSurfaceType() {return surfaceType;}

    @Override
    public boolean hasDirt() {return isDirty;}

    @Override
    public boolean hasChargeStation() { return hasChargeStation; }

    @Override
    public void updateCleanStatus() {
        dirtAmt--;
        checkIfDirty();
    }

    @Override
    public int getXCoord() { return xCoord; }

    @Override
    public int getYCoord() { return yCoord; }

    @Override
    public String getNorthEdge() { return northEdge; }

    @Override
    public String getEastEdge() { return eastEdge; }

    @Override
    public String getSouthEdge() { return southEdge; }

    @Override
    public String getWestEdge() { return westEdge; }

    @Override
    public String toString() {
        return String.format("{X:%d, Y:%d, TYPE:%s, DIRTY:%b, STATION:%b, NORTH: %s, EAST: %s, SOUTH: %s, WEST: %s}\n",
            xCoord, yCoord, surfaceType, isDirty, hasChargeStation, northEdge, eastEdge, southEdge, westEdge);
    }

}
