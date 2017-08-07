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

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * Created by jerry on 16-11-18.
 */
public class Open_Cre_Cla_Sco_Sheet_Controller implements Initializable {



    private final Desktop desktop = Desktop.getDesktop();
    @FXML
    private ComboBox choice_course;

    @FXML
    private ComboBox choice_class;

    public static String classnametra;

    public static String coursenametra;

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

        /*ObservableList<String> class_options = FXCollections.observableArrayList("2015级软件工程R1班", "15级软件工程R2班",
                "2015级软件工程R3班", "2015级软件工程R4班", "2015级软件工程R5班", "2015级软件工程R6班");
        choice_class.setItems(class_options);
        //choice_class.setValue("15级软件工程R1班");
        choice_class.setEditable(false);
        choice_class.setVisibleRowCount(3);*/

    }

    public void openAndCheckFile(ActionEvent actionEvent) throws IOException {


        FileChooser filechooser = new FileChooser();
        configureFileChooser(filechooser);

        File file = filechooser.showOpenDialog(new Stage());
        classnametra = file.getName();
        if (file != null) {
            if (!checkFileExist(file)) {
                //WarningDialog warningDialog = new WarningDialog(stage);

                Stage dialogstage = new Stage();
                Parent dialogroot = FXMLLoader.load(getClass().getResource("/View/WarningDialog.fxml"));
                dialogstage.setTitle("提示");
                Scene dialogscene = new Scene(dialogroot,360,240);
                dialogstage.setScene(dialogscene);
                dialogstage.setResizable(false);
                dialogstage.show();
            }
            //openFile(file);
            else {


               Stage stage = FXRobotHelper.getStages().get(0);
               // Cre_Cla_Sco_Sheet cre_cla_sco_sheet  = new Cre_Cla_Sco_Sheet(stage);
                Parent root = null;
                try {
                    root = FXMLLoader.load(getClass().getResource("/View/Cre_Sco_TableView.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                stage.setTitle("新建成绩单");
                stage.setResizable(false);
                Scene scene = new Scene(root, 720, 600);
                stage.setScene(scene);
                stage.show();

            }
        }


    }

    private static void configureFileChooser(final FileChooser fileChooser) {
        fileChooser.setTitle("班级名单");
        fileChooser.setInitialDirectory(
                new File("ClassList"));

    }

    /*private void openFile(File file) {
        EventQueue.invokeLater(() -> {
            try {
                desktop.open(file);
            } catch (IOException ex) {
                Logger.getLogger(Open_Cre_Cla_Sco_Sheet_Controller.
                        class.getName()).
                        log(Level.SEVERE, null, ex);
            }
        });
    }*/

    private boolean checkFileExist(File this_file) {

        String classname = this_file.getName().substring(0, 12);
        String coursename = (String) choice_course.getValue();
        coursenametra = coursename;
        //System.out.println(classname + coursename);
        File file = new File(coursename);
        File[] files = file.listFiles();
        for (int i = 0; files != null && i < files.length; i++) {
            //System.out.println(files[i].getName()+" "+classname+ "-" + coursename + ".dat");
            if (files[i].getName().equals(classname + "-" + coursename + ".dat")) {

                return false;
            }
        }
        return true;
    }
}
