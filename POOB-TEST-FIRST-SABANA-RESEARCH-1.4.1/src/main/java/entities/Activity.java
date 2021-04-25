package entities;

import java.time.Duration;

public abstract class Activity {

    public static final String ACTIVE_STATE = "active";
    public static final String CLOSED_STATE = "closed";
    public static final String PENDING_STATE = "pending";
    public static final String CANCELED_STATE = "canceled";

    private String name;
    private String state;
    private Iteration iteration;

    public Activity(String name, String state, Iteration iteration) {
        this.name = name;
        this.state = state;

        if (iteration != null) {
            this.iteration = iteration;
            this.iteration.addActivity(this);
        }
    }
    public boolean isActive() {
        boolean active = false;

        if(this.state.equals(ACTIVE_STATE) || this.state.equals(PENDING_STATE))
        {
            active = true;
        }
        return active;
    }
    public abstract Duration getDuration() throws SabanaResearchException;

}
