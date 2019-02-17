package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;

public class Controller {
    @FXML
    private Slider slider;
    @FXML
    private Label label;

    @FXML
    private Button incBtn;
    @FXML
    private Button decBtn;

    ConcurrentValueTester cvt;
    @FXML
    public void initialize() {
        cvt = new ConcurrentValueTester(slider);
    }
    @FXML
    private void startFirst(ActionEvent event) {
        cvt.setdecIsRunning(true);
        int semaphore = cvt.getSemaphore();
        if(semaphore == 1){
            label.setText("first is still running");
        }
        else if(semaphore == 2){
            label.setText("second is still running");
        }
        else{
            decBtn.setDisable(false);
            incBtn.setDisable(true);
            cvt.startDec();
            cvt.setSemaphore(1);
            cvt.setPriorityToDec(1);
            label.setText("first is running");
        }
    }
    @FXML
    private void startSecond(ActionEvent event) {
        cvt.setincIsRunning(true);
        int semaphore = cvt.getSemaphore();
        if(semaphore == 1){
            label.setText("first is still running");
        }
        else if(semaphore == 2){
            label.setText("second is still running");
        }
        else{
            incBtn.setDisable(false);
            decBtn.setDisable(true);
            cvt.startInc();
            cvt.setSemaphore(2);
            cvt.setPriorityToInc(10);
            label.setText("second is running");
        }
    }
    @FXML
    private void stopFirst(ActionEvent event) {
        incBtn.setDisable(false);
        cvt.setSemaphore(0);
        cvt.setdecIsRunning(false);
        label.setText("stop");
    }
    @FXML
    private void stopSecond(ActionEvent event) {
        decBtn.setDisable(false);
        cvt.setSemaphore(0);
        cvt.setincIsRunning(false);
        label.setText("stop");
    }

}
