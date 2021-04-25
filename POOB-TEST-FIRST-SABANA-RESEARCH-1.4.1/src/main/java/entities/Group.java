package entities;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private String name;
    private List<Project> projects;
    private Project project;


    public Group(String name) {
        this.name = name;
        this.projects = new ArrayList<>();
    }



    public void addProject(Project plan) {
        this.projects.add(plan);
    }

    public int countActiveProjects(){

        int proyA = 0;

        for(Project project: this.projects)
        {
            if(project.isActive())
            {
                proyA++;
            }
        }
        return proyA;
    }

    public int CountOpenActivities()
    {
        int openA=0;
        for (Project a: this.projects)
        {
            openA+=a.CountOpenActivities();
        }

        return openA;
    }

    public int CountClosedActivities()
    {
        int closedA=0;
        for (Project a: this.projects)
        {
            closedA+=a.CountClosedActivities();
        }

        return closedA;
    }


}
