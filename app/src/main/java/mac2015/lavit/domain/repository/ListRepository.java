package mac2015.lavit.domain.repository;

import mac2015.lavit.domain.models.LoginModel;
import mac2015.lavit.domain.models.RegistrationModel;
import mac2015.lavit.domain.models.SocialProfile;
import mac2015.lavit.domain.models.response.LoginResponse;
import mac2015.lavit.domain.models.response.RegistrationResponse;
import mac2015.lavit.domain.models.response.Response;


/**
 * Created by noxqs on 23.09.15..
 */
public interface ListRepository {

    Response<String> register(RegistrationModel model);

    Response<String> registerGoogle(RegistrationModel model, SocialProfile profile);

    Response<LoginResponse> login(LoginModel model);

    Response<LoginResponse> loginGoogle(String accessToken, String id, String expires, String authProvider);
}
