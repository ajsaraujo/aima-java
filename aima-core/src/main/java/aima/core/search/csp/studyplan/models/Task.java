package aima.core.search.csp.studyplan.models;

/**
 * Uma tarefa com nome, dia, horário e duração.
 * Ex:
 *   Aula de Programação Imperativa na Segunda-feira
 *   às 13:00. Duração de 2 horas.
 */
public class Task {
    public final String name;
    public final DayTime startTime;
    private final Time duration;

    public Task(String name, DayTime startTime, Time duration) {
        this.name = name;
        this.startTime = startTime;
        this.duration = duration;
    }

    /**
     * Retorna uma nova tarefa que inicia no horário de início de task1
     * e termina no horário final de task2.
     */
    public static Task agglutinate(Task task1, Task task2) {
        String name = task1.name;
        DayTime startTime = task1.startTime;
        Time duration = Time.getDuration(task1.startTime.time, task2.endTime().time);

        return new Task(name, startTime, duration);
    }

    /**
     * Retorna true se as tarefas tem o mesmo nome, e se uma começa quando a outra termina.
     */
    public boolean shouldBeAgglutinated(Task otherTask) {
        if (!name.equals(otherTask.name)) {
            return false;
        }

        return startTime.equals(otherTask.endTime()) || otherTask.startTime.equals(endTime());
    }

    public DayTime endTime() {
        return getInterval().endTime();
    }

    public String toString() {
        Interval interval = new Interval(startTime, duration);

        return new StringBuilder().append(interval).append(" - ").append(name).toString();
    }

    public Interval getInterval() {
        return new Interval(startTime, duration);
    }
}
