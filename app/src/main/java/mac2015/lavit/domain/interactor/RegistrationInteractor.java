package mac2015.lavit.domain.interactor;

import mac2015.lavit.domain.listeners.RegistrationListener;
import mac2015.lavit.domain.models.RegistrationModel;

/**
 * Created by noxqs on 23.09.15..
 */
public interface RegistrationInteractor {

    void register(RegistrationListener listener, RegistrationModel model);
}
