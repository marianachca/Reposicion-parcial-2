import entities.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class DocumentedActivity extends Activity {

    private NormalActivity activity;
    private ISynthesizer synthesizer;
    private List<Question> questions;

    public DocumentedActivity(String name, String state, Iteration iteration, NormalActivity activity) {
        super(name, state, iteration);
        this.activity = activity;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        this.questions.add(question);
    }

    @Override
    public Duration getDuration() throws SabanaResearchException{

        Duration duration = Duration.ofDays(0);
        if(questions.size() == 0)
        {
            throw new SabanaResearchException(SabanaResearchException.BAD_FORMED_DOCUMENTED_ACTIVITY);
        }
        if(activity == null)
        {
            throw new SabanaResearchException(SabanaResearchException.BAD_FORMED_DOCUMENTED_ACTIVITY_WITHOUT_NORMAL_QUESTION);
        }
        for(Question q: this.questions)
        {
            duration=duration.plus(q.getDedication());
        }
        duration=duration.plus(activity.getDuration());

        return duration;
    }
}
