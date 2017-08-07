package Controller;

import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by jerry on 16-11-21.
 */
public class WarningDialogController implements Initializable {



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void close(ActionEvent actionEvent) {

        ObservableList<Stage> stagelists = FXRobotHelper.getStages();
        stagelists.get(stagelists.size()-1).close();

    }
}
