package com.se459.floor;

import com.se459.floor.interfaces.ISurfaces;
import com.se459.floor.interfaces.ISurfacesFactory;

public class SurfacesFactory implements ISurfacesFactory {

    @Override
    public ISurfaces createBareSurface(int dirtAmt, boolean hasChargeStation, int xCoord, int yCoord,
                                        String northEdge, String eastEdge, String southEdge, String westEdge) {
        
        return new FloorTile("bare", dirtAmt, hasChargeStation, xCoord, yCoord, 
                             northEdge, eastEdge, southEdge, westEdge);
    }

    @Override
    public ISurfaces createLowCarpet(int dirtAmt, boolean hasChargeStation, int xCoord, int yCoord,
                                        String northEdge, String eastEdge, String southEdge, String westEdge) {
        
        return new FloorTile("lowCarpet", dirtAmt, hasChargeStation, xCoord, yCoord, 
                             northEdge, eastEdge, southEdge, westEdge);
    }

    @Override
    public ISurfaces createHighCarpet(int dirtAmt, boolean hasChargeStation, int xCoord, int yCoord,
                                        String northEdge, String eastEdge, String southEdge, String westEdge) {
        
        return new FloorTile("highCarpet", dirtAmt, hasChargeStation, xCoord, yCoord, 
                             northEdge, eastEdge, southEdge, westEdge);
    }

}
