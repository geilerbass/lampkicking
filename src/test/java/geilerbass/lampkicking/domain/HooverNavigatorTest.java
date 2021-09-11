package geilerbass.lampkicking.domain;

import geilerbass.lampkicking.model.Coords;
import geilerbass.lampkicking.model.HooverReport;
import geilerbass.lampkicking.model.HooverRequest;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HooverNavigatorTest {

    @Test
    public void hooverNavigatesCorrectlyWithClearPath() {
        assertHooverReportIsAsExpected(new HooverReport(new Coords(1, 3), 1),
                new HooverRequest(
                        new Coords(5, 5),
                        new Coords(1, 2),
                        new Coords[]{new Coords(1, 0), new Coords(2, 2), new Coords(2, 3)},
                        "NNESEESWNWW"));
    }

    @Test
    public void edgeCaseBottomLeftOfGrid() {
        assertHooverReportIsAsExpected(new HooverReport(new Coords(0, 0), 0),
                new HooverRequest(
                        new Coords(5, 5),
                        new Coords(0, 0),
                        new Coords[]{new Coords(1, 0), new Coords(2, 2), new Coords(2, 3)},
                        "SWSWSWSW"));
    }

    @Test
    public void edgeCaseBottomRightOfGrid() {
        assertHooverReportIsAsExpected(new HooverReport(new Coords(4, 0), 0),
                new HooverRequest(
                        new Coords(5, 5),
                        new Coords(4, 0),
                        new Coords[]{new Coords(1, 0), new Coords(2, 2), new Coords(2, 3)},
                        "SESESESE"));
    }

    @Test
    public void edgeCaseTopLeftOfGrid() {
        assertHooverReportIsAsExpected(new HooverReport(new Coords(0, 4), 0),
                new HooverRequest(
                        new Coords(5, 5),
                        new Coords(0, 4),
                        new Coords[]{new Coords(1, 0), new Coords(2, 2), new Coords(2, 3)},
                        "NWNWNWNW"));
    }

    @Test
    public void edgeCaseTopRightOfGrid() {
        assertHooverReportIsAsExpected(new HooverReport(new Coords(4, 4), 0),
                new HooverRequest(
                        new Coords(5, 5),
                        new Coords(4, 4),
                        new Coords[]{new Coords(1, 0), new Coords(2, 2), new Coords(2, 3)},
                        "NENENENE"));
    }

    @Test
    public void noPatchesCleanWhenNoPatchesDirty() {
        assertHooverReportIsAsExpected(new HooverReport(new Coords(4, 4), 0),
                new HooverRequest(
                        new Coords(5, 5),
                        new Coords(0, 0),
                        new Coords[]{},
                        "NNNNESSSSENNNNESSSSENNNN"
                )
                );
    }

    @Test
    public void duplicatePatchesNotCleanedMoreThanOnce() {
        assertHooverReportIsAsExpected(new HooverReport(new Coords(0, 0), 1),
                new HooverRequest(
                        new Coords(5, 5),
                        new Coords(0, 0),
                        new Coords[]{new Coords(1, 1)},
                        "NESWNESWNESW"
                )
        );
    }

    private void assertHooverReportIsAsExpected(final HooverReport expected, final HooverRequest hooverRequest) {

        final HooverNavigator hooverNavigator = new HooverNavigator(hooverRequest.getRoomSize(), hooverRequest.getPatches());

        final HooverReport actual = hooverNavigator.navigate(hooverRequest.getCoords(), hooverRequest.getInstructions());

        assertEquals(expected, actual);
    }


}
