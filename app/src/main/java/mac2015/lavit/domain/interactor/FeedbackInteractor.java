package mac2015.lavit.domain.interactor;

import mac2015.lavit.domain.models.FeedbackModel;

/**
 * Created by noxqs on 24.09.15..
 */
public interface FeedbackInteractor {

    void sendFeedback(SendCallback callback, FeedbackModel model);
    void fetchFeedback(FetchCallback callback, String token);

    interface SendCallback{

        void onSendingError(String msg);
        void onSendingSuccess(String successMsg);
    }
    interface FetchCallback{
        void onFetchError(String msg);
        void onFetchSuccess(Double rating);
    }

}
