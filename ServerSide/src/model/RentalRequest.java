package model;

import java.io.Serializable;

public class RentalRequest implements Serializable {
	private String request_id;
	private String customer_id;
	private String equipment_id;
	private String date;
	
	public RentalRequest(String request_id, String customer_id, String equipment_id, String date) {
		super();
		this.request_id = request_id;
		this.customer_id = customer_id;
		this.equipment_id = equipment_id;
		this.date = date;
	}
	public RentalRequest() {
		super();
		this.request_id = "";
		this.customer_id = "";
		this.equipment_id = "";
		this.date = "";
	}
	public String getRequest_id() {
		return request_id;
	}
	public String getCustomer_id() {
		return customer_id;
	}
	public String getEquipment_id() {
		return equipment_id;
	}
	public String getDate() {
		return date;
	}
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}
	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}
	public void setEquipment_id(String equipment_id) {
		this.equipment_id = equipment_id;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "RentalRequest [request_id=" + request_id + ", customer_id=" + customer_id + ", equipment_id="
				+ equipment_id + ", date=" + date + "]";
	}
	
	
	
	
	
}
