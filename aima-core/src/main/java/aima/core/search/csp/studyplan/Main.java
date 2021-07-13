package aima.core.search.csp.studyplan;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.CSP;
import aima.core.search.csp.CspSolver;
import aima.core.search.csp.FlexibleBacktrackingSolver;
import aima.core.search.csp.studyplan.cases.Case;
import aima.core.search.csp.studyplan.cases.FirstCase;
import aima.core.search.csp.studyplan.models.DayTime;
import aima.core.search.csp.studyplan.models.Interval;
import aima.core.search.csp.studyplan.models.StudyBlock;

import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Case testCase = pickCase();

        testCase.readStudyGoals();

        StudyPlanCSP csp = testCase.toCSP();
        CspSolver solver = new FlexibleBacktrackingSolver().setAll();
        Optional<Assignment<StudyBlock, DayTime>> solution = solver.solve(csp);

        if (solution.isPresent()) {
            Assignment<StudyBlock, DayTime> assignment = solution.get();
            csp.showSchedule(assignment);
        } else {
            System.out.println("Nenhuma solução foi encontrada. Por favor, tente novamente.");
        }
    }

    private static Case pickCase() {
        return new FirstCase();
    }
}
