package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane Form;

    @FXML
    private GridPane FormGridPane;

    @FXML
    private Label FormLabel1;

    @FXML
    private Label FormLabelInfo1;

    @FXML
    private Label FormLabel2;

    @FXML
    private Label FormLabelInfo2;

    @FXML
    private Label FormLabel3;

    @FXML
    private Label FormLabelInfo3;

    @FXML
    private CheckBox FormCeckBox1;

    @FXML
    private CheckBox FormCeckBox2;

    @FXML
    private CheckBox FormCeckBox3;

    @FXML
    private TextField FormTextEdit;

    @FXML
    private Button FormButtom;

    @FXML
    private TextField FormTextEditNotification;

    @FXML
    void initialize() {

        falseTimer();

        FormCeckBox1.setOnAction(event -> {
            if (FormCeckBox1.isSelected()) {
                FormCeckBox2.setSelected(false);
                FormCeckBox3.setSelected(false);
            }
        });

        FormCeckBox2.setOnAction(event -> {
            if (FormCeckBox2.isSelected()) {
                FormCeckBox1.setSelected(false);
                FormCeckBox3.setSelected(false);
            }
        });

        FormCeckBox3.setOnAction(event -> {
            if (FormCeckBox3.isSelected()) {
                FormCeckBox1.setSelected(false);
                FormCeckBox2.setSelected(false);
            }
        });

        FormButtom.setOnAction(event -> {
            if (FormCeckBox1.isSelected()) {
                FormCeckBox1.setSelected(false);
                GoTimer(FormLabel1, FormLabelInfo1);
            } else if (FormCeckBox2.isSelected()) {
                FormCeckBox2.setSelected(false);
                GoTimer(FormLabel2, FormLabelInfo2);
            } else if (FormCeckBox3.isSelected()) {
                FormCeckBox3.setSelected(false);
                GoTimer(FormLabel3, FormLabelInfo3);
            }
        });
    }

    private void GoTimer(Label labelTimer, Label labelInfo) {

        labelInfo.setText(FormTextEditNotification.getText());
        Thread TimerStart = new Timer(Integer.parseInt(FormTextEdit.getText()) * 60, labelTimer);
        TimerStart.start();
    }

    private void falseTimer() {
        FormLabel1.setText("00:00:00");
        FormLabel2.setText("00:00:00");
        FormLabel3.setText("00:00:00");
    }

    public void RefreshTimer(Label TimerLab, String TimeStr){
        TimerLab.setText(TimeStr);
    }
}

class Timer extends Thread{

    Integer TimeInt;
    Label LabelTimer;

    public Timer(Integer Timer, Label LabelTimer){
        TimeInt = Timer;
        this.LabelTimer = LabelTimer;
    }

    @Override
    public void run() {
        try {
            StrtTimer();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void StrtTimer() throws InterruptedException {


        while (TimeInt > 0) {

            Integer H = TimeInt / 3600;
            Integer M = (TimeInt % 3600) / 60;
            Integer S = (TimeInt % 3600) % 60;

            LabelTimer.setText(H + ":" + M + ":" + S);

            //DefClass.RefreshTimer(LabelTimer,H + ":" + M + ":" + S);

            TimeInt = TimeInt - 1;

            Thread.sleep(1000);
        }


    }

}