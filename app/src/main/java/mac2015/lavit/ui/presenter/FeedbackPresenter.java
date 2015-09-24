package mac2015.lavit.ui.presenter;

import mac2015.lavit.ui.view.FeedbackView;

/**
 * Created by dmacan on 24.9.2015..
 */
public interface FeedbackPresenter extends Presenter<FeedbackView> {

    void onProceedSelected();

    void onCancelSelected();

}
