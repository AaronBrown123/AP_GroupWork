package model;

public class Transactions extends Customer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String transaction_id;
	private String customer_id;
	private double amountPaid;
	private String equipment_name;
	private String paymentDate;
	
	public Transactions() {
		this.transaction_id = "";
		this.customer_id = "";
		this.amountPaid = 0.0;
		this.equipment_name = "";
		this.paymentDate = "";
	}
	
	public Transactions(String transaction_id, String customer_id, double amountPaid, String equipment_name, String paymentDate) 
	{
        this.transaction_id = transaction_id;
        this.customer_id = customer_id;
        this.amountPaid = amountPaid;
        this.equipment_name = equipment_name;
        this.paymentDate = paymentDate;
    }

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getEquipment_name() {
		return equipment_name;
	}

	public void setEquipment_name(String equipment_name) {
		this.equipment_name = equipment_name;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	@Override
	public String toString() {
		return "Transactions [transaction_id=" + transaction_id + ", customer_id=" + customer_id + ", amountPaid="
				+ amountPaid + ", equipment_name=" + equipment_name + ", paymentDate=" + paymentDate + "]";
	}
	
	
}
