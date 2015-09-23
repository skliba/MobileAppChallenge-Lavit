package mac2015.lavit.ui.presenter;


import android.app.Activity;
import android.content.Intent;

import mac2015.lavit.ui.view.LoginView;

/**
 * Created by dmacan on 22.9.2015..
 */
public interface LoginPresenter extends Presenter<LoginView> {

    void attemptLogin();

    boolean validateEmail();

    boolean validatePassword();

    void attemptGoogleLogin(Activity activity);

    void onAuthorizationResult(Activity activity, int requestCode, int resultCode, Intent data);
}
