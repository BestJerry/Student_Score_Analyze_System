package Controller;

import Function.ReadAndWrite;
import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by jerry on 16-11-19.
 */
public class Open_Sco_List_Controller implements Initializable {


    @FXML
    private ComboBox choice_course;

    @FXML
    private ComboBox choice_class;

    public static String coursenametra;

    public static String classnametra;

    public void backToMainMenu(ActionEvent actionEvent) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        ObservableList<Stage> stagelist = FXRobotHelper.getStages();
        Scene scene = new Scene(root, 720, 480);
        stagelist.get(0).setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> list = new ArrayList<>();
        try {
            list = ReadAndWrite.readCourse();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<String> course_options = FXCollections.observableArrayList(list);
        choice_course.setItems(course_options);
        choice_course.setValue("面向对象程序设计");
        choice_course.setVisibleRowCount(3);
        choice_course.setEditable(false);

        /*ObservableList<String> class_options = FXCollections.observableArrayList("15级软件工程R1班", "15级软件工程R2班",
                "15级软件工程R3班", "15级软件工程R4班", "15级软件工程R5班", "15级软件工程R6班");
        choice_class.setItems(class_options);
        choice_class.setValue("15级软件工程R1班");
        choice_class.setEditable(false);
        choice_class.setVisibleRowCount(3);*/
    }

    public void openScoreSheet(ActionEvent actionEvent) throws IOException {


        FileChooser filechooser = new FileChooser();
        configureFileChooser(filechooser);

        File file = filechooser.showOpenDialog(new Stage());
        classnametra = file.getName();
        //System.out.println(classnametra);
        String[] pathList = classnametra.split("-");
        coursenametra = pathList[1].replaceAll(".dat", "");

        if (file != null) {

            //openFile(file);
            Stage stage = FXRobotHelper.getStages().get(0);
           // ScoreTable scoreTable = new ScoreTable(stage);
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/View/ScoreTableView.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.setTitle("成绩单");
            stage.setResizable(false);
            Scene scene = new Scene(root, 720, 600);
            stage.setScene(scene);
            stage.show();
        }
    }

    private void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("各班级的该课程成绩单");
        fileChooser.setInitialDirectory(
                new File((String) choice_course.getValue()));

    }
}
