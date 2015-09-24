package mac2015.lavit.ui.view;

import mac2015.lavit.domain.models.User;

/**
 * Created by dmacan on 22.9.2015..
 */
public interface RegistrationView extends View {

    String getEmail();

    String getPassword();

    String getPasswordConfirm();

    String getFirstName();

    String getLastName();

    void showEmailError(String error);

    void showPasswordError(String error);

    void showPasswordConfirmError(String error);

    void showFirstNameError(String error);

    void showLastNameError(String error);

    void clearEmailError();

    void clearPasswordError();

    void clearPasswordConfirmError();

    void clearFirstNameError();

    void clearLastNameError();

    void showLoading(String message);

    void hideLoading();

    void showError(String message);

    void proceed(User user);

    void lock();

    void unlock();

}
