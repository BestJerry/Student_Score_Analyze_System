package Controller;

import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

/**
 * Created by jerry on 16-12-9.
 */
public class IllegalScanWarningController {


    public void close(ActionEvent actionEvent) {
        ObservableList<Stage> stagelists = FXRobotHelper.getStages();
        stagelists.get(stagelists.size()-1).close();
    }
}
