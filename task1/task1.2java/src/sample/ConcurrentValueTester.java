package sample;

import javafx.scene.control.Slider;

public class ConcurrentValueTester {
    private Slider slider;
    private Thread threadDecreaser;
    private Thread threadIncreaser;
    private volatile int semaphore;
    private volatile boolean decIsRunning;
    private volatile boolean incIsRunning;

    public ConcurrentValueTester(Slider s) {
        slider = s;
        semaphore = 0;
        decIsRunning = true;
        incIsRunning = true;

    }
    public void setSemaphore(int value){
        semaphore = value;

    }
    public int getSemaphore(){
        return semaphore;
    }
    public void setPriorityToInc(int priority){
        threadIncreaser.setPriority(priority);
    }
    public void setPriorityToDec(int priority){
        threadDecreaser.setPriority(priority);
    }
    public void setdecIsRunning(boolean value){
        decIsRunning = value;
    }
    public void setincIsRunning(boolean value){
        incIsRunning = value;
    }
    public void startDec(){
        threadDecreaser = new Thread(){
            @Override
            public void run()  {
                while(decIsRunning) {
                    slider.setValue(10);
                }
            }
        };
        threadDecreaser.start();
    }
    public void startInc(){
        threadIncreaser = new Thread(){
            @Override
            public void run()  {
                while(incIsRunning) {
                    slider.setValue(90);

                }

            }
        };
        threadIncreaser.start();
    }

}