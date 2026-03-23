package model;

public abstract class Vehicle {
    protected String vehicleNumber;

    public Vehicle(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public abstract VehicleType getVehicleType();

    public abstract SlotType getSlotType();
}
