package geilerbass.lampkicking.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import geilerbass.lampkicking.model.serialization.HooverReportSerializer;

import java.util.Objects;

@JsonSerialize(using = HooverReportSerializer.class)
public class HooverReport {
    private final Coords coords;
    private final int patches;

    public HooverReport(Coords coords, int patches) {
        this.coords = coords;
        this.patches = patches;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HooverReport that = (HooverReport) o;
        return patches == that.patches && Objects.equals(coords, that.coords);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coords, patches);
    }

    public Coords getCoords() {
        return coords;
    }

    public int getPatches() {
        return patches;
    }
}
