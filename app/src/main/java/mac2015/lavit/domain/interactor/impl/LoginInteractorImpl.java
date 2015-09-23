package mac2015.lavit.domain.interactor.impl;

import mac2015.lavit.domain.interactor.AbstractInteractor;
import mac2015.lavit.domain.interactor.LoginInteractor;
import mac2015.lavit.domain.models.LoginModel;
import mac2015.lavit.domain.repository.ListRepository;
import mac2015.lavit.executor.InteractorExecutor;
import mac2015.lavit.executor.MainThreadExecutor;

/**
 * Created by noxqs on 23.09.15..
 */
public class LoginInteractorImpl extends AbstractInteractor implements LoginInteractor {

    private ListRepository listRepository;
    private LoginModel model;
    private Callback callback;


    public LoginInteractorImpl(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, ListRepository listRepository) {
        super(interactorExecutor, mainThreadExecutor);
        this.listRepository = listRepository;

    }

    @Override
    public void run() {

    }

    @Override
    public void login(Callback callback, LoginModel model) {
        this.model = model;
        this.callback = callback;

        getInteractorExecutor().execute(this);
    }
}
