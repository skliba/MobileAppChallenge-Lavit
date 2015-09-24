package mac2015.lavit.ui.fragment;

import mac2015.lavit.app.BaseFragment;

/**
 * Created by dmacan on 23.9.2015..
 */
public abstract class FeedbackFragment<T> extends BaseTabFragment {

    private T feedback;

    public T getFeedback() {
        return feedback;
    }

    protected void setFeedback(T feedback) {
        this.feedback = feedback;
    }
}