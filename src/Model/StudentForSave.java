package Model;

import com.sun.org.apache.regexp.internal.REUtil;

import java.io.Serializable;

/**
 * Created by jerry on 16-11-21.
 */
public class StudentForSave implements Serializable {
    private String stu_Name;
    private String stu_Id;
    private String stu_Score;

    public StudentForSave() {
    }

    public StudentForSave(String stu_Name, String stu_Id, String stu_Score) {
        this.stu_Name = stu_Name;
        this.stu_Id = stu_Id;
        this.stu_Score = stu_Score;
    }

    public String getStu_Name() {
        return stu_Name;
    }

    public void setStu_Name(String stu_Name) {
        this.stu_Name = stu_Name;
    }

    public String getStu_Id() {
        return stu_Id;
    }

    public void setStu_Id(String stu_Id) {
        this.stu_Id = stu_Id;
    }

    public String getStu_Score() {
        return stu_Score;
    }

    public void setStu_Score(String stu_Score) {

        this.stu_Score = stu_Score;
    }
}
