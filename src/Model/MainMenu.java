package Model;

import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenu extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/View/MainMenu.fxml"));
        primaryStage.setTitle("学生成绩分析系统");
        primaryStage.setScene(new Scene(root, 720, 480));
        primaryStage.setResizable(false);
        primaryStage.show();


    }


    public static void main(String[] args) {

        launch(args);

    }

}
