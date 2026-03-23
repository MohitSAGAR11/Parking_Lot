package model;

import java.time.LocalDateTime;

public class Ticket {
    private final String ticketId;
    private final Vehicle vehicle;
    private final Slot assignedSlot;
    private final Level level; // Track which level for easier exit cleanup
    private final LocalDateTime entryTime;

    public Ticket(String id, Vehicle v, Slot s, Level l, LocalDateTime t) {
        this.ticketId = id; this.vehicle = v; this.assignedSlot = s; this.level = l; this.entryTime = t;
    }
    public Slot getAssignedSlot() { return assignedSlot; }
    public Level getLevel() { return level; }
    public Vehicle getVehicle() { return vehicle; }
    public LocalDateTime getEntryTime() { return entryTime; }
}
