package Controller;

import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable{

    @FXML
    public void quitSystem(ActionEvent actionEvent) {
        System.exit(0);
    }

    @FXML
    public void openCre_Cla_She(ActionEvent actionEvent) throws Exception {


        Parent root = FXMLLoader.load(getClass().getResource("/View/Open_Cre_Cla_Sco_Sheet.fxml"));

        ObservableList<Stage> stagelist = FXRobotHelper.getStages();
        Scene scene = new Scene(root, 720, 480);
        stagelist.get(0).setScene(scene);

    }

    public void openScoreList(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/View/OpenScoreList.fxml"));

        ObservableList<Stage> stagelist = FXRobotHelper.getStages();
        Scene scene = new Scene(root, 720, 480);
        stagelist.get(0).setScene(scene);
    }

    public void OpenAboutSystem(ActionEvent actionEvent) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/View/AboutSystem.fxml"));

        ObservableList<Stage> stagelist = FXRobotHelper.getStages();
        Scene scene = new Scene(root, 720, 480);
        stagelist.get(0).setScene(scene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
