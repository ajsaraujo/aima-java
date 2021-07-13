package aima.test.core.unit.search.csp.studyplan;

import aima.core.search.csp.studyplan.models.Day;
import aima.core.search.csp.studyplan.models.DayTime;
import aima.core.search.csp.studyplan.models.Interval;
import aima.core.search.csp.studyplan.models.Time;
import org.junit.Assert;
import org.junit.Test;

public class IntervalTest {
    DayTime mondayEightPM = new DayTime(Day.Monday, new Time(20, 0));
    DayTime mondayHalfPastEightPM = new DayTime(Day.Monday, new Time(20, 30));
    Time fourHours = new Time(4, 0);

    @Test
    public void overlapShouldReturnFalseIfIntervalsDontOverlap() {
        Interval one = new Interval(new DayTime(Day.Monday, new Time(7, 0)), new Time(1, 0));
        Interval another = new Interval(new DayTime(Day.Tuesday, new Time(7, 0)), new Time(1, 0));

        Assert.assertFalse(one.overlapsWith(another));
    }

    @Test
    public void overlapShouldReturnTrueIfIntervalsOverlap() {
        Interval one = new Interval(new DayTime(Day.Monday, new Time(7, 0)), new Time(1, 0));
        Interval another = new Interval(new DayTime(Day.Monday, new Time(7, 0)), new Time(1, 0));

        Assert.assertTrue(one.overlapsWith(another));
    }

    @Test
    public void overlapShouldReturnTrueIfIntervalsAreEqual() {
        Interval one = new Interval(new DayTime(Day.Monday, new Time(20, 0)), new Time(4, 0));
        Interval another = new Interval(new DayTime(Day.Monday, new Time(20, 0)), new Time(4, 0));

        Assert.assertTrue(one.overlapsWith(another));
    }

    @Test
    public void yetAnotherIntervalOverlapTest() {
        Interval one = new Interval(mondayEightPM, fourHours);
        Interval another = new Interval(mondayHalfPastEightPM, fourHours);

        Assert.assertTrue(one.overlapsWith(another));
    }

    @Test
    public void endTimeShouldBeComputedCorrectly() {
        Interval interval = new Interval(mondayEightPM, fourHours);
        DayTime endTime = interval.endTime();

        Assert.assertEquals(endTime.day, Day.Tuesday);
        Assert.assertEquals(endTime.hours(), 0);
        Assert.assertEquals(endTime.minutes(), 0);
    }

    @Test
    public void beforeShouldBeComputedCorrectly() {
        DayTime midnightTuesday = new DayTime(Day.Tuesday, new Time(0, 0));
        Assert.assertTrue(mondayEightPM.before(midnightTuesday));
    }
}
