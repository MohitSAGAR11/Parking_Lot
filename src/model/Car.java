package model;

public class Car extends Vehicle{
    public Car(String num) {
        super(num);
    }

    @Override
    public SlotType getSlotType() {
        return SlotType.MEDIUM;
    }

    @Override
    public VehicleType getVehicleType() {
        return VehicleType.CAR;
    }
}
