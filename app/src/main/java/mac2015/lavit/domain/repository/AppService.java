package mac2015.lavit.domain.repository;

import java.util.List;

import mac2015.lavit.domain.models.FeedbackModel;
import mac2015.lavit.domain.models.LoginModel;
import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.domain.models.RegistrationModel;
import mac2015.lavit.domain.models.response.LoginResponse;
import mac2015.lavit.domain.models.response.RegistrationResponse;
import mac2015.lavit.domain.models.response.Response;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;

/**
 * Created by noxqs on 23.09.15..
 */
public interface AppService {

    @POST("/app/api/register")
    Response<RegistrationResponse> register(@Body RegistrationModel registrationModel);

    @POST("/app/api/login")
    Response<LoginResponse> login(@Body LoginModel loginModel);

    @GET("/app/api/projects")
    Response<List<ProjectModel>> fetchProjects();

    @GET("/app/api/projects")
    Response<List<ProjectModel>> fetchProjects(@Header("X-Api-Token") String token);

    @POST("/app/api/feedback")
    Response<String> sendFeedback(@Body FeedbackModel feedbackModel, @Header("X-Api-Token") String token);

    @Multipart
    @POST("/api/1/user/avatar")
    Response<String> uploadAvatar(@Header("Authorization") String token, @Part("avatar") TypedFile file);
}
