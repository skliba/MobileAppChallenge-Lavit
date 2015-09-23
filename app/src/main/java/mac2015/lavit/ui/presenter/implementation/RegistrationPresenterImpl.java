package mac2015.lavit.ui.presenter.implementation;

import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

import mac2015.lavit.app.BasePresenter;
import mac2015.lavit.domain.interactor.RegistrationInteractor;
import mac2015.lavit.domain.manager.ValidationManager;
import mac2015.lavit.domain.models.RegistrationModel;
import mac2015.lavit.domain.models.User;
import mac2015.lavit.domain.util.Serializator;
import mac2015.lavit.ui.presenter.RegistrationPresenter;
import mac2015.lavit.ui.view.RegistrationView;

/**
 * Created by dmacan on 23.9.2015..
 */
public class RegistrationPresenterImpl extends BasePresenter implements RegistrationPresenter, RegistrationInteractor.Callback {

    private static final String TAG = "DAM_PRESENTER_REG";
    @Inject
    ValidationManager validationManager;
    @Inject
    RegistrationInteractor registrationInteractor;
    RegistrationView registrationView;

    public RegistrationPresenterImpl(Context context) {
        super(context);
    }

    @Override
    public void attemptRegistration() {
        Log.i(TAG, "Attempting reg");
        if (validateEmail() && validatePassword() && validatePasswordConfirm() && validateFirstName() && validateLastName()) {
            Log.i(TAG, "Locking");
            registrationView.lock();
            registrationView.showLoading("Creating your account");
            Log.i(TAG, "Loading");
            RegistrationModel registerModel = new RegistrationModel();
            registerModel.setEmail(registrationView.getEmail());
            registerModel.setPassword(registrationView.getPassword());
            registerModel.setFirstName(registrationView.getFirstName());
            registerModel.setLastName(registrationView.getLastName());
            registrationInteractor.register(this, registerModel);
            Log.i(TAG, "Registering");
        }
    }

    @Override
    public boolean validateEmail() {
        String msg = validationManager.validateEmail(registrationView.getEmail());
        if (msg == null) {
            registrationView.clearEmailError();
            return true;
        }
        registrationView.showEmailError(msg);
        return false;
    }

    @Override
    public boolean validatePassword() {
        String msg = validationManager.validatePassword(registrationView.getPassword());
        if (msg == null) {
            registrationView.clearPasswordError();
            return true;
        }
        registrationView.showPasswordError(msg);
        return false;
    }

    @Override
    public boolean validatePasswordConfirm() {
        String msg = validationManager.validatePasswordConfirm(registrationView.getPassword(), registrationView.getPasswordConfirm());
        if (msg == null) {
            registrationView.clearPasswordConfirmError();
            return true;
        }
        registrationView.showPasswordConfirmError(msg);
        return false;
    }

    @Override
    public boolean validateFirstName() {
        String msg = validationManager.validateFirstName(registrationView.getFirstName());
        if (msg == null) {
            registrationView.clearFirstNameError();
            return true;
        }
        registrationView.showFirstNameError(msg);
        return false;
    }

    @Override
    public boolean validateLastName() {
        String msg = validationManager.validateLastName(registrationView.getLastName());
        if (msg == null) {
            registrationView.clearLastNameError();
            return true;
        }
        registrationView.showLastNameError(msg);
        return false;
    }

    @Override
    public void initialize() {
        // Not needed
    }

    @Override
    public void onViewCreate() {
        // Not needed
    }

    @Override
    public void onViewResume() {
        // Not needed
    }

    @Override
    public void onViewPause() {
        // Not needed
    }

    @Override
    public void onViewDestroy() {
        // Not needed
    }

    @Override
    public void setView(RegistrationView view) {
        this.registrationView = view;
    }

    @Override
    public void onRegistrationSuccess(User user) {
        Log.i(TAG, "Response");
        this.registrationView.unlock();
        this.registrationView.hideLoading();
        this.registrationView.proceed(Serializator.serialize(user));
    }

    @Override
    public void onRegistrationError(String msg) {
        Log.i(TAG, "Error");
        this.registrationView.unlock();
        this.registrationView.hideLoading();
        this.registrationView.showError(msg);
    }

   /* @Override
    public void onRegister(RegisterResponse registerResponse) {
        Log.i(TAG, "Response");
        this.registrationView.unlock();
        this.registrationView.hideLoading();
        this.registrationView.proceed(Serializator.serialize(registerResponse));
    }

    @Override
    public void onError(String msg) {
        Log.i(TAG, "Error");
        this.registrationView.unlock();
        this.registrationView.hideLoading();
        this.registrationView.showError(msg);
    }*/
}

