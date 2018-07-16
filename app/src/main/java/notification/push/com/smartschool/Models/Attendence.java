package notification.push.com.smartschool.Models;

import java.util.List;

/**
 * Created by Mujahid on 7/3/2018.
 */

public class Attendence {

    private List<AttendenceData> attendence;

    public Attendence(List<AttendenceData> attendence) {
        this.attendence = attendence;
    }

    public List<AttendenceData> getAttendence() {
        return attendence;
    }

    public static class AttendenceData {
        private String attendance_date;
        private int status;
        private int half_day;
        private int leave_status;

        public AttendenceData(String attendance_date, int status, int half_day, int leave_status) {
            this.attendance_date = attendance_date;
            this.status = status;
            this.half_day = half_day;
            this.leave_status = leave_status;
        }

        public String getAttendance_date() {
            return attendance_date;
        }

        public int getStatus() {
            return status;
        }

        public int getHalf_day() {
            return half_day;
        }

        public int getLeave_status() {
            return leave_status;
        }
    }
}
