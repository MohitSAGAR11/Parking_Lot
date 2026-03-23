
import calculator.RateCalculator;
import model.*;
import service.ParkingLot;

import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Gate gate1 = new Gate("G1");
        Gate gate2 = new Gate("G2");
        List<Gate> gates = Arrays.asList(gate1, gate2);

        Map<Gate, Double> d1 = new HashMap<>(); d1.put(gate1, 5.0); d1.put(gate2, 20.0);
        Slot s1 = new Slot("S1", SlotType.MEDIUM, d1);

        Level lvl1 = new Level("L1", Collections.singletonList(s1), gates);

        Map<SlotType, Double> rates = new HashMap<>(); rates.put(SlotType.MEDIUM, 20.0);
        ParkingLot.create(Collections.singletonList(lvl1), gates, new RateCalculator(rates));

        ParkingLot lot = ParkingLot.getInstance();
        Ticket t = lot.park(new Car("ABC-123"), gate1, LocalDateTime.now().minusHours(3));
        System.out.println("Parked: " + t.getAssignedSlot().getSlotId());
        System.out.println("Fee: $" + lot.exit(t, LocalDateTime.now()));
    }
}