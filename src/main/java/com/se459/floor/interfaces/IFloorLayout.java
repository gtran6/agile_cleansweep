package com.se459.floor.interfaces;

import java.util.List;

public interface IFloorLayout {
    
    List<List<ISurfaces>> getFloor();

    void printDetailLayout();    
}
