package mac2015.lavit.domain.listeners;

import mac2015.lavit.domain.models.Response.RegistrationResponse;

/**
 * Created by noxqs on 23.09.15..
 */
public interface RegistrationListener {

    void onRegister(RegistrationResponse response);
    void onError(String msg);
}
