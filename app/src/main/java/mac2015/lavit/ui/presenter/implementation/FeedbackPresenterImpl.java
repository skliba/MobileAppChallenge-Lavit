package mac2015.lavit.ui.presenter.implementation;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

import mac2015.lavit.app.BasePresenter;
import mac2015.lavit.domain.interactor.SendFeedbackInteractor;
import mac2015.lavit.domain.manager.Preferences;
import mac2015.lavit.domain.models.FeedbackModel;
import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.ui.presenter.FeedbackPresenter;
import mac2015.lavit.ui.view.FeedbackView;

/**
 * Created by dmacan on 24.9.2015..
 */
public class FeedbackPresenterImpl extends BasePresenter implements FeedbackPresenter, SendFeedbackInteractor.SendCallback {

    private static final String TAG = "DAM_PRES_FEDBACK";
    @Inject
    SendFeedbackInteractor sendFeedbackInteractor;
    @Inject
    Preferences preferences;

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
            sendFeedbackInteractor.sendFeedback(this, createModel(), preferences.getToken(), project.getId());
            feedbackView.finishFeedback();
        }
    }

    private FeedbackModel createModel() {
        FeedbackModel model = new FeedbackModel();
        model.setComment(feedbackView.getFeedbackComment());
        model.setImage(feedbackView.getFeedbackPhoto());
        if (feedbackView.getFeedbackLocation() != null) {
            model.setLatitude((int) feedbackView.getFeedbackLocation().getLatitude());
            model.setLongitude((int) feedbackView.getFeedbackLocation().getLongitude());
        }
        model.setRating(feedbackView.getFeedbackRating());
        return model;
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
        pages = project.getFeedbackTypes();
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

    @Override
    public void onSendingError(String msg) {
        Log.e(TAG, "Error:" + msg);
    }

    @Override
    public void onSendingSuccess(String successMsg) {
        Log.i(TAG, "Success sendinf");
    }
}
