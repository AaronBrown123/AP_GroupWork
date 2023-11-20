package Model;
import java.io.Serializable;

public class Message implements Serializable {

    private String message_id;
    private String customer_id;
    private String message;
    private String date;

    public Message() {
        this.message_id = "";
        this.customer_id = "";
        this.message = "";
        this.date = "";
    }

    public Message(String message_id, String customer_id, String message, String date) {
        this.message_id = message_id;
        this.customer_id = customer_id;
        this.message = message;
        this.date = date;
    }

    // Getters and setters...

    public String getMessage_id() {
        return message_id;
    }

    public void setMessage_id(String message_id) {
        this.message_id = message_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Messages [message_id=" + message_id + ", customer_id=" + customer_id + ", message=" + message
                + ", date=" + date + "]";
    }
}
