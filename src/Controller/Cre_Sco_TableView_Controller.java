package Controller;

import Function.ReadAndWrite;
import Model.Student;
import Model.StudentForSave;
import com.sun.javafx.robot.impl.FXRobotHelper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by jerry on 16-11-20.
 */
public class Cre_Sco_TableView_Controller implements Initializable {



    @FXML
    private Label title;
    @FXML
    private TableView score_tableview;

    @FXML
    private TableColumn student_name;
    @FXML
    private TableColumn student_id;
    @FXML
    private TableColumn<Student,String> student_score;



    ObservableList<Student> data =
            FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        title.setText(Open_Cre_Cla_Sco_Sheet_Controller.classnametra.substring(0,12)+"-"+
        Open_Cre_Cla_Sco_Sheet_Controller.coursenametra);

        ArrayList<Student> list = null;
        try {
            list = ReadAndWrite.readStudentInfo();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Student student : list) {
            data.add(student);
        }

        student_name.setCellValueFactory(new PropertyValueFactory<>("stu_Name"));
        student_id.setCellValueFactory(new PropertyValueFactory<>("stu_Id"));
        student_score.setCellValueFactory(new PropertyValueFactory<>("stu_Score"));


        /*Callback<TableColumn<Student, String>,
                        TableCell<Student, String>> cellFactory
                = (TableColumn<Student, String> p) -> new EditingCell();

        student_score.setCellFactory(cellFactory);*/
        student_score.setCellFactory(TextFieldTableCell.<Student>forTableColumn());

        student_score.setOnEditCommit(
                (TableColumn.CellEditEvent<Student, String> t) -> {
                    ((Student) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                    ).setStu_Score(t.getNewValue());
                });

        score_tableview.setEditable(true);
        score_tableview.setItems(data);
    }

    public void back(ActionEvent actionEvent) throws IOException {

        Pane root = FXMLLoader.load(getClass().getResource("/View/Open_Cre_Cla_Sco_Sheet.fxml"));
        ObservableList<Stage> stagelist = FXRobotHelper.getStages();
        Scene scene = new Scene(root, 720, 480);
        stagelist.get(0).setTitle("学生成绩分析系统");
        stagelist.get(0).setScene(scene);

    }

    public void saveData(ActionEvent actionEvent) throws IOException {

        boolean flag = false;

        ArrayList<StudentForSave> list = new ArrayList<>();
        for(Student student : data){
            StudentForSave student1 = new StudentForSave();
            student1.setStu_Name(student.getStu_Name());
            student1.setStu_Id(student.getStu_Id());
            try {
                if(student.getStu_Score()=="") {
                    student1.setStu_Score("0");
                }
                else if(Double.parseDouble(student.getStu_Score())>100||Double.parseDouble(student.getStu_Score())<0){
                    flag = true;
                    Stage stage = new Stage();
                    //WarningDialog warningDialog = new WarningDialog(stage);

                    Stage dialogstage = new Stage();
                    Parent dialogroot = FXMLLoader.load(getClass().getResource("/View/IllegalScanWarning.fxml"));
                    dialogstage.setTitle("提示");
                    Scene dialogscene = new Scene(dialogroot,360,240);
                    dialogstage.setScene(dialogscene);
                    dialogstage.setResizable(false);
                    dialogstage.show();
                }
                else
                    student1.setStu_Score(student.getStu_Score());
                list.add(student1);
            }
            catch (Exception e){
                flag = true;
                //WarningDialog warningDialog = new WarningDialog(stage);

                Stage dialogstage = new Stage();
                Parent dialogroot = FXMLLoader.load(getClass().getResource("/View/IllegalScanWarning.fxml"));
                dialogstage.setTitle("提示");
                Scene dialogscene = new Scene(dialogroot,360,240);
                dialogstage.setScene(dialogscene);
                dialogstage.setResizable(false);
                dialogstage.show();
            }

        }

        if(!flag) {


            ReadAndWrite.newWriteScore(list);

            Pane root = FXMLLoader.load(getClass().getResource("/View/Open_Cre_Cla_Sco_Sheet.fxml"));
            ObservableList<Stage> stagelist = FXRobotHelper.getStages();
            Scene scene = new Scene(root, 720, 480);
            stagelist.get(0).setTitle("学生成绩分析系统");
            stagelist.get(0).setScene(scene);
        }
    }

    /*class EditingCell extends TableCell<Student, String> {

        private TextField textField;

        public EditingCell() {
        }

        @Override
        public void startEdit() {
            if (!isEmpty()) {
                super.startEdit();
                createTextField();
                setText(null);
                setGraphic(textField);
                textField.selectAll();
            }
        }

        @Override
        public void cancelEdit() {
            super.cancelEdit();

            setText((String) getItem());
            setGraphic(null);
        }

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (empty) {
                setText(null);
                setGraphic(null);
            } else {
                if (isEditing()) {
                    if (textField != null) {
                        textField.setText(getString());
                    }
                    setText(null);
                    setGraphic(textField);
                } else {

                        setText(item);
                    setGraphic(null);
                }
            }
        }

        private void createTextField() {
            textField = new TextField(getString());
            textField.setMinWidth(this.getWidth() - this.getGraphicTextGap()* 2);
            textField.focusedProperty().addListener(
                    (ObservableValue<? extends Boolean> arg0,
                     Boolean arg1, Boolean arg2) -> {
                        if (!arg2) {

                            commitEdit(textField.getText());
                        }
                        else
                            commitEdit(getString());
                    });
        }

        private String getString() {
            return getItem() == null ? "" : getItem().toString();
        }
    }*/

}
