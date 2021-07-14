package aima.core.search.csp.studyplan.cases;

import aima.core.search.csp.studyplan.models.Day;
import aima.core.search.csp.studyplan.models.Subject;
import aima.core.search.csp.studyplan.models.Time;

public class SecondCase extends Case {
    public SecondCase() {
        Time onePM = new Time(13, 0);
        Time threePM = new Time(15, 0);
        Time fivePM = new Time(17, 0);
        Time eightPM = new Time(20, 0);

        Time twoHours = new Time(2, 0);
        Time fourHours = new Time(4, 0);

        Subject comp0409 = new Subject("COMP0409");
        comp0409.addClass(Day.Monday, onePM, twoHours);
        comp0409.addClass(Day.Wednesday, onePM, twoHours);
        addSubject(comp0409);

        Subject comp0438 = new Subject("COMP0438");
        comp0438.addClass(Day.Friday, onePM, fourHours);
        addSubject(comp0438);

        Subject comp0412 = new Subject("COMP0412");
        comp0412.addClass(Day.Tuesday, threePM, twoHours);
        comp0412.addClass(Day.Thursday, threePM, twoHours);
        addSubject(comp0412);

        Subject comp0408 = new Subject("COMP0408");
        comp0408.addClass(Day.Monday, fivePM, twoHours);
        comp0408.addClass(Day.Wednesday, fivePM, twoHours);
        addSubject(comp0408);

        Subject comp0461 = new Subject("COMP0461");
        comp0461.addClass(Day.Monday, eightPM, twoHours);
        comp0461.addClass(Day.Wednesday, eightPM, twoHours);
        addSubject(comp0461);
    }
}
