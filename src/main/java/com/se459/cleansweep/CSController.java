package com.se459.cleansweep;

import java.util.List;
import java.util.Stack;
import com.se459.floor.interfaces.IFloorLayout;
import com.se459.floor.interfaces.ISurfaces;

public class CSController implements Runnable {

    // Sensors -- Surfaces, Navigation, Layout
    // Movement -- forward, backward, right, left; *path management
    // Power Management -- capacity and calculations
    // Dirt Management -- capacity and return to charge stations

    // Path Taken by CS
    private Stack<ISurfaces> path;
    private ISurfaces currentLocation;
    List<List<ISurfaces>> floorLayout;

    private int count = 0;

    public CSController(IFloorLayout fl) {

        path = new Stack<ISurfaces>();
        this.floorLayout = fl.getFloor();

        // get current location and push to the path obj
        // first tile should ideally be charge station location
        currentLocation = floorLayout.get(0).get(0);
        startupCS();
    }

    @Override
    public void run() {

        // this will probably be the biggest method and will call the other methods in
        // the private methods depending on the conditions

        // need some sort of mechanism that keeps track of power levels so it can invoke
        // returnToCharge()

        // need some sort of mechanism that keeps track of dirt levels so it can return
        // to be emptied

        // need some sort of mechanism that monitors both power levels & dirt to ensure
        // the vacuum returns when needed

        // System.out.println("You made it to the controller's run()");

        for (int j = 0; j < floorLayout.size(); j++) {
            List<ISurfaces> row = floorLayout.get(j);
            for (int i = 0; i < row.size(); i++) {
                ISurfaces ft = row.get(i);
                count++;
                if (ft != currentLocation) {
                    traverse(ft);
                    System.out.printf("Moving to and cleaning tile at (%d,%d) \n", ft.getXCoord(), ft.getYCoord());
                    while (currentLocation.hasDirt()) {
                        clean();
                    }
                }
            }
        }
        returnToChargeStation();
        shutdownCS();
        System.out.printf("Total tiles cleaned: %d\n", count);

    }

    private void traverse(ISurfaces next) {
        // retrieve current location of the device and add it to the path before moving
        // on
        path.push(currentLocation);

        // update the power level of the vacuum
        CleanSweepSingleton.updateCurrentCharge(costToTraverse(currentLocation, next));

        // set location of vacuum to the next tile so it 'moves'
        currentLocation = next;
    }

    private double getSurfaceCost(ISurfaces tile) {
        if (tile == null) throw new IllegalArgumentException();

        String surface = tile.getSurfaceType();
        double cost = 0.0;

        if (surface.equals("bare")) {
            cost = 1.0;
        } else if (surface.equals("lowCarpet")) {
            cost = 2.0;
        } else if (surface.equals("highCarpet")) {
            cost = 3.0;
        }

        return cost;
    }


    private double costToTraverse(ISurfaces current, ISurfaces next) {
        // energy cost to travel is avg of the cost of each tile
        return (getSurfaceCost(current) + getSurfaceCost(next)) / 2;
    }

    private void clean() {
        System.out.printf("Cleaning floor tile (%d, %d)\n", currentLocation.getXCoord(), currentLocation.getYCoord());

        currentLocation.updateCleanStatus();

        CleanSweepSingleton.updateCurrentCharge(1);
        CleanSweepSingleton.updateCurrentDirt();

        System.out.printf("[Current power level: %f] [Current dirt level: %d]\n",
                CleanSweepSingleton.getCurrentCharge(), CleanSweepSingleton.getCurrentDirt());

        if (currentLocation.hasDirt())
            System.out.println("Oops, need to clean again!");
        else
            System.out.println("All clean and moving on!");

    }

    private void returnToChargeStation() { // currently working on moving backwards on the path taken
        ISurfaces ft = path.pop();

        while (!ft.hasChargeStation()) {
            // power update to move to previous tile
            CleanSweepSingleton.updateCurrentCharge(costToTraverse(currentLocation, ft));

            // update current location to previous tile
            currentLocation = ft;

            System.out.printf("Returning to charge from location at (%d, %d)\n", ft.getXCoord(), ft.getYCoord());
            ft = path.pop();
        }

        System.out.printf("At (%d, %d) to charge \n", ft.getXCoord(), ft.getYCoord());
    }

    private void shutdownCS() { // should be invoked when CS cannot move in any direction
        CleanSweepSingleton.shutdown();
    }

    private void startupCS() { // not sure if needed but can expand/delete later
        CleanSweepSingleton.startup();
    }

}
