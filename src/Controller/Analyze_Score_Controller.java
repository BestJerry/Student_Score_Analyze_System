package Controller;

import Function.ReadAndWrite;
import Model.StudentForSave;
import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by jerry on 16-11-20.
 */
public class Analyze_Score_Controller implements Initializable {

    @FXML
    private Label max_min_ave;
    @FXML
    private Label fail;
    @FXML
    private Label pass;
    @FXML
    private Label middle;
    @FXML
    private Label nice;
    @FXML
    private Label wonderful;

    int max;
    int min;
    double ave;

    double failamount = 0;
    double passamount = 0;
    double middleamount = 0;
    double niceamount = 0;
    double wonderfulamount = 0;

    ArrayList<StudentForSave> studentForSaveslist = new ArrayList<>();


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            studentForSaveslist = ReadAndWrite.readStudentScore();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        calculate();

        max_min_ave.setText("最高分：" + max + "分，最低分：" + min + "分，平均分：" + ave + "分");
        fail.setText("不及格（分数＜60）：" + (int) failamount + "人，占" + String.format("%.2f", failamount*100.0/studentForSaveslist.size()) + "%");
        pass.setText("及格（60<=分数<70）：" + (int) passamount + "人，占" + String.format ("%.2f",passamount * 100.0 / studentForSaveslist.size()) + "%");
        middle.setText("中等（70<=分数<80）：" + (int) middleamount + "人，占" + String.format("%.2f", middleamount * 100.0 / studentForSaveslist.size()) + "%");
        nice.setText("良好（80<=分数<90）：" + (int) niceamount + "人，占" + String.format("%.2f",niceamount * 100.0 / studentForSaveslist.size())+ "%");
        wonderful.setText("及格（90<=分数<100）：" + (int) wonderfulamount + "人，占" + String.format("%.2f",wonderfulamount * 100.0 / studentForSaveslist.size()) + "%");
    }

    public void calculate() {

        max = Integer.parseInt(studentForSaveslist.get(0).getStu_Score());
        min = Integer.parseInt(studentForSaveslist.get(0).getStu_Score());
        ave = 0;
        double sum = 0;
        for (StudentForSave studentForSave : studentForSaveslist) {
            if (Integer.parseInt(studentForSave.getStu_Score()) > max) {
                max = Integer.parseInt(studentForSave.getStu_Score());
            }
            if (Integer.parseInt(studentForSave.getStu_Score()) < min) {
                min = Integer.parseInt(studentForSave.getStu_Score());
            }
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
            sum += Integer.parseInt(studentForSave.getStu_Score());
        }

        ave = (int) (sum / studentForSaveslist.size() * 100) / 100.0;

    }


    public void close(ActionEvent actionEvent) {

        ObservableList<Stage> stagelists = FXRobotHelper.getStages();
        stagelists.get(stagelists.size() - 1).close();
    }
}
