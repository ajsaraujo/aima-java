package aima.core.search.csp.studyplan.constraints;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;
import aima.core.search.csp.studyplan.models.DayTime;
import aima.core.search.csp.studyplan.models.Interval;
import aima.core.search.csp.studyplan.models.StudyBlock;
import aima.core.search.csp.studyplan.models.Task;

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
