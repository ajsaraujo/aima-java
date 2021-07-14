package aima.core.search.csp.studyplan;

import aima.core.search.csp.studyplan.models.Task;

import java.util.Comparator;

public class TaskStartTimeComparator implements Comparator<Task> {
    public int compare(Task task1, Task task2) {
        if (task1.startTime.before(task2.startTime)) {
            return -1;
        }

        return +1;
    }
}
