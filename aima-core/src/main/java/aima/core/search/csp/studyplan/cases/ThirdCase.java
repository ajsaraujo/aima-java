package aima.core.search.csp.studyplan.cases;

import aima.core.search.csp.studyplan.models.Day;
import aima.core.search.csp.studyplan.models.Subject;
import aima.core.search.csp.studyplan.models.Time;

public class ThirdCase extends Case {
    public ThirdCase() {
        Time onePM = new Time(13, 0);
        Time threePM = new Time(15, 0);
        Time fivePM = new Time(17, 0);
        Time eightPM = new Time(20, 0);
        Time twoHours = new Time(2, 0);

        Subject elet0043 = new Subject("ELET0043");
        elet0043.addClass(Day.Monday, onePM, twoHours);
        addSubject(elet0043);

        Subject mat0096 = new Subject("MAT0096");
        mat0096.addClass(Day.Tuesday, onePM, twoHours);
        mat0096.addClass(Day.Thursday, onePM, twoHours);
        addSubject(mat0096);

        Subject mat0154 = new Subject("MAT0154");
        mat0154.addClass(Day.Wednesday, onePM, twoHours);
        mat0154.addClass(Day.Friday, onePM, twoHours);
        addSubject(mat0154);

        Subject estat0011 = new Subject("ESTAT0011");
        estat0011.addClass(Day.Monday, threePM, twoHours);
        estat0011.addClass(Day.Wednesday, threePM, twoHours);
        addSubject(estat0011);

        Subject comp0409 = new Subject("COMP0409");
        comp0409.addClass(Day.Tuesday, threePM, twoHours);
        comp0409.addClass(Day.Thursday, threePM, twoHours);
        addSubject(comp0409);

        Subject comp0415 = new Subject("COMP0415");
        comp0415.addClass(Day.Monday, fivePM, twoHours);
        comp0415.addClass(Day.Wednesday, fivePM, twoHours);
        addSubject(comp0415);

        Subject comp0412 = new Subject("COMP0412");
        comp0412.addClass(Day.Tuesday, fivePM, twoHours);
        comp0412.addClass(Day.Thursday, fivePM, twoHours);
        addSubject(comp0412);

        Subject comp0417 = new Subject("COMP0417");
        comp0417.addClass(Day.Friday, fivePM, twoHours);
        addSubject(comp0417);
    }
}
