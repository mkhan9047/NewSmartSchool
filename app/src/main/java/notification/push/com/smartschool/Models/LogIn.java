package notification.push.com.smartschool.Models;

import java.util.List;

/**
 * Created by Mujahid on 5/22/2018.
 */

public class LogIn {
    private String reg_no;
    private String  password;
    private List<Student> student;
    private int success;
    private String student_photo;

    public LogIn(String password, String student_photo,String reg_no, List<Student> student, int success) {
        this.reg_no = reg_no;
        this.student_photo = student_photo;
        this.student = student;
        this.success = success;
        this.password = password;
    }

    public String getStudent_photo(){
        return student_photo;
    }

    public String getPassword() {
        return password;
    }

    public String getReg_no() {
        return reg_no;
    }

    public List<Student> getStudent() {
        return student;
    }

    public int getSuccess() {
        return success;
    }
}
