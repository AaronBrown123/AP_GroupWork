package model;

import java.io.Serializable;

public class Messages implements Serializable{
	
	private String message_id;
	private String customer_id;
	private String message;
	private String date;
	
	public Messages() {
		this.message_id = "100";
		this.customer_id = "200";
		this.message = "message";
		this.date = "";
	}
	
	public Messages(String message_id, String customer_id, String message, String date) {
		this.message_id = message_id;
		this.customer_id = customer_id;
		this.message = message;
		this.date = date;
	}

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


