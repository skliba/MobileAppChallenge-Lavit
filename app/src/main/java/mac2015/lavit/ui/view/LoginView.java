package mac2015.lavit.ui.view;

import android.content.Intent;

import mac2015.lavit.domain.models.User;

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

    void proceed(User user);

    void lock();

    void unlock();

    void requestAuthorization(Intent intent, int code);
}
