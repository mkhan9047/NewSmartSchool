package notification.push.com.smartschool.Models;

/**
 * Created by Mujahid on 7/7/2018.
 */

public class PostComplaint {
    private int success;
    private String message_body;
    public PostComplaint(int success, String message_body) {
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
