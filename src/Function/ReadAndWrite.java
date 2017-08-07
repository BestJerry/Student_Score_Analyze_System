package Function;

import Controller.Open_Cre_Cla_Sco_Sheet_Controller;
import Controller.Open_Sco_List_Controller;
import Model.Student;
import Model.StudentForSave;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by jerry on 16-11-19.
 */
public class ReadAndWrite {


    public static ArrayList<String> readCourse() throws IOException, ClassNotFoundException {

        ArrayList<String> list = new ArrayList<>();

        File file = new File("course.txt");

        try (
                Scanner input = new Scanner(file, "UTF-8");

        ) {

            while (input.hasNext()) {
                list.add(input.nextLine());
            }

        }
        return list;
    }


    public static ArrayList<Student> readStudentInfo() throws IOException, ClassNotFoundException {

        ArrayList<Student> list = new ArrayList<>();
        String classname = Open_Cre_Cla_Sco_Sheet_Controller.classnametra;
        //File file = new File("ClassList","2015级软件工程R2班.txt");
        File file = new File("ClassList", classname);
        try (
                Scanner input = new Scanner(file,"UTF-8");

        ) {

            while (input.hasNext()) {
                Student student = new Student();
                student.setStu_Id(input.next());
                student.setStu_Name(input.next());
                student.setStu_Score("");
                list.add(student);
            }

        }
        return list;
    }

    public static ArrayList<StudentForSave> readStudentScore() throws IOException, ClassNotFoundException {
        String classname = Open_Sco_List_Controller.classnametra.substring(0, 12);
        String coursename = Open_Sco_List_Controller.coursenametra;
        String filename = classname + "-" + coursename + ".dat";
        File file = new File(coursename, filename);

        ArrayList<StudentForSave> studentForSaveArrayList = new ArrayList<>();

        try (
                ObjectInputStream inputStream = new ObjectInputStream
                        (new FileInputStream(file));
        ) {

            studentForSaveArrayList = (ArrayList<StudentForSave>) inputStream.readObject();

        }
        return studentForSaveArrayList;
    }


    public static void newWriteScore(ArrayList<StudentForSave> studentArrayList) throws IOException {

        String classname = Open_Cre_Cla_Sco_Sheet_Controller.classnametra.substring(0, 12);
        String coursename = Open_Cre_Cla_Sco_Sheet_Controller.coursenametra;
        String filename = classname + "-" + coursename + ".dat";
        File file = new File(coursename, filename);
        try (


                ObjectOutputStream outputStream = new ObjectOutputStream
                        (new FileOutputStream(file));

        ) {

            outputStream.writeObject(studentArrayList);

        }
    }

    public static void writeScore(ArrayList<StudentForSave> studentArrayList) throws IOException {

        String classname = Open_Sco_List_Controller.classnametra.substring(0, 12);
        String coursename = Open_Sco_List_Controller.coursenametra;
        String filename = classname + "-" + coursename + ".dat";
        File file = new File(coursename, filename);
        try (


                ObjectOutputStream outputStream = new ObjectOutputStream
                        (new FileOutputStream(file));

        ) {

            outputStream.writeObject(studentArrayList);

        }
    }
}


