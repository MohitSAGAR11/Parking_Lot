package strategy;

import model.Gate;
import model.Level;
import model.Slot;
import model.SlotType;

import java.util.List;

public interface SlotStrategy {
    Slot findSlot(Gate gate, SlotType type, List<Level> levels);
}
