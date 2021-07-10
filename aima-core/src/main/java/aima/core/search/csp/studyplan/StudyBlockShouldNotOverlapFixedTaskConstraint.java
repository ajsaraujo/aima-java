package aima.core.search.csp.studyplan;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;

import java.util.ArrayList;
import java.util.List;

/**
 * Verifica se os blocos de estudo se sobrepõem a um determinado
 * intervalo.
 */
public class StudyBlockShouldNotOverlapFixedTaskConstraint implements Constraint<StudyBlock, DayTime> {
    private final Task fixedTask;
    private final StudyBlock studyBlock;

    public StudyBlockShouldNotOverlapFixedTaskConstraint(Task fixedTask, StudyBlock studyBlock) {
        this.fixedTask = fixedTask;
        this.studyBlock = studyBlock;
    }

    public List<StudyBlock> getScope() {
        return List.of(studyBlock);
    }

    public boolean isSatisfiedWith(Assignment<StudyBlock, DayTime> assignment) {
        DayTime studyTime = assignment.getValue(studyBlock);
        Interval studyInterval = new Interval(studyTime, studyBlock.duration);

        return !fixedTask.getInterval().overlapsWith(studyInterval);
    }
}
