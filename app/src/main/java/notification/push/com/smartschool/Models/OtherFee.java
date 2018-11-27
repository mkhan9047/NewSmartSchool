package notification.push.com.smartschool.Models;

import java.util.List;

/**
 * Created by Mujahid on 7/13/2018.
 */

public class OtherFee {

    List<String> string;

    public OtherFee(List<String> string) {
        this.string = string;
    }

    public List<String> getString() {
        return string;
    }
}
