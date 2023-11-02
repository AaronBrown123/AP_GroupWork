package model;

import java.io.Serializable;

//Subclass representing staging equipment
public class StagingEquipment extends Equipment implements Serializable {

	public StagingEquipment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StagingEquipment(Equipment otherEquipment) {
		super(otherEquipment);
		// TODO Auto-generated constructor stub
	}

	public StagingEquipment(String equipment_id, String equipment_name, String equipment_category,
			double equipment_cost_per_day) {
		super(equipment_id, equipment_name, equipment_category, equipment_cost_per_day);
		// TODO Auto-generated constructor stub
	}

	
}