package model;

import java.util.Objects;

public class Gate {
    private final String gateId;

    public Gate(String gateId) {
        this.gateId = gateId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Gate)) {
            return false;
        }
        return Objects.equals(gateId, ((Gate) obj).gateId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gateId);
    }
}
