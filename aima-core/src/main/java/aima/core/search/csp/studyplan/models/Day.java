package aima.core.search.csp.studyplan.models;

public enum Day {
    Monday(1, "Segunda"),
    Tuesday(2, "Terça"),
    Wednesday(3, "Quarta"),
    Thursday(4, "Quinta"),
    Friday(5, "Sexta");
    // Saturday(6, "Sábado"),
    // Sunday(7, "Domingo");

    private final int index;
    private final String name;

    Day(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public String toString() {
        return this.name;
    }

    public boolean before(Day other) {
        return this.index < other.index;
    }

    Day next() {
        switch (index) {
            case 0:
            case 5:
                return Day.Monday;
            case 1:
                return Day.Tuesday;
            case 2:
                return Day.Wednesday;
            case 3:
                return Day.Thursday;
            case 4:
                return Day.Friday;
            /**
                case 6:
                return Day.Sunday;
                 **/
        }

        throw new RuntimeException("Expected day to have an index between 0 and 4");
    }
}
