package aima.core.search.csp.studyplan.cases;

import aima.core.search.csp.studyplan.models.Day;
import aima.core.search.csp.studyplan.models.Subject;
import aima.core.search.csp.studyplan.models.Time;

public class FirstCase extends Case {
    public FirstCase() {
        Subject comp0455 = new Subject("COMP0455");
        comp0455.addClass(Day.Tuesday, new Time(15, 0), new Time(2, 0));
        comp0455.addClass(Day.Thursday, new Time(15, 0), new Time(2, 0));
        addSubject(comp0455);

        Subject comp0481 = new Subject("COMP0481");
        comp0481.addClass(Day.Thursday, new Time(19, 0), new Time(2, 0));
        addSubject(comp0481);

        Subject comp0408 = new Subject("COMP0408");
        comp0408.addClass(Day.Monday, new Time(21, 0), new Time(1, 0));
        comp0408.addClass(Day.Wednesday, new Time(21, 0), new Time(1, 0));
        addSubject(comp0408);
    }
}
