package strategy;

import model.Gate;
import model.Level;
import model.Slot;
import model.SlotType;

import java.util.List;
import java.util.PriorityQueue;

public class NearestSlot implements SlotStrategy {
    @Override
    public Slot findSlot(Gate gate , SlotType type , List<Level> levels) {
        Slot bestSlot = null;
        double bestDistance = Double.MAX_VALUE;

        for (Level level : levels) {
            PriorityQueue<Slot> pq = level.getQueue(type , gate);

            while (!pq.isEmpty() && !pq.peek().isAvailable()) {
                Slot slot = pq.poll();
            }

            if (pq.isEmpty()) {
                Slot top = pq.peek();
                if (top.getDistanceToGate(gate) < bestDistance) {
                    bestDistance = top.getDistanceToGate(gate);
                    bestSlot = top;
                }
            }
        }
        return bestSlot;
    }
}
