package model;

public class Bike extends Vehicle {
    public Bike(String num) {
        super(num);
    }

    @Override
    public SlotType getSlotType() {
        return SlotType.SMALL;
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.TWO_WHEELER;
    }
}
