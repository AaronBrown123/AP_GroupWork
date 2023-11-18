package model;

import java.io.Serializable;
import java.util.UUID;

public class RentalRequest implements Serializable {

   
    private String request_id;
    private String customer_id;
    private String equipment_name;
    private String event_date;

    public RentalRequest() {
        // Use generateRequestId() to set request_id
        this.request_id = generateRequestId();
        this.customer_id = "";
        this.equipment_name = "";
        this.event_date = "";
    }

    public RentalRequest(String customer_id, String equipment_name, String event_date) {
        // Use generateRequestId() to set request_id
        this.request_id = generateRequestId();
        this.customer_id = customer_id;
        this.equipment_name = equipment_name;
        this.event_date = event_date;
    }

    // Getters and setters...

    public String getRequest_id() {
        return request_id;
    }
    private String generateRequestId() {
        // Use UUID to generate a unique request_id
        return UUID.randomUUID().toString();
    }

    public void setRequest_id(String request_id) {
        this.request_id = request_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getEquipment_name() {
        return equipment_name;
    }

    public void setEquipment_name(String equipment_name) {
        this.equipment_name = equipment_name;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }

    @Override
    public String toString() {
        return "RentalRequest [request_id=" + request_id + ", customer_id=" + customer_id + ", equipment_name=" + equipment_name
                + ", event_date=" + event_date + "]";
    }
}
