package geilerbass.lampkicking.domain;

import geilerbass.lampkicking.model.Coords;

import java.util.function.Function;

public enum HooverMove {

    N(start -> new Coords(start.getX(), start.getY() + 1)),
    S(start -> new Coords(start.getX(), start.getY() - 1)),
    E(start -> new Coords(start.getX() + 1, start.getY())),
    W(start -> new Coords(start.getX() - 1, start.getY()));

    private Function<Coords, Coords> move;

    HooverMove(Function<Coords, Coords> move) {
        this.move = move;
    }

    public Function<Coords, Coords> getMove() {
        return move;
    }
}
