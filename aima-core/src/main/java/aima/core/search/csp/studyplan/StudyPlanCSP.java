package aima.core.search.csp.studyplan;

import aima.core.search.csp.CSP;
import aima.core.search.csp.Domain;
import aima.core.search.csp.studyplan.constraints.StudyBlockShouldNotOverlapFixedTaskConstraint;
import aima.core.search.csp.studyplan.constraints.StudyBlockShouldNotOverlapOtherStudyBlockConstraint;
import aima.core.search.csp.studyplan.models.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Neste PSR, cada variável é uma tarefa com
 * duração pré-determinada. Desejamos atribuir
 * horários para a execução de cada tarefa de modo
 * que não haja sobreposição de horários.
 */
public class StudyPlanCSP extends CSP<StudyBlock, DayTime> {
    private final Time WAKE_UP_TIME = new Time(7, 0);
    private final Time GO_TO_BED_TIME = new Time(23, 0);

    private final List<Task> fixedTasks = new ArrayList<>();
    private final Domain<DayTime> domain = makeDomain();

    public StudyPlanCSP() {
        super();

        loadFixedTasks();
    }

    public void loadConstraints() {
        for (Task task : fixedTasks) {
            for (StudyBlock studyBlock : getVariables()) {
                addConstraint(new StudyBlockShouldNotOverlapFixedTaskConstraint(task, studyBlock));
            }
        }

        List<StudyBlock> studyBlocks = getVariables();

        for (int i = 0; i < studyBlocks.size(); i++) {
            StudyBlock one = studyBlocks.get(i);

            for (int j = i + 1; j < studyBlocks.size(); j++) {
                StudyBlock another = studyBlocks.get(j);

                addConstraint(new StudyBlockShouldNotOverlapOtherStudyBlockConstraint(one, another));
            }
        }
    }

    public void addSubject(Subject subject) {
        subject.getStudyBlocks().forEach(this::addStudyBlock);
        subject.getClasses().forEach(this::addFixedTask);
    }

    private void addStudyBlock(StudyBlock studyBlock) {
        addVariable(studyBlock);
        setDomain(studyBlock, domain);
    }

    private void addFixedTask(Task task) {
        fixedTasks.add(task);
    }

    private void loadFixedTasks() {
        Time sevenAM = new Time(7, 0);
        Time twelveAM = new Time(12, 0);
        Time sevenPM = new Time(19, 0);

        Time halfAnHour = new Time(0, 30);
        Time oneHour = new Time(1, 0);

        for (Day day: Day.values()) {
            Task breakfast = new Task("Café da Manhã", new DayTime(day, sevenAM), halfAnHour);
            Task lunch = new Task("Almoço", new DayTime(day, twelveAM), oneHour);
            Task dinner = new Task("Jantar", new DayTime(day, sevenPM), halfAnHour);
            Task sleep = new Task("Dormir", new DayTime(day, GO_TO_BED_TIME), calculateSleepDuration());

            fixedTasks.add(breakfast);
            fixedTasks.add(lunch);
            fixedTasks.add(dinner);
            fixedTasks.add(sleep);
        }
    }

    private Domain<DayTime> makeDomain() {
        ArrayList<DayTime> domain = new ArrayList<>();

        for (Day day : Day.values()) {
            for (int hours = WAKE_UP_TIME.hours; hours < GO_TO_BED_TIME.hours; hours++) {
                for (int minutes = 0; minutes < 60; minutes += 30) {
                    domain.add(new DayTime(day, new Time(hours, minutes)));
                }
            }
        }

        return new Domain<>(domain);
    }

    private Time calculateSleepDuration() {
        assert GO_TO_BED_TIME.minutes == 0 && WAKE_UP_TIME.minutes == 0;
        int HOURS_IN_A_DAY = 24;
        int sleepHours = HOURS_IN_A_DAY + WAKE_UP_TIME.hours - GO_TO_BED_TIME.hours;

        return new Time(sleepHours, 0);
    }
}
