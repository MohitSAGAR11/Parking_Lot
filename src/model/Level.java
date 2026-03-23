package model;

import java.util.*;

public class Level {
    private final String levelId;
    private final Map<SlotType, Map<Gate, PriorityQueue<Slot>>> minHeapPool;

    public Level(String id, List<Slot> slots, List<Gate> gates) {
        this.levelId = id;
        this.minHeapPool = new HashMap<>();
        initializeQueues(slots, gates);
    }

    public void initializeQueues(List<Slot> slots, List<Gate> gates) {
        for (SlotType type : SlotType.values()) {
            Map<Gate, PriorityQueue<Slot>> gateMap = new HashMap<>();
            for (Gate gate : gates) {
                PriorityQueue<Slot> queue = new PriorityQueue<>(Comparator.comparingDouble(s -> s.getDistanceToGate(gate)));
                slots.stream().filter(s -> s.getSlotType() == type).forEach(queue::add);
                gateMap.put(gate, queue);
            }
            minHeapPool.put(type, gateMap);
        }
    }

    public PriorityQueue<Slot> getQueue(SlotType type, Gate gate) {
        return minHeapPool.get(type).get(gate);
    }

    public void addSlotToQueues(Slot slot, List<Gate> gates) {
        for (Gate gate : gates) {
            minHeapPool.get(slot.getSlotType()).get(gate).add(slot);
        }
    }
}
