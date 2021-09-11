package geilerbass.lampkicking.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import geilerbass.lampkicking.model.serialization.HooverRequestDeserializer;

import java.util.Arrays;
import java.util.Objects;

@JsonDeserialize(using = HooverRequestDeserializer.class)
public class HooverRequest {
    public Coords getRoomSize() {
        return roomSize;
    }

    public Coords getCoords() {
        return coords;
    }

    public Coords[] getPatches() {
        return patches;
    }

    public String getInstructions() {
        return instructions;
    }

    private final Coords roomSize;
    private final Coords coords;
    private final Coords[] patches;
    private final String instructions;

    public HooverRequest(final Coords roomSize, final Coords coords, final Coords[] patches, final String instructions) {
        this.roomSize = roomSize;
        this.coords = coords;
        this.patches = patches;
        this.instructions = instructions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HooverRequest that = (HooverRequest) o;
        return Objects.equals(roomSize, that.roomSize) && Objects.equals(coords, that.coords) && Arrays.equals(patches, that.patches) && Objects.equals(instructions, that.instructions);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(roomSize, coords, instructions);
        result = 31 * result + Arrays.hashCode(patches);
        return result;
    }
}
