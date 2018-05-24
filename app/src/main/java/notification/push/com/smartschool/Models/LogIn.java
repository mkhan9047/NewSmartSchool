package notification.push.com.smartschool.Models;

import java.util.List;

/**
 * Created by Mujahid on 5/22/2018.
 */

public class LogIn {
    private String reg_no;
    private List<Student> student;
    private int success;

    public LogIn(String reg_no, List<Student> student, int success) {
        this.reg_no = reg_no;
        this.student = student;
        this.success = success;
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
