package model;

import java.io.Serializable;

public class Staff implements Serializable {
	private String employee_id;
	private String employee_name;
	private String contact;
	private String employee_password;
	
	
	public Staff() {
		this.employee_id = "employee_id";
		this.employee_name = "employee_name";
		this.contact = "contact";
		this.employee_password = "employee_password";
	}
	
	public Staff(String employee_id, String employee_name, String contact, String employee_password) {
		this.employee_id = employee_id;
		this.employee_name = employee_name;
		this.contact = contact;
		this.employee_password = employee_password;
	}

	public Staff(Staff otherStaff) {
	    this.employee_id = otherStaff.employee_id;
	    this.employee_name = otherStaff.employee_name;
	    this.contact = otherStaff.contact;
	    this.employee_password = otherStaff.employee_password;
	}

	
	public String getEmployee_id() {
		return employee_id;
	}


	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}


	public String getEmployee_name() {
		return employee_name;
	}


	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getEmployee_password() {
		return employee_password;
	}


	public void setEmployee_password(String employee_password) {
		this.employee_password = employee_password;
	}

	@Override
	public String toString() {
		return "Staff [employee_id=" + employee_id + ", employee_name=" + employee_name + ", contact=" + contact
				+ ", employee_password=" + employee_password + "]";
	}
	
}
