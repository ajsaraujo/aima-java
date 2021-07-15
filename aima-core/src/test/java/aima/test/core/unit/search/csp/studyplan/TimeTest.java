package aima.test.core.unit.search.csp.studyplan;

import aima.core.search.csp.studyplan.models.Time;
import org.junit.Assert;
import org.junit.Test;

public class TimeTest {
    @Test
    public void getDurationTest() {
        Time oneOClock = new Time(1, 0);
        Time twoOClock = new Time(2, 0);
        Time oneHour = new Time(1, 0);

        Assert.assertTrue(Time.getDuration(oneOClock, twoOClock).equals(oneHour));
    }
}
