package model;

import java.io.Serializable;

public class Equipment implements Serializable{
	
    private String equipment_id;
    private String equipment_name;
    private String equipment_category;
    private double equipment_cost_per_day;
    
    public Equipment() {
        this.equipment_id = "";
        this.equipment_name = "";
        this.equipment_category = "";
        this.equipment_cost_per_day = 0.0;
    }

    public Equipment(Equipment otherEquipment) {
        this.equipment_id = otherEquipment.equipment_id;
        this.equipment_name = otherEquipment.equipment_name;
        this.equipment_category = otherEquipment.equipment_category;
        this.equipment_cost_per_day = otherEquipment.equipment_cost_per_day;
    }

    
	public Equipment(String equipment_id, String equipment_name, String equipment_category,
			double equipment_cost_per_day) {
		this.equipment_id = equipment_id;
		this.equipment_name = equipment_name;
		this.equipment_category = equipment_category;
		this.equipment_cost_per_day = equipment_cost_per_day;
	}


	public String getEquipment_id() {
		return equipment_id;
	}


	public void setEquipment_id(String equipment_id) {
		this.equipment_id = equipment_id;
	}


	public String getEquipment_name() {
		return equipment_name;
	}


	public void setEquipment_name(String equipment_name) {
		this.equipment_name = equipment_name;
	}


	public String getEquipment_category() {
		return equipment_category;
	}


	public void setEquipment_category(String equipment_category) {
		this.equipment_category = equipment_category;
	}


	public double getEquipment_cost_per_day() {
		return equipment_cost_per_day;
	}


	public void setEquipment_cost_per_day(double equipment_cost_per_day) {
		this.equipment_cost_per_day = equipment_cost_per_day;
	}


	@Override
	public String toString() {
		return "Equipment [equipment_id=" + equipment_id + ", equipment_name=" + equipment_name
				+ ", equipment_category=" + equipment_category + ", equipment_cost_per_day=" + equipment_cost_per_day
				+ "]";
	}
   
	
    
}