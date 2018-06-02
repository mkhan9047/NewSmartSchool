package notification.push.com.smartschool.Models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mujahid on 5/27/2018.
 */

public class Homework implements Serializable {
    private List<Works> homework;

    public Homework(List<Works> homeworks) {
        this.homework = homeworks;
    }

    public List<Works> getHomeworks() {
        return homework;
    }

    public static class Works implements Serializable {
        int homework_id;
        String homework_date;
        String created_date;
        String homework_detail;
        String teacher_name;
        String file_name;

        public Works(int homework_id, String homework_date, String created_date, String homework_detail, String teacher_name, String file_name) {
            this.homework_id = homework_id;
            this.homework_date = homework_date;
            this.created_date = created_date;
            this.homework_detail = homework_detail;
            this.teacher_name = teacher_name;
            this.file_name = file_name;
        }

        public String getFile_name(){
            return file_name;
        }

        public int getHomework_id() {
            return homework_id;
        }

        public String getHomework_date() {
            return homework_date;
        }

        public String getCreated_date() {
            return created_date;
        }

        public String getHomework_detail() {
            return homework_detail;
        }

        public String getTeacher_name() {
            return teacher_name;
        }
    }
}
