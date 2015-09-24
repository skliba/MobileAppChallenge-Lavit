package mac2015.lavit.domain.interactor.impl;

import android.telecom.Call;

import mac2015.lavit.domain.interactor.AbstractInteractor;
import mac2015.lavit.domain.interactor.FetchFeedbackInteractor;
import mac2015.lavit.domain.repository.ListRepository;
import mac2015.lavit.executor.InteractorExecutor;
import mac2015.lavit.executor.MainThreadExecutor;

/**
 * Created by noxqs on 24.09.15..
 */
public class FetchFeedbackInteractorImpl extends AbstractInteractor implements FetchFeedbackInteractor {

    private Callback callback;
    private long projectId;
    private String token;
    private double rating;
    private ListRepository listRepository;

    public FetchFeedbackInteractorImpl(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor) {
        super(interactorExecutor, mainThreadExecutor);
    }

    @Override
    public void fetchFeedback(Callback callback, long projectId, String token, double rating) {

    }

    @Override
    public void run() {

    }
}
