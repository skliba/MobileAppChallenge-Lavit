package mac2015.lavit.ui.presenter.implementation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import mac2015.lavit.app.BasePresenter;
import mac2015.lavit.domain.interactor.LoginGoogleInteractor;
import mac2015.lavit.domain.interactor.LoginInteractor;
import mac2015.lavit.domain.manager.GoogleApiManager;
import mac2015.lavit.domain.manager.Preferences;
import mac2015.lavit.domain.manager.ValidationManager;
import mac2015.lavit.domain.models.LoginModel;
import mac2015.lavit.domain.models.User;
import mac2015.lavit.ui.presenter.LoginPresenter;
import mac2015.lavit.ui.view.LoginView;

/**
 * Created by dmacan on 23.9.2015..
 */
public class LoginPresenterImpl extends BasePresenter implements LoginPresenter, LoginInteractor.Callback, LoginGoogleInteractor.Callback {

    private static final String TAG = "DAM_PRESENTER_LOGIN";
    @Inject
    ValidationManager validationManager;
    @Inject
    LoginInteractor loginInteractor;
    @Inject
    LoginGoogleInteractor loginGoogleInteractor;
    @Inject
    GoogleApiManager googleApiManager;
    @Inject
    Preferences preferences;
    LoginView loginView;

    public LoginPresenterImpl(Context context) {
        super(context);
    }

    @Override
    public void attemptLogin() {
        loginView.showLoading("Signing you in");
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
    public void attemptGoogleLogin(Activity activity) {
        loginGoogleInteractor.login(this, activity, googleApiManager);
    }

    @Override
    public void onAuthorizationResult(Activity activity, int requestCode, int resultCode, Intent data) {
        if (requestCode == 69) {
            attemptGoogleLogin(activity);
        }
    }

    @Override
    public void initialize() {
        //Not needed
    }

    @Override
    public void onViewCreate() {
        if (preferences.isUserStored()) {
            loginView.proceed(preferences.getUser());
        }
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
    public void onLoginAuthorizationRequest(Intent intent) {
        loginView.requestAuthorization(intent, 69);
    }

    @Override
    public void onLoginError(String msg) {
        loginView.unlock();
        loginView.hideLoading();
        loginView.showError(msg);
    }

    @Override
    public void onLoginSuccess(User user) {
        loginView.hideLoading();
        preferences.storeUser(user);
        preferences.storeToken(user.getToken());
        loginView.proceed(user);
    }


}
