package mac2015.lavit.domain.interactor;

import android.app.Activity;
import android.content.Intent;

import mac2015.lavit.domain.manager.GoogleApiManager;
import mac2015.lavit.domain.models.User;

/**
 * Created by noxqs on 23.09.15..
 */
public interface LoginGoogleInteractor {


    void login(final Callback callback, Activity activity, GoogleApiManager googleApiManager);

    interface Callback {
        void onLoginAuthorizationRequest(Intent intent);

        void onLoginError(String msg);

        void onLoginSuccess(User user);
    }
}
