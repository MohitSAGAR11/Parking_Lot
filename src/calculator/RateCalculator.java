package calculator;

import model.SlotType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

public class RateCalculator {
    private Map<SlotType, Double> rateMap;

    public RateCalculator(Map<SlotType, Double> rateMap) {
        this.rateMap = rateMap;
    }

    public double calculateRate(SlotType type, LocalDateTime entryTime, LocalDateTime exitTime) {
        long hours = Math.max(1, Duration.between(entryTime, exitTime).toHours());
        return hours * rateMap.getOrDefault(type, 10.0);
    }
}
