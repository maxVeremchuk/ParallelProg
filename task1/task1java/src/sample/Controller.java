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
    private Button decreaserAddPriorityBut;
    @FXML
    private Button decreaserSubstractPriorityBut;
    @FXML
    private Button increaserAddPriorityBut;
    @FXML
    private Button increaserSubstractPriorityBut;

    ConcurrentValueTester cvt;
    @FXML
    public void initialize() {
        cvt = new ConcurrentValueTester(slider);
        cvt.startTesting();
        /*slider.valueProperty().addListener((observable, oldValue, newValue) -> {

        });*/
    }
    @FXML
    private void handledecreaserAddAction(ActionEvent event) {
        cvt.incDecPrior();
    }
    @FXML
    private void handledecreaserSubstractAction(ActionEvent event) {
        cvt.decDecPrior();
    }
    @FXML
    private void handleincreaserAddAction(ActionEvent event) {
        cvt.incIncPrior();
    }
    @FXML
    private void handleincreaserSubstractAction(ActionEvent event) {
        cvt.decIncPrior();
    }


}
