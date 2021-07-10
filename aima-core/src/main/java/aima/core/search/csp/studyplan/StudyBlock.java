package aima.core.search.csp.studyplan;

import aima.core.search.csp.Variable;

/**
 * Bloco de estudo com nome e duração.
 * Ex:
 * COMP0421 - 1h30 minutos
 */
public class StudyBlock extends Variable {
    public final Time duration;

    public StudyBlock(String name, Time duration) {
        super(name);
        this.duration = duration;
    }
}
