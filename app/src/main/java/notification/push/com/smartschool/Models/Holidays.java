package notification.push.com.smartschool.Models;

import java.util.List;

/**
 * Created by Mujahid on 6/1/2018.
 */

public class Holidays {
    private List<Days> holidays;

    public Holidays(List<Days> holidays) {
        this.holidays = holidays;
    }

    public List<Days> getHolidays() {
        return holidays;
    }

    public static class Days {
        int holiday_id;
        String from_date;
        String to_date;
        String holiday_name;

        public Days(int holiday_id, String from_date, String to_date, String holiday_name) {
            this.holiday_id = holiday_id;
            this.from_date = from_date;
            this.to_date = to_date;
            this.holiday_name = holiday_name;
        }

        public int getHoliday_id() {
            return holiday_id;
        }

        public String getFrom_date() {
            return from_date;
        }

        public String getTo_date() {
            return to_date;
        }

        public String getHoliday_name() {
            return holiday_name;
        }
    }


}
