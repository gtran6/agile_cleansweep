package com.se459.floor.interfaces;

public interface ISurfacesFactory {
    
    ISurfaces createBareSurface(int dirtAmt, boolean hasChargeStation, int xCoord, int yCoord,
                                String northEdge, String eastEdge, String southEdge, String westEdge);

    ISurfaces createLowCarpet(int dirtAmt, boolean hasChargeStation, int xCoord, int yCoord,
                                String northEdge, String eastEdge, String southEdge, String westEdge);

    ISurfaces createHighCarpet(int dirtAmt, boolean hasChargeStation, int xCoord, int yCoord,
                                String northEdge, String eastEdge, String southEdge, String westEdge);

}
