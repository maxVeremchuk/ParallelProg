package sample;

import javafx.scene.control.Slider;


public class ConcurrentValueTester {
    private Slider slider;
    private SliderChangingThread threadDecreaser;
    private SliderChangingThread threadIncreaser;

    public ConcurrentValueTester(Slider s) {
        slider = s;
    }

    public void incDecPrior() {
        threadDecreaser.incPrior();
    }
    public void decDecPrior() {
        threadDecreaser.decPrior();
    }
    public void incIncPrior() {
        threadIncreaser.incPrior();
    }
    public void decIncPrior() {
        threadIncreaser.decPrior();
    }
    public void startTesting() {
        threadDecreaser = new SliderChangingThread(slider, -1);
        threadIncreaser = new SliderChangingThread(slider, 1);
        threadDecreaser.setDaemon(true);
        threadIncreaser.setDaemon(true);
        threadDecreaser.start();
        threadIncreaser.start();
    }
}