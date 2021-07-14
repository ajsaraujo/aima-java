package aima.core.search.csp.studyplan.models;

public class Sleep extends Task {
    public Sleep(DayTime startTime, Time duration) {
        super("Dormir", startTime, duration);
    }

    public String toString() {
        return new StringBuilder().append("         ").append(startTime.time).append(" - Dormir").toString();
    }
}
