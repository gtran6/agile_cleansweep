package com.se459.cleansweep;

public class DirtBin {

    private final int maxCapacity = 50;
    private int currentDirtLevel = 0;

    public int getDirtLevel() {
        return currentDirtLevel;
    }

    public void update() {
        currentDirtLevel++;
    }

    public boolean notFull() {
        return currentDirtLevel < maxCapacity;
    }

    public String CDLPercentToString() {
        double cdl = 100 * (currentDirtLevel / maxCapacity);
        return String.valueOf(cdl);
    }

    public void empty() {
        currentDirtLevel = 0;
        System.out.printf("Dirt Bin is empty at %d units of dirt\n", currentDirtLevel);
    }
}
