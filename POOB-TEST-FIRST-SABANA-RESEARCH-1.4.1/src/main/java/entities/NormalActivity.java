package entities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class NormalActivity extends Activity {

    private List<Step> steps;

    public NormalActivity(String name, String state, Iteration iteration) {
        super(name, state, iteration);
        this.steps = new ArrayList<>();
    }

    public void addStep(Step step) {
        this.steps.add(step);
    }
  
    @Override
    public Duration getDuration() throws SabanaResearchException{

        Duration duration = Duration.ofDays(0);
        if(steps.size() == 0)
        {
            throw new SabanaResearchException(SabanaResearchException.BAD_FORMED_NORMAL_ACTIVITY);
        }
        for(Step s: this.steps)
        {
            duration=duration.plus(s.getDuration());
        }


        return duration;
    }
}
