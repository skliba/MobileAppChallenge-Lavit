package mac2015.lavit.ui.presenter;


import mac2015.lavit.ui.view.LoginView;

/**
 * Created by dmacan on 22.9.2015..
 */
public interface LoginPresenter extends Presenter<LoginView> {

    void attemptRegistration();

    boolean validateEmail();

    boolean validatePassword();

}
