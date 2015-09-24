package mac2015.lavit.domain.repository;

import mac2015.lavit.domain.models.LoginModel;
import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.domain.models.RegistrationModel;
import mac2015.lavit.domain.models.response.LoginResponse;
import mac2015.lavit.domain.models.response.RegistrationResponse;
import mac2015.lavit.domain.models.response.Response;
import retrofit.http.Body;
import retrofit.http.POST;

/**
 * Created by noxqs on 23.09.15..
 */
public interface AppService {

    @POST("/app/api/register")
    Response<RegistrationResponse> register(@Body RegistrationModel registrationModel);

    @POST("/app/api/login")
    Response<LoginResponse> login(@Body LoginModel loginModel);

    //Response<ProjectResponse> fetchProjects(ProjectModel model);
}
