package geilerbass.lampkicking.domain;

import geilerbass.lampkicking.model.Coords;
import geilerbass.lampkicking.model.HooverReport;
import geilerbass.lampkicking.model.HooverRequest;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HooverNavigatorTest {

    @Test
    public void hooverNavigatesCorrectlyWithClearPath() {
        assertHooverReportIsAsExpected(new HooverRequest(
                new Coords(5, 5),
                new Coords(1, 2),
                new Coords[]{new Coords(1, 0), new Coords(2, 2), new Coords(2, 3)},
                "NNESEESWNWW"), new HooverReport(new Coords(1, 3), 1)
        );
    }

    @Test
    public void edgeCaseBottomLeftOfGrid() {
        assertHooverReportIsAsExpected(new HooverRequest(
                new Coords(5, 5),
                new Coords(0, 0),
                new Coords[]{new Coords(1, 0), new Coords(2, 2), new Coords(2, 3)},
                "SWSWSWSW"), new HooverReport(new Coords(0, 0), 0)
        );
    }

    @Test
    public void edgeCaseBottomRightOfGrid() {
        assertHooverReportIsAsExpected(new HooverRequest(
                new Coords(5, 5),
                new Coords(4, 0),
                new Coords[]{new Coords(1, 0), new Coords(2, 2), new Coords(2, 3)},
                "SESESESE"), new HooverReport(new Coords(4, 0), 0)
        );
    }

    @Test
    public void edgeCaseTopLeftOfGrid() {
        assertHooverReportIsAsExpected(new HooverRequest(
                new Coords(5, 5),
                new Coords(0, 4),
                new Coords[]{new Coords(1, 0), new Coords(2, 2), new Coords(2, 3)},
                "NWNWNWNW"), new HooverReport(new Coords(0, 4), 0)
        );
    }

    @Test
    public void edgeCaseTopRightOfGrid() {
        assertHooverReportIsAsExpected(new HooverRequest(
                new Coords(5, 5),
                new Coords(4, 4),
                new Coords[]{new Coords(1, 0), new Coords(2, 2), new Coords(2, 3)},
                "NENENENE"), new HooverReport(new Coords(4, 4), 0)
        );
    }

    @Test
    public void noPatchesCleanWhenNoPatchesDirty() {
        assertHooverReportIsAsExpected(new HooverRequest(
                new Coords(5, 5),
                new Coords(0, 0),
                new Coords[]{},
                "NNNNESSSSENNNNESSSSENNNN"
        ), new HooverReport(new Coords(4, 4), 0)
        );
    }

    @Test
    public void duplicatePatchesNotCleanedMoreThanOnce() {
        assertHooverReportIsAsExpected(new HooverRequest(
                new Coords(5, 5),
                new Coords(0, 0),
                new Coords[]{new Coords(1, 1)},
                "NESWNESWNESW"
        ), new HooverReport(new Coords(0, 0), 1)
        );
    }

    @Test
    public void dirtyStartingPositionIsCleaned() {
        assertHooverReportIsAsExpected(new HooverRequest(
                new Coords(5, 5),
                new Coords(2, 2),
                new Coords[]{new Coords(2, 2), new Coords(3, 1)},
                "NNEEN"
        ), new HooverReport(new Coords(4, 4), 1)
        );
    }

    private void assertHooverReportIsAsExpected(final HooverRequest hooverRequest, final HooverReport expected) {

        final HooverNavigator hooverNavigator = new HooverNavigator(hooverRequest.getRoomSize(), hooverRequest.getPatches());

        final HooverReport actual = hooverNavigator.navigate(hooverRequest.getCoords(), hooverRequest.getInstructions());

        assertEquals(expected, actual);
    }


}
