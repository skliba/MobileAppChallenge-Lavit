package mac2015.lavit.domain.interactor;

import mac2015.lavit.domain.models.FeedbackModel;
import mac2015.lavit.domain.models.response.FeedbackResponse;

/**
 * Created by noxqs on 24.09.15..
 */
public interface FetchFeedbackInteractor {

    void fetchFeedback(Callback callback, long projectId, String token,  double rating);

    interface Callback{
        void onFetchFeedbackSucces(FeedbackResponse model);
        void onFetchFeedbackError(String msg);
    }
}
