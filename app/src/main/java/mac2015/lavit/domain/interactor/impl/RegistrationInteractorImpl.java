package mac2015.lavit.domain.interactor.impl;

import mac2015.lavit.domain.interactor.AbstractInteractor;
import mac2015.lavit.domain.interactor.RegistrationInteractor;
import mac2015.lavit.domain.listeners.RegistrationListener;
import mac2015.lavit.domain.models.RegistrationModel;
import mac2015.lavit.domain.models.Response.RegistrationResponse;
import mac2015.lavit.executor.InteractorExecutor;
import mac2015.lavit.executor.MainThreadExecutor;

/**
 * Created by noxqs on 23.09.15..
 */
public class RegistrationInteractorImpl extends AbstractInteractor implements RegistrationInteractor, RegistrationListener {

    private RegistrationListener listener;

    public RegistrationInteractorImpl(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor) {
        super(interactorExecutor, mainThreadExecutor);
    }

    @Override
    public void register(RegistrationListener listener, RegistrationModel model) {
        this.listener = listener;
    }

    @Override
    public void run() {
        // logika
    }

    @Override
    public void onRegister(final RegistrationResponse response) {
        getMainThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                listener.onRegister(response);
            }
        });
    }

    @Override
    public void onError(String msg) {
        
    }
}
