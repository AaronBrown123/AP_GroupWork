package model;

import java.io.Serializable;

//Subclass representing lighting equipment
public class LightingEquipment extends Equipment implements Serializable{

	public LightingEquipment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LightingEquipment(Equipment otherEquipment) {
		super(otherEquipment);
		// TODO Auto-generated constructor stub
	}

	public LightingEquipment(String equipment_id, String equipment_name, String equipment_category,
			double equipment_cost_per_day) {
		super(equipment_id, equipment_name, equipment_category, equipment_cost_per_day);
		// TODO Auto-generated constructor stub
	}

	
}