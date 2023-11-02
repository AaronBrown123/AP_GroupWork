package model;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {
	private int customer_id;
	private String customer_name;
	private String contact;
	private String customer_password;
	private double accountbal;
	
	public Customer() {
	    this.customer_id = 0;
	    this.customer_name = "Default Name";
	    this.contact = "Default Contact";
	    this.customer_password = "Default Password";
	    this.accountbal = 0.0;
	}

	
	public Customer(int customer_id, String customer_name, String contact, String customer_password,
			double accountbal) {
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.contact = contact;
		this.customer_password = customer_password;
		this.accountbal = accountbal;
	}


	public Customer(Customer otherCustomer) {
	    this.customer_id = otherCustomer.customer_id;
	    this.customer_name = otherCustomer.customer_name;
	    this.contact = otherCustomer.contact;
	    this.customer_password = otherCustomer.customer_password;
	    this.accountbal = otherCustomer.accountbal;
	}

	public int getCustomer_id() {
		return customer_id;
	}


	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}


	public String getCustomer_name() {
		return customer_name;
	}


	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}


	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}


	public String getCustomer_password() {
		return customer_password;
	}


	public void setCustomer_password(String customer_password) {
		this.customer_password = customer_password;
	}


	public double getAccountbal() {
		return accountbal;
	}


	public void setAccountbal(double accountbal) {
		this.accountbal = accountbal;
	}
	
	
	
	
	
}