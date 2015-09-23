package mac2015.lavit.domain.repository;

import mac2015.lavit.domain.models.LoginModel;
import mac2015.lavit.domain.models.RegistrationModel;
import mac2015.lavit.domain.models.Response.LoginResponse;
import mac2015.lavit.domain.models.Response.Response;

/**
 * Created by noxqs on 23.09.15..
 */
public interface ListRepository {

    Response<String> register(RegistrationModel model);

    Response<LoginResponse> login(LoginModel model);
}
