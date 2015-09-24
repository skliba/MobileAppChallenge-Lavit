package mac2015.lavit.domain.interactor.impl;

import mac2015.lavit.domain.interactor.AbstractInteractor;
import mac2015.lavit.domain.interactor.SendFeedbackInteractor;
import mac2015.lavit.domain.models.FeedbackModel;
import mac2015.lavit.domain.models.response.Response;
import mac2015.lavit.domain.repository.api.impl.ApiManagerImpl;
import mac2015.lavit.executor.InteractorExecutor;
import mac2015.lavit.executor.MainThreadExecutor;

/**
 * Created by dmacan on 24.9.2015..
 */
public class SendFeedbackInteractorImpl extends AbstractInteractor implements SendFeedbackInteractor {

    private SendCallback sendCallback;
    private FeedbackModel feedbackModel;
    private String token;
    private ApiManagerImpl apiManager;

    public SendFeedbackInteractorImpl(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, ApiManagerImpl apiManager) {
        super(interactorExecutor, mainThreadExecutor);
        this.apiManager = apiManager;
    }

    @Override
    public void sendFeedback(SendCallback callback, FeedbackModel model, String token) {
        this.sendCallback = callback;
        this.feedbackModel = model;
        this.token = token;
        getInteractorExecutor().execute(this);
    }

    @Override
    public void run() {
        try {
            Response<String> response = apiManager.sendFeedback(feedbackModel, token);
            notifySuccess();
        } catch (Exception e) {
            notifyError();
        }
    }

    private void notifySuccess() {
        getMainThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                sendCallback.onSendingSuccess("Feedback was successfuly sent");
            }
        });
    }

    private void notifyError() {
        getMainThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                sendCallback.onSendingError("Failed to send feedback");
            }
        });
    }
}
