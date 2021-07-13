package aima.core.search.csp.studyplan.models;

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

    public String toString() {
        return day.toString() + " às " + time.toString();
    }

    public boolean before(DayTime other) {
        if (day != other.day) {
            return day.before(other.day);
        }

        if (hours() != other.hours()) {
            return hours() < other.hours();
        }

        return minutes() <= other.minutes();
    }

    public int hours() {
        return time.hours;
    }

    public int minutes() {
        return time.minutes;
    }
}
