package mac2015.lavit.ui.presenter.implementation;

import android.content.Context;

import javax.inject.Inject;

import mac2015.lavit.app.BasePresenter;
import mac2015.lavit.domain.interactor.LoginInteractor;
import mac2015.lavit.domain.manager.ValidationManager;
import mac2015.lavit.domain.models.LoginModel;
import mac2015.lavit.domain.models.User;
import mac2015.lavit.ui.presenter.LoginPresenter;
import mac2015.lavit.ui.view.LoginView;

/**
 * Created by dmacan on 23.9.2015..
 */
public class LoginPresenterImpl extends BasePresenter implements LoginPresenter, LoginInteractor.Callback {

    @Inject
    ValidationManager validationManager;
    @Inject
    LoginInteractor loginInteractor;
    LoginView loginView;

    public LoginPresenterImpl(Context context) {
        super(context);
    }

    @Override
    public void attemptLogin() {
        LoginModel loginModel = new LoginModel();
        loginModel.setEmail(loginView.getEmail());
        loginModel.setPassword(loginView.getPassword());
        loginInteractor.login(this, loginModel);
    }

    @Override
    public boolean validateEmail() {
        String msg = validationManager.validateEmail(loginView.getEmail());
        if (msg == null) {
            loginView.clearEmailError();
            return true;
        }
        loginView.showEmailError(msg);
        return false;
    }

    @Override
    public boolean validatePassword() {
        String msg = validationManager.validatePassword(loginView.getPassword());
        if (msg == null) {
            loginView.clearPasswordError();
            return true;
        }
        loginView.showPasswordError(msg);
        return false;
    }

    @Override
    public void initialize() {
        //Not needed
    }

    @Override
    public void onViewCreate() {
        //Not needed
    }

    @Override
    public void onViewResume() {
        //Not needed
    }

    @Override
    public void onViewPause() {
        //Not needed
    }

    @Override
    public void onViewDestroy() {
        //Not needed
    }

    @Override
    public void setView(LoginView view) {
        this.loginView = view;
    }

    @Override
    public void onLoginError(String msg) {
        loginView.showError(msg);
    }

    @Override
    public void onLoginSuccess(User user) {
        loginView.proceed(user);
    }
}
