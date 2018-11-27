package notification.push.com.smartschool.Models;

import java.util.List;

/**
 * Created by Mujahid on 8/2/2018.
 */

public class Bus {
    private List<Transport> bus_fee;

    public static class Transport {
        int month;
        int year;

        public Transport(int month, int year) {
            this.month = month;
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public int getYear() {
            return year;
        }
    }

    public List<Transport> getBus_fee() {
        return bus_fee;
    }
}
