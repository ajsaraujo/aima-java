package aima.core.search.csp.studyplan;

/**
 * Uma tarefa com nome, dia, horário e duração.
 * Ex:
 *   Aula de Programação Imperativa na Segunda-feira
 *   às 13:00. Duração de 2 horas.
 */
public class Task implements TimeInterval {
    private final String name;
    private final DayTime startTime;
    private final Time duration;

    public Task(String name, DayTime startTime, Time duration) {
        this.name = name;
        this.startTime = startTime;
        this.duration = duration;
    }

    public Interval getInterval() {
        return new Interval(startTime, duration);
    }
}
