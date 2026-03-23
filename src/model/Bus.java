package model;

public class Bus extends Vehicle {
    public Bus(String num) {
        super(num);
    }

    @Override
    public SlotType getSlotType() {
        return SlotType.LARGE;
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.BUS;
    }
}
