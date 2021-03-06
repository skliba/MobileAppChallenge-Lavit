package mac2015.lavit.domain.repository;

import mac2015.lavit.domain.models.LoginModel;
import mac2015.lavit.domain.models.RegistrationModel;
import mac2015.lavit.domain.models.Response.LoginResponse;
import mac2015.lavit.domain.models.Response.RegistrationResponse;
import mac2015.lavit.domain.models.Response.Response;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by noxqs on 23.09.15..
 */
public interface AppService {

    Response<RegistrationResponse> register(@Body RegistrationModel registrationModel);

    @POST("/test")
    Response<LoginResponse> login(@Body LoginModel loginModel);
}
