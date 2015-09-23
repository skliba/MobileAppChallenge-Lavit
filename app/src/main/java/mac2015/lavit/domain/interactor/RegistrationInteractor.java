package mac2015.lavit.domain.interactor;

import mac2015.lavit.domain.models.RegistrationModel;
import mac2015.lavit.domain.models.User;

/**
 * Created by noxqs on 23.09.15..
 */
public interface RegistrationInteractor {

    void register(Callback callback, RegistrationModel model);

    interface Callback{
        void onRegistrationSuccess(User user);

        void onRegistrationError(String msg);
    }
}
