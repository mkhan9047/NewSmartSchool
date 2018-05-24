package notification.push.com.smartschool.Models;

import java.io.Serializable;

/**
 * Created by Mujahid on 5/22/2018.
 */

public class Student implements Serializable {
    private String student_name;
    private String student_id;
    private String father_name;
    private String gender;

    public Student(String student_name, String student_id, String father_name, String gender) {
        this.student_name = student_name;
        this.student_id = student_id;
        this.father_name = father_name;
        this.gender = gender;
    }

    public String getStudent_name() {
        return student_name;
    }

    public String getStudent_id() {
        return student_id;
    }

    public String getFather_name() {
        return father_name;
    }

    public String getGender() {
        return gender;
    }
}
