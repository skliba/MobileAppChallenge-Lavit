package mac2015.lavit.domain.interactor.impl;

import android.telecom.Call;

import mac2015.lavit.domain.interactor.AbstractInteractor;
import mac2015.lavit.domain.interactor.FetchFeedbackInteractor;
import mac2015.lavit.domain.models.response.FeedbackResponse;
import mac2015.lavit.domain.models.response.Response;
import mac2015.lavit.domain.models.response.ZokaResponse;
import mac2015.lavit.domain.repository.ListRepository;
import mac2015.lavit.executor.InteractorExecutor;
import mac2015.lavit.executor.MainThreadExecutor;
import retrofit.RetrofitError;

/**
 * Created by noxqs on 24.09.15..
 */
public class FetchFeedbackInteractorImpl extends AbstractInteractor implements FetchFeedbackInteractor {

    private Callback callback;
    private long projectId;
    private String token;
    private double rating;
    private FeedbackResponse model;
    private ListRepository listRepository;


    public FetchFeedbackInteractorImpl(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, ListRepository listRepository) {
        super(interactorExecutor, mainThreadExecutor);
        this.listRepository = listRepository;
    }

    @Override
    public void fetchFeedback(Callback callback, long projectId, String token, double rating) {
        this.callback = callback;
        this.projectId = projectId;
        this.token = token;
        this.rating = rating;

        getInteractorExecutor().execute(this);
    }

    @Override
    public void run() {
        try{
            final Response<ZokaResponse> feedbackResponse = listRepository.fetchFeedback(token, projectId);
            model = feedbackResponse.getData().getFeedbackResponseZoka();
            notifySucces(model);
        }
        catch(RetrofitError e){
            notifyError(e.getMessage());
        }
    }

    private void notifySucces(final FeedbackResponse feedbackResponse) {
        getMainThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                callback.onFetchFeedbackSucces(feedbackResponse);
            }
        });
    }

    private void notifyError(final String msg) {
        getMainThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                callback.onFetchFeedbackError(msg);
            }
        });
    }
}
