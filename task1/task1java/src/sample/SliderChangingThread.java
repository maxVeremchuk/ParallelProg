package sample;

import javafx.scene.control.Slider;

import javax.swing.*;

public class SliderChangingThread extends  Thread {
    private int prior = 5;
    private Slider slider;
    private int changer;
    SliderChangingThread(Slider slider, int changer){
        this.slider = slider;
        this.changer = changer;
    }
    public void incPrior() {
        if(prior < 10) {
            prior++;
            System.out.println(prior);
            setPriority(prior);
        }
    }
    public void decPrior() {
        if(prior > 1) {
            prior--;
            System.out.println(prior);
            setPriority(prior);
        }
    }
    @Override
    public void run() {
        while(true) {
            synchronized (slider) {
                slider.setValue(slider.getValue() + changer);
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
