package aima.core.search.csp.studyplan;

import aima.core.search.csp.CSP;

import java.util.ArrayList;
import java.util.List;

public class StudyPlanCSP extends CSP<StudyBlock, DayTime> {
    public final List<Task> fixedTasks;

    public StudyPlanCSP(List<StudyBlock> studyBlocks, ArrayList<Task> classes) {
        super(studyBlocks);

        fixedTasks = new ArrayList<>();
        fixedTasks.addAll(classes);

        loadMeals();
    }

    private void loadMeals() {
        Time sevenAM = new Time(7, 0);
        Time twelveAM = new Time(12, 0);
        Time sevenPM = new Time(19, 0);
        Time halfAnHour = new Time(0, 30);
        Time oneHour = new Time(1, 0);

        for (Day day: Day.values()) {
            Task breakfast = new Task("Café da Manhã", new DayTime(day, sevenAM), halfAnHour);
            Task lunch = new Task("Almoço", new DayTime(day, twelveAM), oneHour);
            Task dinner = new Task("Jantar", new DayTime(day, sevenPM), halfAnHour);

            fixedTasks.add(breakfast);
            fixedTasks.add(lunch);
            fixedTasks.add(dinner);
        }
    }
}
