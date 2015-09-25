package mac2015.lavit.domain.interactor.impl;

import java.io.File;

import mac2015.lavit.domain.interactor.AbstractInteractor;
import mac2015.lavit.domain.interactor.SendFeedbackInteractor;
import mac2015.lavit.domain.models.FeedbackModel;
import mac2015.lavit.domain.models.response.Response;
import mac2015.lavit.domain.repository.api.impl.ApiManagerImpl;
import mac2015.lavit.executor.InteractorExecutor;
import mac2015.lavit.executor.MainThreadExecutor;
import retrofit.mime.TypedFile;

/**
 * Created by dmacan on 24.9.2015..
 */
public class SendFeedbackInteractorImpl extends AbstractInteractor implements SendFeedbackInteractor {

    private SendCallback sendCallback;
    private FeedbackModel feedbackModel;
    private String token;
    private long projectId;
    private ApiManagerImpl apiManager;
    private File file;

    public SendFeedbackInteractorImpl(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, ApiManagerImpl apiManager) {
        super(interactorExecutor, mainThreadExecutor);
        this.apiManager = apiManager;
    }

    @Override
    public void sendFeedback(SendCallback callback, FeedbackModel model, String token, long projectId) {
        this.sendCallback = callback;
        this.feedbackModel = model;
        this.token = token;
        this.file = model.getImage();
        this.projectId = projectId;
        getInteractorExecutor().execute(this);
    }

    @Override
    public void run() {
        try {
            TypedFile typedFile = new TypedFile("image/jpeg", file);
            Response<String> fileResponse = apiManager.sendImage(typedFile, projectId);
            if (fileResponse.getData() != null && !fileResponse.getData().equals("fail")) {
                feedbackModel.setImageUrl(fileResponse.getData());
            }
            Response<String> feedbackResponse = apiManager.sendFeedback(feedbackModel, token, projectId);
            notifySuccess(feedbackResponse.getMessage());
        } catch (Exception e) {
            notifyError();
        }
    }

    private void notifySuccess(String msg) {
        getMainThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                sendCallback.onSendingSuccess("msg");
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
