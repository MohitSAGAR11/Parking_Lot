package model;

import java.util.Map;

public class Slot {
    private final String slotId;
    private final SlotType slotType;
    private boolean isAvailable = true;
    private final Map<Gate, Double> distanceToGates;

    public Slot(String id, SlotType type, Map<Gate, Double> distances) {
        this.slotId = id; this.slotType = type; this.distanceToGates = distances;
    }
    public double getDistanceToGate(Gate gate) { return distanceToGates.getOrDefault(gate, Double.MAX_VALUE); }
    public void setAvailable(boolean available) { isAvailable = available; }
    public boolean isAvailable() { return isAvailable; }
    public SlotType getSlotType() { return slotType; }
    public String getSlotId() { return slotId; }
}
