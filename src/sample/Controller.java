package sample;

import java.awt.*;
import java.awt.image.SampleModel;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import javax.swing.*;

public class Controller {

    @FXML
    public ResourceBundle resources;

    @FXML
    public URL location;

    @FXML
    public AnchorPane Form;

    @FXML
    public GridPane FormGridPane;

    @FXML
    public Label FormLabel1;

    @FXML
    public Label FormLabelInfo1;

    @FXML
    public Label FormLabel2;

    @FXML
    public Label FormLabelInfo2;

    @FXML
    public Label FormLabel3;

    @FXML
    public Label FormLabelInfo3;

    @FXML
    public CheckBox FormCeckBox1;

    @FXML
    public CheckBox FormCeckBox2;

    @FXML
    public CheckBox FormCeckBox3;

    @FXML
    public TextField FormTextEdit;

    @FXML
    public Button FormButtom;

    @FXML
    public TextField FormTextEditNotification;

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

        FormButtom.setOnAction(event -> { ActiveCheckbox();});
    }



    private void ActiveCheckbox(){

        if (FormCeckBox1.isSelected()) {
            if (FormLabel1.getText() == "00:00:00"){
                FormCeckBox1.setSelected(false);
                GoTimer(FormLabel1, FormLabelInfo1);
            }
        } else if (FormCeckBox2.isSelected()) {
            if (FormLabel2.getText() == "00:00:00") {
                FormCeckBox2.setSelected(false);
                GoTimer(FormLabel2, FormLabelInfo2);
            }
        } else if (FormCeckBox3.isSelected()) {
            if (FormLabel3.getText() == "00:00:00"){
                FormCeckBox3.setSelected(false);
                GoTimer(FormLabel3, FormLabelInfo3);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Данный таймер уже видет отсчет","Error",JOptionPane.ERROR_MESSAGE);
           /* int RestartBool = JOptionPane.showConfirmDialog(null,"Хотите обнулить его ?","Сброс таймера",JOptionPane.YES_NO_OPTION);

            if (RestartBool == 0) {
                if (FormCeckBox1.isSelected()) {FormLabel1.setText("00:00:00");}
                if (FormCeckBox1.isSelected()) {FormLabel2.setText("00:00:00");}
                if (FormCeckBox1.isSelected()) {FormLabel3.setText("00:00:00");}
                ActiveCheckbox();
            }
            */
        }
    }

    private void GoTimer(Label labelTimer, Label labelInfo) {

        labelInfo.setText(FormTextEditNotification.getText());

        Timer     TimerLab = new Timer();
        TimerLabel TimerTaskLab =  new TimerLabel(Integer.parseInt(FormTextEdit.getText()) * 60, labelTimer, labelInfo);

        TimerLab.schedule(TimerTaskLab,1);

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

class TimerLabel extends  TimerTask {

    Integer TimeInt;
    Label LabelTimer,LabelInfo;


    public TimerLabel(Integer Timer, Label LabelTimer, Label LabelInf){
        TimeInt = Timer;
        this.LabelTimer = LabelTimer;
        LabelInfo = LabelInf;
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
                Platform.runLater(() ->{
                    Integer H = TimeInt / 3600;
                    Integer M = (TimeInt % 3600) / 60;
                    Integer S = (TimeInt % 3600) % 60;

                    LabelTimer.setText(H + ":" + M + ":" + S);

                    TimeInt = TimeInt - 1;
                });
            Thread.sleep(1000);
        }

        Platform.runLater(()->{
            LabelTimer.setText("00:00:00");
            JOptionPane.showMessageDialog(null, LabelInfo.getText(),LabelInfo.getText(),JOptionPane.ERROR_MESSAGE);
        });
    }
}
