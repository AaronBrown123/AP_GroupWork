package model;

import java.io.Serializable;

public class ScheduleEquipment implements Serializable {
	private String schedule_id;
	private String date; 
	private String equipment_name;
	private String customer_id;
	private String equipment_id;
	
	public ScheduleEquipment(String schedule_id, String date, String equipment_name, String customer_id, String equipment_id) {
		super();
		this.schedule_id = schedule_id;
		this.date = date;
		this.equipment_name = equipment_name;
		this.customer_id = customer_id;
		this.equipment_id= equipment_id;
	}
	
	public ScheduleEquipment() {
		super();
		this.schedule_id = "";
		this.date = "";
		this.equipment_name = "";
		this.customer_id = "";
		this.equipment_id = "";
	}

	public String getSchedule_id() {
		return schedule_id;
	}

	public void setSchedule_id(String schedule_id) {
		this.schedule_id = schedule_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEquipment_name() {
		return equipment_name;
	}

	public void setEquipment_name(String equipment_name) {
		this.equipment_name = equipment_name;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getEquipment_id() {
		return equipment_id;
	}

	public void setEquipment_id(String equipment_id) {
		this.equipment_id = equipment_id;
	}

	@Override
	public String toString() {
		return "ScheduleEquipment [schedule_id=" + schedule_id + ", date=" + date + ", equipment_name=" + equipment_name
				+ ", customer_id=" + customer_id + ", equipment_id=" + equipment_id + "]";
	}


	
	
	
}
