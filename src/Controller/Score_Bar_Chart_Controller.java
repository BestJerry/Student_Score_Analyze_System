package Controller;

import Function.ReadAndWrite;
import Model.StudentForSave;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by jerry on 16-11-21.
 */
public class Score_Bar_Chart_Controller implements Initializable {

    final static String one = "<60分";
    final static String two = "60-69分";
    final static String three = "70-79分";
    final static String four = "80-89分";
    final static String five = "≥90分";

    @FXML
    private BarChart barchart;

    @FXML
    private NumberAxis numberAxis;

    @FXML
    private CategoryAxis categoryAxis;

    int failamount = 0;
    int passamount = 0;
    int middleamount = 0;
    int niceamount = 0;
    int wonderfulamount = 0;

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


        XYChart.Series series = new XYChart.Series();
        series.setName(Open_Sco_List_Controller.classnametra.substring(0,12)+"-"+
                Open_Sco_List_Controller.coursenametra);
        series.getData().add(new XYChart.Data(one,failamount));
        series.getData().add(new XYChart.Data(two,passamount));
        series.getData().add(new XYChart.Data(three,middleamount));
        series.getData().add(new XYChart.Data(four,niceamount));
        series.getData().add(new XYChart.Data(five,wonderfulamount));
        barchart.getData().addAll(series);
    }
}
