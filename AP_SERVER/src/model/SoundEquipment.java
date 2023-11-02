package model;

import java.io.Serializable;

//Subclass representing sound equipment
public class SoundEquipment extends Equipment implements Serializable{

	public SoundEquipment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SoundEquipment(Equipment otherEquipment) {
		super(otherEquipment);
		// TODO Auto-generated constructor stub
	}

	public SoundEquipment(String equipment_id, String equipment_name, String equipment_category,
			double equipment_cost_per_day) {
		super(equipment_id, equipment_name, equipment_category, equipment_cost_per_day);
		// TODO Auto-generated constructor stub
	}

	
}