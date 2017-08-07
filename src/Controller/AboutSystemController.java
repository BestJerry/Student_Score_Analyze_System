package Controller;

import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jerry on 16-11-22.
 */
public class AboutSystemController implements Initializable {


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void backtomenu(ActionEvent actionEvent) throws IOException {
        Pane root = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        ObservableList<Stage> stagelist = FXRobotHelper.getStages();
        Scene scene = new Scene(root, 720, 480);
        stagelist.get(0).setScene(scene);
    }
}
