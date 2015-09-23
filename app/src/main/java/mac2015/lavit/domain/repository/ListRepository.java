package mac2015.lavit.domain.repository;

import mac2015.lavit.domain.models.LoginModel;
import mac2015.lavit.domain.models.RegistrationModel;
<<<<<<< HEAD
import mac2015.lavit.domain.models.SocialProfile;
import mac2015.lavit.domain.models.response.LoginResponse;
import mac2015.lavit.domain.models.response.RegistrationResponse;
import mac2015.lavit.domain.models.response.Response;
=======
import mac2015.lavit.domain.models.Response.LoginResponse;
import mac2015.lavit.domain.models.Response.Response;
>>>>>>> 987e4d52f319852a65c150f4c1893392992b2e7a

/**
 * Created by noxqs on 23.09.15..
 */
public interface ListRepository {

    Response<String> register(RegistrationModel model);

    Response<RegistrationResponse> registerGoogle(RegistrationModel model, SocialProfile profile);

    Response<LoginResponse> login(LoginModel model);

    Response<LoginResponse> loginGoogle(String accessToken, String id, String expires, String authProvider);
}
