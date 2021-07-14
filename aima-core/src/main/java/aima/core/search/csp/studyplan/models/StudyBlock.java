package aima.core.search.csp.studyplan.models;

import aima.core.search.csp.Variable;

/**
 * Bloco de estudo com nome e duração.
 * Ex:
 * COMP0421 - 1h30 minutos
 */
public class StudyBlock extends Variable {
    public final Time duration;
    public String subjectName;

    public StudyBlock(String name, Time duration) {
        super(name);

        String[] parts = name.split(" ");

        this.subjectName = parts[0] + " " + parts[1];
        this.duration = duration;
    }
}
