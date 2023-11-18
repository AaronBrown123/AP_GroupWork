package model;

public class Quotation {
	  private String customerId;
	    private String equipmentId;
	    private String quotationId;
		public Quotation(String customerId, String equipmentId, String quotationId) {
			super();
			this.customerId = customerId;
			this.equipmentId = equipmentId;
			this.quotationId = quotationId;
		}
		public String getCustomerId() {
			return customerId;
		}
		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}
		public String getEquipmentId() {
			return equipmentId;
		}
		public void setEquipmentId(String equipmentId) {
			this.equipmentId = equipmentId;
		}
		public String getQuotationId() {
			return quotationId;
		}
		public void setQuotationId(String quotationId) {
			this.quotationId = quotationId;
		}
		@Override
		public String toString() {
			return "Quotation [customerId=" + customerId + ", equipmentId=" + equipmentId + ", quotationId="
					+ quotationId + ", getCustomerId()=" + getCustomerId() + ", getEquipmentId()=" + getEquipmentId()
					+ ", getQuotationId()=" + getQuotationId() + ", getClass()=" + getClass() + ", hashCode()="
					+ hashCode() + ", toString()=" + super.toString() + "]";
		}
	    

}
