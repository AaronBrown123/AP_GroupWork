package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EquipmentAvailabilityResponse implements Serializable {
    private String equipmentId;
    private String availabilityStatus;

    public EquipmentAvailabilityResponse(String equipmentId, String availabilityStatus) {
        this.equipmentId = equipmentId;
        this.availabilityStatus = availabilityStatus;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }
}
