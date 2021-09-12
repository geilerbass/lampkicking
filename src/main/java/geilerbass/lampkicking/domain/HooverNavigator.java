package geilerbass.lampkicking.domain;

import geilerbass.lampkicking.model.Coords;
import geilerbass.lampkicking.model.HooverReport;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class HooverNavigator {
    private final Coords boundarySpaces;
    private final Coords[] dirtyPatches;

    public HooverNavigator(final Coords roomSize, final Coords[] dirtyPatches) {
        this.boundarySpaces = new Coords(roomSize.getX() -1, roomSize.getY() - 1);
        this.dirtyPatches = dirtyPatches;
    }

    public HooverReport navigate(final Coords start, final String instructions) {
        final List<Function<Coords, Coords>> hooverMoves = toHooverMoves(instructions);

        final LinkedList<Coords> path = computeHooverPath(start, hooverMoves);

        final Set<Coords> visited = computeSpacesVisited(path);

        return new HooverReport(path.getFirst(), visited.size());
    }

    private Set<Coords> computeSpacesVisited(LinkedList<Coords> path) {
        final Set<Coords> visited = new HashSet<>(path);
        visited.retainAll(new HashSet<>(Arrays.asList(dirtyPatches)));
        return visited;
    }

    private LinkedList<Coords> computeHooverPath(Coords start, List<Function<Coords, Coords>> hooverMoves) {
        final LinkedList<Coords> path = new LinkedList<>();
        path.addFirst(start);

        for(Function<Coords, Coords> move : hooverMoves) {
            path.addFirst(move.apply(path.getFirst()));
        }
        return path;
    }

    private List<Function<Coords, Coords>> toHooverMoves(String instructions) {
        return instructions.chars().mapToObj(this::mapInstructions).collect(Collectors.toList());
    }

    private Function<Coords, Coords> mapInstructions(int c) {
        switch (c) {
            case 'N' :
                return (start -> start.getY() < boundarySpaces.getY() ? new Coords(start.getX(), start.getY() + 1) : start);
            case 'S' :
                return (start -> start.getY() > 0 ? new Coords(start.getX(), start.getY() - 1) : start);
            case 'E' :
                return (start -> start.getX() < boundarySpaces.getX() ? new Coords(start.getX() + 1, start.getY()) : start);
            case 'W' :
                return (start -> start.getX() > 0 ? new Coords(start.getX() - 1, start.getY()) : start);
            default :
                return (start -> start);
        }
    }
}
