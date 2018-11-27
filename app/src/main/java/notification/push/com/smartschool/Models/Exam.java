package notification.push.com.smartschool.Models;

import java.util.List;

/**
 * Created by Mujahid on 7/16/2018.
 */

public class Exam {
    List<ExamNames> exam_names;

    public Exam(List<ExamNames> exam_names) {
        this.exam_names = exam_names;
    }

    public List<ExamNames> getExam_names() {
        return exam_names;
    }

    public static class ExamNames{
    String exam_type;
    int mark_id;
    String year;

        public String getExam_type() {
            return exam_type;
        }

        public int getMark_id() {
            return mark_id;
        }

        public String getYear() {
            return year;
        }

        public ExamNames(String exam_type, int mark_id, String year) {
            this.exam_type = exam_type;
            this.mark_id = mark_id;
            this.year = year;
        }
    }
}
