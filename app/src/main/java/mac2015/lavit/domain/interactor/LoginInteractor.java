package mac2015.lavit.domain.interactor;

import mac2015.lavit.domain.models.LoginModel;
import mac2015.lavit.domain.models.User;

/**
 * Created by noxqs on 23.09.15..
 */
public interface LoginInteractor {

    void login(Callback callback, LoginModel model);

    interface Callback{

        void onLoginError(String msg);
        void onLoginSuccess(User user);


    }
}
