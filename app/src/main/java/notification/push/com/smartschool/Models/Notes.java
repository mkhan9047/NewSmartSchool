package notification.push.com.smartschool.Models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Mujahid on 5/26/2018.
 */

public class Notes implements Serializable{

    private List<NoteItems> note;

    public Notes(List<NoteItems> notes) {
        this.note = notes;
    }



    public List<NoteItems> getNotes() {
        return note;
    }

    public static class NoteItems implements Serializable{
        int note_id;
        String note_date;
        String note_title;
        String message;
        String created_date;
        String teacher_name;

        public NoteItems(int note_id, String note_date, String note_title, String message, String created_date, String teacher_name) {
            this.note_id = note_id;
            this.note_date = note_date;
            this.note_title = note_title;
            this.message = message;
            this.created_date = created_date;
            this.teacher_name = teacher_name;
        }

        public int getNote_id() {
            return note_id;
        }

        public String getTeacher_name(){
            return teacher_name;
        }
        public String getNote_date() {
            return note_date;
        }

        public String getNote_title() {
            return note_title;
        }

        public String getMessage() {
            return message;
        }

        public String getCreated_date() {
            return created_date;
        }
    }
}
