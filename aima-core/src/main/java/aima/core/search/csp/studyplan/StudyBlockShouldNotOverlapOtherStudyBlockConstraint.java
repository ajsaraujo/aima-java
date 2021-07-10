package aima.core.search.csp.studyplan;

import aima.core.search.csp.Assignment;
import aima.core.search.csp.Constraint;

import java.util.List;

public class StudyBlockShouldNotOverlapOtherStudyBlockConstraint implements Constraint<StudyBlock, DayTime> {
    private final StudyBlock one;
    private final StudyBlock another;

    public StudyBlockShouldNotOverlapOtherStudyBlockConstraint(StudyBlock one, StudyBlock another) {
        this.one = one;
        this.another = another;
    }

    public List<StudyBlock> getScope() {
        return List.of(one, another);
    }

    public boolean isSatisfiedWith(Assignment<StudyBlock, DayTime> assignment) {
        DayTime oneStudyTime = assignment.getValue(one);
        Interval oneStudyInterval = new Interval(oneStudyTime, one.duration);

        DayTime anotherStudyTime = assignment.getValue(another);
        Interval anotherStudyInterval = new Interval(anotherStudyTime, another.duration);

        return !oneStudyInterval.overlapsWith(anotherStudyInterval);
    }
}
