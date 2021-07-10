package aima.core.search.csp.studyplan;

import aima.core.search.csp.CSP;
import aima.core.search.csp.Domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Neste PSR, cada variável é uma tarefa com
 * duração pré-determinada. Desejamos atribuir
 * horários para a execução de cada tarefa de modo
 * que não haja sobreposição de horários.
 */
public class StudyPlanCSP extends CSP<StudyBlock, DayTime> {
    private final List<Task> fixedTasks = new ArrayList<>();
    private final Domain<DayTime> domain = makeDomain();

    public StudyPlanCSP() {
        super();

        loadMeals();
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

    private Domain<DayTime> makeDomain() {
        ArrayList<DayTime> domain = new ArrayList<>();

        for (Day day : Day.values()) {
            for (int hours = 0; hours < 24; hours++) {
                for (int minutes = 0; minutes < 60; minutes += 30) {
                    domain.add(new DayTime(day, new Time(hours, minutes)));
                }
            }
        }

        return new Domain<>(domain);
    }
}
