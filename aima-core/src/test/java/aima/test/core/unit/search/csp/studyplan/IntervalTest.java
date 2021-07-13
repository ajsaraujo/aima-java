package aima.test.core.unit.search.csp.studyplan;

import aima.core.search.csp.studyplan.models.Day;
import aima.core.search.csp.studyplan.models.DayTime;
import aima.core.search.csp.studyplan.models.Interval;
import aima.core.search.csp.studyplan.models.Time;
import org.junit.Assert;
import org.junit.Test;

public class IntervalTest {
    @Test
    public void overlapShouldReturnFalseIfIntervalsDontOverlap() {
        Interval one = new Interval(new DayTime(Day.Monday, new Time(7, 0)), new Time(1, 0));
        Interval another = new Interval(new DayTime(Day.Tuesday, new Time(7, 0)), new Time(1, 0));

        Assert.assertFalse(one.overlapsWith(another));
    }

    @Test
    public void overlapShouldTrueIfIntervalsOverlap() {
        Interval one = new Interval(new DayTime(Day.Monday, new Time(7, 0)), new Time(1, 0));
        Interval another = new Interval(new DayTime(Day.Monday, new Time(7, 0)), new Time(1, 0));

        Assert.assertTrue(one.overlapsWith(another));
    }
}
