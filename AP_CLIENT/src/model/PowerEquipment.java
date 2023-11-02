package model;

import java.io.Serializable;

//Subclass representing power equipment
public class PowerEquipment extends Equipment implements Serializable{

	public PowerEquipment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PowerEquipment(Equipment otherEquipment) {
		super(otherEquipment);
		// TODO Auto-generated constructor stub
	}

	public PowerEquipment(String equipment_id, String equipment_name, String equipment_category,
			double equipment_cost_per_day) {
		super(equipment_id, equipment_name, equipment_category, equipment_cost_per_day);
		// TODO Auto-generated constructor stub
	}
 
}