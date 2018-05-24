package notification.push.com.smartschool.Models;

import java.util.List;

/**
 * Created by Mujahid on 5/24/2018.
 */

public class Notice {

    private List<Items> notice;

    public Notice(List<Items> notice) {
        this.notice = notice;
    }

    public List<Items> getNotice() {
        return notice;
    }

    public static class Items {
        private int notice_id;
        private String notice_date;
        private String notice_title;
        private String message;
        private String created_date;

        public Items(int notice_id, String notice_date, String notice_title, String message, String created_date) {
            this.notice_id = notice_id;
            this.notice_date = notice_date;
            this.notice_title = notice_title;
            this.message = message;
            this.created_date = created_date;
        }

        public int getNotice_id() {
            return notice_id;
        }

        public String getNotice_date() {
            return notice_date;
        }

        public String getNotice_title() {
            return notice_title;
        }

        public String getMessage() {
            return message;
        }

        public String getCreated_date() {
            return created_date;
        }
    }
}
