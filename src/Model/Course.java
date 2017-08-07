package Model;

import java.io.Serializable;

/**
 * Created by jerry on 16-11-19.
 */
public class Course implements Serializable{

    private String coursename;

    public Course(String coursename) {
        this.coursename = coursename;
    }

    public String getCoursename() {
        return coursename;
    }

    public void setCoursename(String coursename) {
        this.coursename = coursename;
    }
}
