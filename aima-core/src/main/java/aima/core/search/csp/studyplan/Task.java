package aima.core.search.csp.studyplan;

/**
 * Uma tarefa com nome, dia, horário e duração.
 * Ex:
 *   Aula de Programação Imperativa na Segunda-feira
 *   às 13:00. Duração de 2 horas.
 */
public class Task {
    public final String name;
    public final DayTime startTime;
    public final Time duration;

    public Task(String name, DayTime startTime, Time duration) {
        this.name = name;
        this.startTime = startTime;
        this.duration = duration;
    }
}
