package mac2015.lavit.domain.interactor;

import mac2015.lavit.domain.models.FeedbackModel;

/**
 * Created by noxqs on 24.09.15..
 */
public interface FetchFeedbackInteractor {

    void fetchFeedback(Callback callback, long projectId, String token,  double rating);

    interface Callback{
        void onFetchFeedbackSucces(FeedbackModel model);
        void onFetchFeedbackError(String msg);
    }
}
