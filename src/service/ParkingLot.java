package service;

import calculator.RateCalculator;
import model.*;
import strategy.NearestSlot;
import strategy.SlotStrategy;

import java.time.LocalDateTime;
import java.util.List;
import java.util.PriorityQueue;
import java.util.UUID;

public class ParkingLot {
    private static volatile ParkingLot instance;
    private List<Level> levels;
    private List<Gate>  gates;
    private RateCalculator rateCalculator;
    private SlotStrategy slotStrategy;

    private ParkingLot(List<Level> levels , List<Gate> gates, RateCalculator rateCalculator) {
        this.levels = levels;
        this.gates = gates;
        this.rateCalculator = rateCalculator;
        this.slotStrategy = new NearestSlot();
    }

    public static void create(List<Level> levels, List<Gate> gates, RateCalculator rateCalculator) {
        if (instance == null) {
            synchronized (ParkingLot.class) {
                if (instance == null) instance = new ParkingLot(levels, gates, rateCalculator);
            }
        }
    }

    public static ParkingLot getInstance() {
        return instance;
    }

    public synchronized Ticket park(Vehicle vehicle, Gate gate, LocalDateTime entryTime) {
        if (vehicle == null) throw new IllegalArgumentException("Vehicle cannot be null");
        if (gate == null)    throw new IllegalArgumentException("Gate cannot be null");

        Slot bestSlot = null;
        Level bestLevel = null;
        double minDistance = Double.MAX_VALUE;

        for (Level level : levels) {
            PriorityQueue<Slot> pq = level.getQueue(vehicle.getSlotType(), gate);

            if (pq == null) continue;

            while (!pq.isEmpty() && !pq.peek().isAvailable()) { pq.poll(); }

            if (!pq.isEmpty()) {
                Slot candidate = pq.peek();
                double dist = candidate.getDistanceToGate(gate);
                if (dist < minDistance) {
                    minDistance = dist;
                    bestSlot  = candidate;
                    bestLevel = level;
                }
            }
        }

        if (bestSlot == null) {
            throw new RuntimeException("Parking lot is full for vehicle type: "
                    + vehicle.getSlotType());
        }

        bestSlot.setAvailable(false);
        return new Ticket(UUID.randomUUID().toString(), vehicle, bestSlot, bestLevel, entryTime);
    }

    public synchronized double exit(Ticket ticket , LocalDateTime exitTime) {
        Slot slot = ticket.getAssignedSlot();
        slot.setAvailable(true);

        ticket.getLevel().addSlotToQueues(slot , gates);

        return rateCalculator.calculateRate(ticket.getVehicle().getSlotType() , ticket.getEntryTime() , exitTime);
    }
}
