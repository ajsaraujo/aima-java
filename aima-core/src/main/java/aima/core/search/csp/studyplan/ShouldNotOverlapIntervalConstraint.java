package aima.core.search.csp.studyplan;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;

import java.util.ArrayList;
import java.util.List;

/**
 * Verifica se os blocos de estudo se sobrepõem a um determinado
 * intervalo.
 */
public class ShouldNotOverlapIntervalConstraint implements Constraint<StudyBlock, DayTime> {
    private final List<StudyBlock> studyBlocks = new ArrayList<>();
    private final Interval interval;

    public ShouldNotOverlapIntervalConstraint(Interval interval) {
        this.interval = interval;
    }

    public void addVariable(StudyBlock studyBlock) {
        this.studyBlocks.add(studyBlock);
    }

    public List<StudyBlock> getScope() {
        return this.studyBlocks;
    }

    public boolean isSatisfiedWith(Assignment<StudyBlock, DayTime> assignment) {
        for (StudyBlock studyBlock : studyBlocks) {
            DayTime dayTime = assignment.getValue(studyBlock);
            Interval studyInterval = new Interval(dayTime, studyBlock.duration);

            if (studyInterval.overlapsWith(interval)) {
                return false;
            }
        }

        return true;
    }
}
