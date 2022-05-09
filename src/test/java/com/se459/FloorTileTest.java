package com.se459;

import static org.junit.Assert.assertEquals;

import com.se459.floor.FloorTile;
import com.se459.floor.interfaces.ISurfaces;

import org.junit.Test;

public class FloorTileTest {

    @Test
    public void testGetMethodsSuccess() {
        ISurfaces testTile = new FloorTile("bare", 1, false, 0, 0, "obstacle", "open", "stairs", "unknown");
        String testType;
        boolean testDirty1;
        boolean testDirty2;
        boolean testHasStation;
        int testXCoord;
        int testYCoord;
        String testNorthEdge;
        String testEastEdge;
        String testSouthEdge;
        String testWestEdge;

        testType = testTile.getSurfaceType();
        testDirty1 = testTile.hasDirt();
        testHasStation = testTile.hasChargeStation();
        testXCoord = testTile.getXCoord();
        testYCoord = testTile.getYCoord();
        testNorthEdge = testTile.getNorthEdge();
        testEastEdge = testTile.getEastEdge();
        testSouthEdge = testTile.getSouthEdge();
        testWestEdge = testTile.getWestEdge();

        testTile.updateCleanStatus();
        testDirty2 = testTile.hasDirt();

        assertEquals("bare", testType);
        assertEquals(true, testDirty1);
        assertEquals(false, testHasStation);
        assertEquals(false, testDirty2);
        assertEquals(0, testXCoord);
        assertEquals(0, testYCoord);
        assertEquals("obstacle", testNorthEdge);
        assertEquals("open", testEastEdge);
        assertEquals("stairs", testSouthEdge);
        assertEquals("unknown", testWestEdge);
    }
    
}
