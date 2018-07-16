package notification.push.com.smartschool.Models;

import java.util.List;

/**
 * Created by Mujahid on 7/13/2018.
 */

public class OtherFee {

    private List<Fees> other_fees;

    public OtherFee(List<Fees> other_fees) {
        this.other_fees = other_fees;
    }

    public List<Fees> getOther_fees() {
        return other_fees;
    }

    public static class Fees {
        String fee_name;
        int status;

        public String getFee_name() {
            return fee_name;
        }

        public int getStatus() {
            return status;
        }

        public Fees(String fee_name, int status) {
            this.fee_name = fee_name;
            this.status = status;
        }
    }
}
