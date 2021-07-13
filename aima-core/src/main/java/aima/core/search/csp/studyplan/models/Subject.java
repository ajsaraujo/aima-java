package aima.core.search.csp.studyplan.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Uma disciplina, com nome,
 * horários de aula e blocos de estudo.
 */
public class Subject {
    public final String name;
    private final ArrayList<Task> classes;
    private final ArrayList<StudyBlock> studyBlocks;

    public Subject(String name) {
        this.name = name;

        this.classes = new ArrayList<>();
        this.studyBlocks = new ArrayList<>();
    }

    public void addClass(Day day, Time startTime, Time duration) {
        classes.add(new Task(name, new DayTime(day, startTime), duration));
    }

    public void addStudyBlock(Time duration) {
        studyBlocks.add(new StudyBlock("Estudar " + name + " (" + (studyBlocks.size() + 1 ) + ")", duration));
    }

    public List<Task> getClasses() {
        return Collections.unmodifiableList(classes);
    }

    public List<StudyBlock> getStudyBlocks() {
        return Collections.unmodifiableList(studyBlocks);
    }
}
