package Controller;

import Function.ReadAndWrite;
import Model.StudentForSave;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by jerry on 16-11-21.
 */
public class Score_PieGraph_Controller implements Initializable {

    @FXML
    private PieChart piechart;

    int failamount = 0;
    int passamount = 0;
    int middleamount = 0;
    int niceamount = 0;
    int wonderfulamount = 0;

    int total = 0;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        ArrayList<StudentForSave> list = new ArrayList<>();
        try {
            list = ReadAndWrite.readStudentScore();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        total = list.size();
        for (StudentForSave studentForSave : list) {

            if (Integer.parseInt(studentForSave.getStu_Score()) < 60) {
                failamount++;
            }
            if (Integer.parseInt(studentForSave.getStu_Score()) >= 60 && Integer.parseInt(studentForSave.getStu_Score()) < 70) {
                passamount++;
            }
            if (Integer.parseInt(studentForSave.getStu_Score()) >= 70 && Integer.parseInt(studentForSave.getStu_Score()) < 80) {
                middleamount++;
            }
            if (Integer.parseInt(studentForSave.getStu_Score()) >= 80 && Integer.parseInt(studentForSave.getStu_Score()) < 90) {
                niceamount++;
            }
            if (Integer.parseInt(studentForSave.getStu_Score()) >= 90 && Integer.parseInt(studentForSave.getStu_Score()) < 100) {
                wonderfulamount++;
            }

        }

        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                        new PieChart.Data("<60分\n "+failamount*100/total+"%", failamount),
                        new PieChart.Data("60-69分\n "+passamount*100/total+"%", passamount),
                        new PieChart.Data("70-79分\n "+middleamount*100/total+"%", middleamount),
                        new PieChart.Data("80-89分\n "+niceamount*100/total+"%", niceamount),
                        new PieChart.Data("≥90分\n "+wonderfulamount*100/total+"%", wonderfulamount));

        piechart.setData(pieChartData);

        Label caption = new Label("");
        caption.setTextFill(Color.DARKORANGE);
        caption.setStyle("-fx-font: 24 arial;");

        for (PieChart.Data data : piechart.getData()) {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
                    e -> {
                        caption.setTranslateX(e.getSceneX());
                        caption.setTranslateY(e.getSceneY());
                        caption.setText(String.valueOf(data.getPieValue()) + "%");
                    });
        }
    }
}
