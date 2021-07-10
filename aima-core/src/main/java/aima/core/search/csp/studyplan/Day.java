package aima.core.search.csp.studyplan;

public enum Day {
    Monday(1),
    Tuesday(2),
    Wednesday(3),
    Thursday(4),
    Friday(5),
    Saturday(6),
    Sunday(7);

    private final int index;

    Day(int index) {
        this.index = index;
    }

    Day next() {
        switch (index) {
            case 1:
                return Day.Tuesday;
            case 2:
                return Day.Wednesday;
            case 3:
                return Day.Thursday;
            case 4:
                return Day.Friday;
            case 5:
                return Day.Saturday;
            case 6:
                return Day.Sunday;
            case 7:
                return Day.Monday;
        }

        throw new RuntimeException("Expected day to have an index between 1 and 7");
    }
}
