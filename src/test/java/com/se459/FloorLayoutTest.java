package com.se459;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import com.se459.floor.FloorLayout;
import com.se459.floor.interfaces.IFloorLayout;

import org.junit.Test;

public class FloorLayoutTest {
    
    @Test
    public void testNewFloorLayoutSuccess() {
        IFloorLayout testLayout = new FloorLayout("floor_layout.json");
        IFloorLayout testLayout2 = new FloorLayout("test_single_tile.json");
        String testPrintResult = null;
        String testPrintResult2 = null;

        testPrintResult = testLayout.toString();
        testPrintResult2 = testLayout2.toString();

        assertNotNull(testPrintResult);
        assertNotNull(testPrintResult2);
    }

    @Test
    public void testNewFloorLayoutWithIncorrectJSONFormatResultsFailure() {
        IFloorLayout testLayout1 = new FloorLayout("test_bad_format.json");
        IFloorLayout testLayout2 = new FloorLayout("test_bad_keys.json");
        IFloorLayout testLayout3 = new FloorLayout("test_bad_surfaces.json");
        String result1 = null;
        String result2 = null;
        String result3 = null;

        try {
            result1 = testLayout1.toString();
            result2 = testLayout2.toString();
            result3 = testLayout3.toString();

            fail();
        } catch (NullPointerException e) {
            assertNull(result1);
            assertNull(result2);
            assertNull(result3);
        }
    }



}
