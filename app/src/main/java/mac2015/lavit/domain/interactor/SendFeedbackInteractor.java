package mac2015.lavit.domain.interactor;

import mac2015.lavit.domain.models.FeedbackModel;

/**
 * Created by dmacan on 24.9.2015..
 */
public interface SendFeedbackInteractor {

    void sendFeedback(SendCallback callback, FeedbackModel model, String token);

    interface SendCallback {

        void onSendingError(String msg);

        void onSendingSuccess(String successMsg);
    }

}
