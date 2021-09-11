package geilerbass.lampkicking.domain;

import geilerbass.lampkicking.model.Coords;
import geilerbass.lampkicking.model.HooverReport;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HooverNavigator {
    private final Coords roomSize;
    private final Coords[] patches;

    public HooverNavigator(final Coords roomSize, final Coords[] patches) {
        this.roomSize = roomSize;
        this.patches = patches;
    }

    public HooverReport navigate(final Coords start, final String instructions) {
        List<Function<Coords, Coords>> hooverMoves = toHooverMoves(instructions);
        Set<Coords> visited = new HashSet<>();
        Coords position = start;
        for(Function<Coords, Coords> move : hooverMoves) {
            position = move.apply(position);
            visited.add(position);
        }

        visited.retainAll(new HashSet<>(Arrays.asList(patches)));

        return new HooverReport(position, visited.size());
    }

    private List<Function<Coords, Coords>> toHooverMoves(String instructions) {
        return instructions.chars().mapToObj(this::mapInstructions).collect(Collectors.toList());
    }

    private Function<Coords, Coords> mapInstructions(int c) {
        switch (c) {
            case 'N' :
                return (start -> start.getY() < roomSize.getY() ? new Coords(start.getX(), start.getY() + 1) : start);
            case 'S' :
                return (start -> start.getY() > 0 ? new Coords(start.getX(), start.getY() - 1) : start);
            case 'E' :
                return (start -> start.getY() < roomSize.getX() ? new Coords(start.getX() + 1, start.getY()) : start);
            case 'W' :
                return (start -> start.getX() > 0 ? new Coords(start.getX() - 1, start.getY()) : start);
            default :
                return (start -> start);
        }
    }
}
