package com.se459.cleansweep;

//import com.se459.floor.FloorTile;

public class CleanSweepSingleton {

    private static CleanSweepSingleton instance = new CleanSweepSingleton();

    private boolean shutdown;
    private DirtBin dirtBin;
    private Battery battery;

    private CleanSweepSingleton() {
        dirtBin = new DirtBin();
        battery = new Battery();
    }

    public static double getCurrentCharge() {
        return instance.getCharge();
    }

    private double getCharge() {
        return battery.currentCharge();
    }

    public static int getCurrentDirt() {
        return instance.getDirt();
    }

    private int getDirt() {
        return dirtBin.getDirtLevel();
    }

    public static boolean isShutdown() {
        return instance.isSD();
    }

    private boolean isSD() {
        return shutdown;
    }

    public static void shutdown() {
        instance.sd();
    }

    public static void startup() {
        instance.su();
    }

    public static void charge() {
        instance.chargeBattery();
    }

    private void chargeBattery() {
        battery.charge();
    }

    private void su() {
        shutdown = false;
        System.out.println("CleanSweep is powered up and ready to go");
    }

    private void sd() {
        shutdown = true;
        System.out.println("CleanSweep is shutting down. Goodbye.");
    }

    public static void updateCurrentCharge(double reduction) {
        instance.updateCC(reduction);
    }

    private void updateCC(double reduction) {
        battery.update(reduction);
    }

    public static void updateCurrentDirt() {
        instance.updateCD();
    }

    private void updateCD() {
        dirtBin.update();
    }
}
