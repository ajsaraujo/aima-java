package aima.core.search.csp.studyplan;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.CSP;
import aima.core.search.csp.Domain;
import aima.core.search.csp.studyplan.constraints.StudyBlockShouldNotOverlapFixedTaskConstraint;
import aima.core.search.csp.studyplan.constraints.StudyBlockShouldNotOverlapOtherStudyBlockConstraint;
import aima.core.search.csp.studyplan.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;

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

    public void addExtraCurricularActivity(ExtracurricularActivity activity) {
        activity.getStudyBlocks().forEach(this::addStudyBlock);
    }

    public void showSchedule(Assignment<StudyBlock, DayTime> assignment) {
        for (Day day : Day.values()) {
            System.out.println(day.name.toUpperCase());

            List<Task> todaysTasks = getTodaysFixedTasks(day);

            for (StudyBlock studyBlock : assignment.getVariables()) {
                DayTime dayTime = assignment.getValue(studyBlock);

                if (dayTime.day.equals(day)) {
                    todaysTasks.add(new Task(studyBlock.subjectName, dayTime, studyBlock.duration));
                }
            }

            todaysTasks.sort(new TaskStartTimeComparator());

            for (Task task : agglutinate(todaysTasks)) {
                System.out.println(task.toString());
            }

            System.out.println("");
        }
    }

    private List<Task> agglutinate(List<Task> tasks) {
        List<Task> agglutinatedList = new ArrayList<>();

        for (Task task : tasks) {
            if (agglutinatedList.isEmpty()) {
                agglutinatedList.add(task);
            } else {
                int lastIndex = agglutinatedList.size() - 1;
                Task lastTask = agglutinatedList.get(lastIndex);

                if (task.shouldBeAgglutinated(lastTask)) {
                    agglutinatedList.set(lastIndex, Task.agglutinate(lastTask, task));
                } else {
                    agglutinatedList.add(task);
                }
            }
        }

        return agglutinatedList;
    }

    private List<Task> getTodaysFixedTasks(Day day) {
        List<Task> todaysFixedTasks = new ArrayList<>();

        for (Task task : fixedTasks) {
            if (task.startTime.day.equals(day)) {
                todaysFixedTasks.add(task);
            }
        }

        return todaysFixedTasks;
    }

    private void addStudyBlock(StudyBlock studyBlock) {
        addVariable(studyBlock);
        loadDomain(studyBlock);
    }

    private void loadDomain(StudyBlock studyBlock) {
        ArrayList<DayTime> subdomain = new ArrayList<>();

        for (DayTime value : domain) {
            Interval interval = new Interval(value, studyBlock.duration);

            boolean studyWillEndBeforeMidnight = !interval.endsPastMidnight();
            boolean studyWillEndBeforeBedTime = !endsBeforeBedTime(interval);
            boolean startTimeIsValid = studyWillEndBeforeMidnight && studyWillEndBeforeBedTime;

            if (startTimeIsValid) {
                subdomain.add(value);
            }
        }

        setDomain(studyBlock, new Domain<>(subdomain));
    }

    private boolean endsBeforeBedTime(Interval interval) {
        Day day = interval.endTime().day;
        DayTime bedTime = new DayTime(day, GO_TO_BED_TIME);

        return bedTime.before(interval.endTime());
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
            Task sleep = new Sleep(new DayTime(day, GO_TO_BED_TIME), calculateSleepDuration());

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
