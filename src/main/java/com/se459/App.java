package com.se459;


import com.se459.cleansweep.CSController;
import com.se459.floor.FloorLayout;
import com.se459.floor.interfaces.IFloorLayout;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        IFloorLayout floor = new FloorLayout("floor_layout.json");

        System.out.println("\n");
        //floor.printDetailLayout(); // printing floor in detail
        System.out.println(floor.toString()); // printing floor
        System.out.println("\n");

        CSController csController = new CSController(floor);
        csController.run();
    }
}
