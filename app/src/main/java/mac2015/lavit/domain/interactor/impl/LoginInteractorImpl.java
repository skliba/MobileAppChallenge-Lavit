package mac2015.lavit.domain.interactor.impl;

import android.util.Log;

import mac2015.lavit.domain.interactor.AbstractInteractor;
import mac2015.lavit.domain.interactor.LoginInteractor;
import mac2015.lavit.domain.models.LoginModel;
import mac2015.lavit.domain.models.response.LoginResponse;
import mac2015.lavit.domain.models.response.Response;
import mac2015.lavit.domain.models.User;
import mac2015.lavit.domain.repository.ListRepository;
import mac2015.lavit.executor.InteractorExecutor;
import mac2015.lavit.executor.MainThreadExecutor;
import retrofit.RetrofitError;

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
        try{
            final Response<LoginResponse> loginResponse = listRepository.login(model);
            User user = new User();
            user.setEmail(model.getEmail());
            user.setPassword(model.getPassword());
            user.setToken(loginResponse.getMessage().getToken());
            Log.e("TOKEN", loginResponse.getMessage().getToken());
            notifySuccess(user);
        }
        catch(RetrofitError e){
            notifyError(e.getMessage());
        }


    }

    private void notifySuccess(final User user){
        getMainThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                callback.onLoginSuccess(user);
            }
        });
    }

    private void notifyError(final String msg){
        getMainThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                callback.onLoginError(msg);
            }
        });
    }

    @Override
    public void login(Callback callback, LoginModel model) {
        this.model = model;
        this.callback = callback;

        getInteractorExecutor().execute(this);
    }
}
