package aima.core.search.csp.studyplan.models;

import java.util.ArrayList;
import java.util.List;

public class ExtracurricularActivity {
    private String description;
    private int weeklyHours;
    private boolean officeHoursOnly;

    public ExtracurricularActivity(String description, int weeklyHours, boolean officeHoursOnly) {
        this.description = description;
        this.weeklyHours = weeklyHours;
        this.officeHoursOnly = officeHoursOnly;
    }

    public List<StudyBlock> getStudyBlocks() {
        int numOfThirtyMinuteBlocks = weeklyHours * 2;
        List<StudyBlock> list = new ArrayList<>();

        for (int i = 0; i < numOfThirtyMinuteBlocks; i++) {
            String uniqueDescription = new StringBuilder().append(description).append(" (").append(i).append(")").toString();
            StudyBlock studyBlock = new StudyBlock(uniqueDescription, new Time(0, 30));
            studyBlock.officeHoursOnly = officeHoursOnly;

            list.add(studyBlock);
        }

        return list;
    }
}
