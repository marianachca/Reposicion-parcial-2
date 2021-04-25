package entities;

import entities.ExecutiveSynthesizer;
import entities.Group;
import entities.Iteration;
import entities.SabanaResearchException;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *  La clase Proyect representa a un proyecto de la universidad
 */
public class Project {

    private String name;
    private LocalDate dateInit;
    private LocalDate dateEnd;
    private Group group;
    private List<Iteration> iterations;
    private List<Student> members;
    private StudentSynthesizer synthesizerstuden;
    private ExecutiveSynthesizer executiveSynthesizer;

    public Project(String name, LocalDate dateInit, LocalDate dateEnd, Group group) {
        this.name = name;
        this.dateInit = dateInit;
        this.dateEnd = dateEnd;
        this.group = group;
        this.iterations = new ArrayList<>();
        this.members = new ArrayList<>();

        group.addProject(this);
    }

    public void addIteration(Iteration iteration) {
        this.iterations.add(iteration);
    }

    /**
     * Obtiene la duración del proyecto
     * @return la duracion en dias del proyecto
     * @throws SabanaResearchException
     */
    public Duration getDuration() throws SabanaResearchException {

        Duration duration = Duration.ofDays(0);
        if(iterations.size() == 0)
        {
            throw new SabanaResearchException(SabanaResearchException.BAD_FORMED_PROJECT);
        }
        for(Iteration e:this.iterations)
        {
            Duration durationd = e.getDuration();
            duration = duration.plus(durationd);
        }
        return duration;
    }

    public List<List<String>> summarize(String tipoderesumen,Project p) {
        List<List<String>> sumary = new ArrayList<>();

        if(tipoderesumen.equals("Student"))
            sumary.add(synthesizerstuden.SynthetizerProject(p));

        if(tipoderesumen.equals("Iteration"))
            sumary.add(executiveSynthesizer.SynthetizerProject(p));

        return sumary;

    }
    public void setDateInit(LocalDate dateInit) {
        this.dateInit = dateInit;
    }

    public void setDateEnd(LocalDate dateEnd) {
        this.dateEnd = dateEnd;
    }

    public boolean isActive() {

        boolean active = false;

        if(LocalDate.now().isBefore(this.dateEnd) || LocalDate.now().isEqual(this.dateEnd) && LocalDate.now().isAfter(this.dateInit) || LocalDate.now().isEqual(this.dateInit))
        {
            for(Iteration i: this.iterations)
            {
                int activities= 0;
                activities+=i.CountOpenActivities();
                if(activities > 0)
                {
                    active = true;
                }

            }
        }
        return active;
    }
    
    public int CountOpenActivities()
    {
        int openA=0;
        for (Iteration a: this.iterations)
        {
            openA+=a.CountOpenActivities();
        }

        return openA;
    }

    public int CountClosedActivities()
    {
        int closedA=0;
        for (Iteration acti: this.iterations)
        {
            closedA+=acti.CountClosedActivities();
        }

        return closedA;
    }

    public List<Student> getMembers() {
        return members;
    }

    public List<Iteration> getIterations() {
        return iterations;
    }
}

