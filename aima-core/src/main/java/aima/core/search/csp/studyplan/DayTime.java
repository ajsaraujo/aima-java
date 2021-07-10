package aima.core.search.csp.studyplan;

/**
 * Dia e hora.
 * Ex: Segunda feira às 12h30.
 */
public class DayTime {
    public final Day day;
    public final Time time;

    public DayTime(Day day, Time time) {
        this.day = day;
        this.time = time;
    }
}
