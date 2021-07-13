package aima.core.search.csp.studyplan.models;

public class Interval {
    private final DayTime startTime;
    private final Time duration;

    public Interval(DayTime startTime, Time duration) {
        this.startTime = startTime;
        this.duration = duration;
    }

    public DayTime endTime() {
        Day day = startTime.day;
        int minutes = startTime.minutes() + duration.minutes;
        int hours = startTime.hours() + duration.hours;

        while (minutes > 60) {
            minutes -= 60;
            hours++;
        }

        while (hours > 24) {
            hours -= 24;
            day = day.next();
        }

        return new DayTime(day, new Time(hours, minutes));
    }

    public boolean overlapsWith(Interval other) {
        return this.startTime.before(other.endTime()) && other.startTime.before(this.endTime());
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder
            .append(startTime.day.toString())
            .append(" das ")
            .append(startTime.time.toString())
            .append(" às ")
            .append(endTime().time.toString());

        return stringBuilder.toString();
    }
}
