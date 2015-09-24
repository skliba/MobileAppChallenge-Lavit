package mac2015.lavit.domain.repository;

import java.util.List;

import mac2015.lavit.domain.models.LoginModel;
import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.domain.models.RegistrationModel;
import mac2015.lavit.domain.models.SocialProfile;
import mac2015.lavit.domain.models.response.LoginResponse;
import mac2015.lavit.domain.models.response.RegistrationResponse;
import mac2015.lavit.domain.models.response.Response;
import retrofit.mime.TypedFile;


/**
 * Created by noxqs on 23.09.15..
 */
public interface ListRepository {

    Response<RegistrationResponse> register(RegistrationModel model);

    Response<RegistrationResponse> registerGoogle(RegistrationModel model, SocialProfile profile);

    Response<LoginResponse> login(LoginModel model);

    Response<LoginResponse> loginGoogle(String accessToken, String id, String expires, String authProvider);

    Response<List<ProjectModel>> fetchProjects(String token);

    String sendImage(TypedFile file, String projectId);
}
