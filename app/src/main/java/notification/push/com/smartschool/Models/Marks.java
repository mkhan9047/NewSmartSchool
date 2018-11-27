package notification.push.com.smartschool.Models;

import java.util.List;

/**
 * Created by Mujahid on 7/17/2018.
 */

public class Marks {

    private List<MarksList> marks;

    public Marks(List<MarksList> marks) {
        this.marks = marks;
    }

    public List<MarksList> getMarks() {
        return marks;
    }

    public static class MarksList{

        String subject_name;
        int mark;
        int full_mark;

        public String getSubject_name() {
            return subject_name;
        }

        public int getMark() {
            return mark;
        }

        public int getFull_mark() {
            return full_mark;
        }

        public MarksList(String subject_name, int mark, int full_mark) {
            this.subject_name = subject_name;
            this.mark = mark;
            this.full_mark = full_mark;
        }
    }
}
