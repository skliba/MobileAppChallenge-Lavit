package mac2015.lavit.ui.presenter.implementation;

import android.content.Context;

import mac2015.lavit.app.BasePresenter;
import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.ui.presenter.FeedbackPresenter;
import mac2015.lavit.ui.view.FeedbackView;

/**
 * Created by dmacan on 24.9.2015..
 */
public class FeedbackPresenterImpl extends BasePresenter implements FeedbackPresenter {

    private ProjectModel project;
    private FeedbackView feedbackView;
    private int[] pages;
    private int currentPage = 0;

    public FeedbackPresenterImpl(Context context) {
        super(context);
    }

    @Override
    public void onProceedSelected() {
        if (currentPage < pages.length - 1) {
            currentPage += 1;
            updatePage();
            feedbackView.turnPage(currentPage);
        } else {
            // TODO: ask user if he/she is sure about finishing
            feedbackView.showStatus("Sending feedback");
            // TODO: send feedback
            feedbackView.finishFeedback();
        }
    }

    @Override
    public void onCancelSelected() {
        if (currentPage > 0) {
            currentPage -= 1;
            updatePage();
            feedbackView.turnPage(currentPage);
        } else {
            feedbackView.cancelFeedback(); // TODO: ask user if he/she is sure about canceling
        }
    }

    private void updatePage() {
        feedbackView.toggleFirstPage(currentPage == 0);
        feedbackView.toggleLastPage(currentPage == pages.length - 1);
    }

    @Override
    public void initialize() {
        project = feedbackView.getProject();
        pages = new int[]{0, 1, 2}; // TODO get types
    }

    @Override
    public void onViewCreate() {
        feedbackView.enableFeedbacks(pages);
        updatePage();
    }

    @Override
    public void onViewResume() {

    }

    @Override
    public void onViewPause() {

    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void setView(FeedbackView view) {
        this.feedbackView = view;
    }
}
