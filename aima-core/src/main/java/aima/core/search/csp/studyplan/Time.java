package aima.core.search.csp.studyplan;

/**
 * Horas e minutos. Pode ser utilizado
 * para representar HORÁRIO ou DURAÇÃO.
 */
public class Time {
    public final int hours;
    public final int minutes;

    public Time(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public String toString() {
        return hours + " " + minutes;
    }
}
