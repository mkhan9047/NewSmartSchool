package notification.push.com.smartschool.Models;

/**
 * Created by Mujahid on 8/8/2018.
 */

public class PassChange {
    private int success;
    private String message_body;
    public PassChange(int success, String message_body) {
        this.success = success;
        this.message_body = message_body;
    }

    public int getSuccess() {
        return success;
    }

    public String getMessage_body(){
        return message_body;
    }
}
