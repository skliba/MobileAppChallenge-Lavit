package mac2015.lavit.ui.presenter;


import mac2015.lavit.ui.view.RegistrationView;

/**
 * Created by dmacan on 22.9.2015..
 */
public interface RegistrationPresenter extends Presenter<RegistrationView> {

    void attemptRegistration();

    boolean validateEmail();

    boolean validatePassword();

    boolean validatePasswordConfirm();

    boolean validateFirstName();

    boolean validateLastName();

}
