package mac2015.lavit.domain.interactor.impl;

import mac2015.lavit.domain.interactor.AbstractInteractor;
import mac2015.lavit.domain.interactor.RegistrationInteractor;
import mac2015.lavit.domain.models.LoginModel;
import mac2015.lavit.domain.models.RegistrationModel;
import mac2015.lavit.domain.models.response.LoginResponse;
import mac2015.lavit.domain.models.response.RegistrationResponse;
import mac2015.lavit.domain.models.response.Response;
import mac2015.lavit.domain.models.User;
import mac2015.lavit.domain.repository.ListRepository;
import mac2015.lavit.executor.InteractorExecutor;
import mac2015.lavit.executor.MainThreadExecutor;
import retrofit.RetrofitError;

/**
 * Created by noxqs on 23.09.15..
 */
public class RegistrationInteractorImpl extends AbstractInteractor implements RegistrationInteractor {

    private ListRepository listRepository;
    private RegistrationModel registrationModel;
    private LoginModel loginModel;
    private Callback callback;

    public RegistrationInteractorImpl(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, ListRepository listRepository) {
        super(interactorExecutor, mainThreadExecutor);
        this.listRepository = listRepository;

    }

    @Override
    public void run() {
        try{
            final Response<RegistrationResponse> registrationResponse = listRepository.register(registrationModel);
            final Response<LoginResponse> loginResponse = listRepository.login(loginModel);
            User user = new User();
            user.setPassword(loginModel.getPassword());
            user.setEmail(loginModel.getEmail());
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
                callback.onRegistrationSuccess(user);
            }
        });
    }

    private void notifyError(final String msg){
        getMainThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                callback.onRegistrationError(msg);
            }
        });
    }

    @Override
    public void register(Callback callback, RegistrationModel model) {
        this.registrationModel = model;
        this.callback = callback;

        loginModel = new LoginModel();
        loginModel.setEmail(registrationModel.getEmail());
        loginModel.setPassword(registrationModel.getPassword());

        getInteractorExecutor().execute(this);
    }
}
