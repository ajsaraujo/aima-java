package aima.core.search.csp.studyplan;

/**
 * Dia e hora.
 * Ex: Segunda feira às 12h30.
 */
public class DayTime {
    private final Day day;
    private final Time time;

    public DayTime(Day day, Time time) {
        this.day = day;
        this.time = time;
    }
}
