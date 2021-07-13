package aima.core.search.csp.studyplan.models;

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
        return format(hours) + ":" + format(minutes);
    }

    public boolean equals(Time other) {
        return hours == other.hours && minutes == other.minutes;
    }

    private String format(int number) {
        String str = String.valueOf(number);
        String zeroPaddedNumber = '0' + str;
        return str.length() >= 2 ? str : zeroPaddedNumber;
    }
}
