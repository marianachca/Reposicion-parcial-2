package entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SabanaResearch {

    private List<Group> groups;
    private List<Summary> summaries;

    public SabanaResearch(List<Group> groups) {
        this.groups = groups;
        this.summaries = new ArrayList<>();
    }

    public int countOfGroups() {
        return this.groups.size();
    }

    public int countOfSummaries() {
        return this.summaries.size();
    }

    public void addSummary(Summary summary){this.summaries.add(summary);}

    public Summary createSummaryEntry() {
        int activitiePro=0,openA=0,closedP=0;


        for(Group g: this.groups)
        {
            activitiePro+=g.countActiveProjects();
            openA += g.CountOpenActivities();
            closedP += g.CountClosedActivities();
        }
        Summary s = new Summary(activitiePro,LocalDate.now(),openA,closedP);
        addSummary(s);
        return s;
    }

}
