package mac2015.lavit.domain.interactor.impl;

import android.util.Log;

import java.util.List;

import mac2015.lavit.domain.interactor.AbstractInteractor;
import mac2015.lavit.domain.interactor.ProjectInteractor;
import mac2015.lavit.domain.models.ProjectModel;
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
public class ProjectInteractorImpl extends AbstractInteractor implements ProjectInteractor {

    private static final String TAG = "DAM_INTER_PROJ";
    private ListRepository listRepository;
    private Callback callback;
    private String token;


    public ProjectInteractorImpl(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, ListRepository listRepository) {
        super(interactorExecutor, mainThreadExecutor);
        this.listRepository = listRepository;
    }

    @Override
    public void run() {
        try {
            Response<List<ProjectModel>> projectResponse = listRepository.fetchProjects(token);
            Log.i(TAG, "fetched responses");
            List<ProjectModel> models = projectResponse.getData();
            Log.i(TAG, "Got data");
            for (ProjectModel model : models) {
                Log.i(TAG, "Fetching feedback for model " + model.getId());
                final Response<ZokaResponse> feedbackResponse = listRepository.fetchFeedback(token, model.getId());
                for (FeedbackResponse response : feedbackResponse.getData().getFeedbackResponseZoka()) {
                    model.setFeedbackModel(response);
                }
            }
            Log.i(TAG, "Successy");
            notifySuccess(models);
        } catch (RetrofitError e) {
            Log.e(TAG, "Retrofit error", e);
            notifyError(e.getMessage());
        }

    }

    private void notifyError(final String message) {
        getMainThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                callback.onProjectFetchFail(message);
            }
        });
    }

    private void notifySuccess(final List<ProjectModel> data) {
        getMainThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                callback.onProjectFetchSuccess(data);
            }
        });
    }

    @Override
    public void fetchProjects(Callback callback, String token) {
        this.callback = callback;
        this.token = token;
        getInteractorExecutor().execute(this);
    }
}
