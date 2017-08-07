package Model;

import javafx.beans.property.*;

import java.io.Serializable;

/**
 * Created by jerry on 16-11-18.
 */
public class Student implements Serializable {

    private final SimpleStringProperty stu_Id = new SimpleStringProperty();

    private final SimpleStringProperty stu_Name  = new SimpleStringProperty();

    private final SimpleStringProperty stu_Score = new SimpleStringProperty();

    public Student() {
        this("","","");
    }

    public Student(String stu_Name, String stu_Id, String stu_score) {
        setStu_Name(stu_Name);
        setStu_Id(stu_Id);
        setStu_Score(stu_score);
    }

    public String getStu_Id() {
        return stu_Id.get();
    }

    public SimpleStringProperty stu_IdProperty() {
        return stu_Id;
    }

    public void setStu_Id(String stu_Id) {
        this.stu_Id.set(stu_Id);
    }

    public String getStu_Name() {
        return stu_Name.get();
    }

    public SimpleStringProperty stu_NameProperty() {
        return stu_Name;
    }

    public void setStu_Name(String stu_Name) {
        this.stu_Name.set(stu_Name);
    }

    public String getStu_Score() {
        return stu_Score.get();
    }

    public SimpleStringProperty stu_ScoreProperty() {
        return stu_Score;
    }

    public void setStu_Score(String stu_Score) {


        this.stu_Score.set(stu_Score);
    }
}
