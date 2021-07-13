package aima.core.search.csp.studyplan;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.CspSolver;
import aima.core.search.csp.FlexibleBacktrackingSolver;
import aima.core.search.csp.studyplan.models.*;

import java.util.Optional;

public class FirstCase {
    public static void run() {
        StudyPlanCSP csp = new StudyPlanCSP();

        Subject comp0455 = new Subject("COMP0455");
        comp0455.addClass(Day.Tuesday, new Time(15, 0), new Time(2, 0));
        comp0455.addClass(Day.Thursday, new Time(15, 0), new Time(2, 0));
        comp0455.addStudyBlock(new Time(0, 30));

        Subject comp0481 = new Subject("COMP0481");
        comp0481.addClass(Day.Thursday, new Time(19, 0), new Time(2, 0));
        comp0481.addStudyBlock(new Time(1, 30));

        Subject comp0408 = new Subject("COMP0408");
        comp0408.addClass(Day.Monday, new Time(21, 0), new Time(1, 0));
        comp0408.addClass(Day.Wednesday, new Time(21, 0), new Time(1, 0));
        comp0408.addStudyBlock(new Time(0, 30));

        csp.addSubject(comp0455);
        csp.addSubject(comp0481);
        csp.addSubject(comp0408);

        csp.loadConstraints();

        CspSolver solver = new FlexibleBacktrackingSolver().setAll();
        Optional<Assignment<StudyBlock, DayTime>> solution = solver.solve(csp);

        if (solution.isPresent()) {
            Assignment<StudyBlock, DayTime> assignment = solution.get();

            for (StudyBlock variable : assignment.getVariables()) {
                System.out.println(variable.getName());

                DayTime dayTime = assignment.getValue(variable);
                System.out.println(dayTime.toString());
            }
        } else {
            System.out.println("Failed :(");
        }
    }
}
