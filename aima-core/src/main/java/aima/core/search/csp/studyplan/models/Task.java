package aima.core.search.csp.studyplan.models;

/**
 * Uma tarefa com nome, dia, horário e duração.
 * Ex:
 *   Aula de Programação Imperativa na Segunda-feira
 *   às 13:00. Duração de 2 horas.
 */
public class Task {
    private final String name;
    public final DayTime startTime;
    private final Time duration;

    public Task(String name, DayTime startTime, Time duration) {
        this.name = name;
        this.startTime = startTime;
        this.duration = duration;
    }

    public String toString() {
        Interval interval = new Interval(startTime, duration);

        return new StringBuilder().append(interval).append(" - ").append(name).toString();
    }

    public Interval getInterval() {
        return new Interval(startTime, duration);
    }
}
