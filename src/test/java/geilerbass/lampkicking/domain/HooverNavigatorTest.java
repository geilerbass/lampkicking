package geilerbass.lampkicking.domain;

import geilerbass.lampkicking.model.Coords;
import geilerbass.lampkicking.model.HooverReport;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HooverNavigatorTest {

    @Test
    public void hooverNavigatesCorrectly() {
        HooverNavigator hooverNavigator =
                new HooverNavigator(new Coords(5, 5),
                        new Coords[]{new Coords(1, 0), new Coords(2, 2), new Coords(2, 3)});

        HooverReport actual = hooverNavigator.navigate(new Coords(1, 2), "NNESEESWNWW");

        HooverReport expected = new HooverReport(new Coords(1, 3), 1);
        assertEquals(expected, actual);
    }
}
