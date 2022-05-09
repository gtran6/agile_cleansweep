package com.se459.cleansweep;

public class Battery {

    private final double maxCharge = 250;
    private double currentCharge;

    public Battery() {
        currentCharge = maxCharge;
    }

    public void update(double diff) {
        currentCharge -= diff;
        double percent = (currentCharge / maxCharge) * 100;
        System.out.println("Current battery levels are: " + percent + "%");
    }

    public double currentCharge() {
        return currentCharge;
    }

    public void charge() {
        currentCharge = maxCharge;
        System.out.printf("Fully Charged at %d\n", currentCharge);
    }

    public boolean isEmpty() {
        return currentCharge == 0;
    }

}
