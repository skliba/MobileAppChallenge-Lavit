package mac2015.lavit.ui.view;

/**
 * Created by dmacan on 22.9.2015..
 */
public interface LoginView extends View {

    String getEmail();

    String getPassword();

    void showEmailError(String error);

    void showPasswordError(String error);

    void clearEmailError();

    void clearPasswordError();

    void showLoading(String message);

    void hideLoading();

    void showError(String message);

    void proceed(String data);

    void lock();

    void unlock();

}
