package mac2015.lavit.domain.repository.api.impl;

import android.util.Log;

import java.util.List;

import mac2015.lavit.domain.models.FeedbackModel;
import mac2015.lavit.domain.models.LoginModel;
import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.domain.models.RegistrationModel;
import mac2015.lavit.domain.models.SocialProfile;
import mac2015.lavit.domain.models.response.LoginResponse;
import mac2015.lavit.domain.models.response.RegistrationResponse;
import mac2015.lavit.domain.models.response.Response;
import mac2015.lavit.domain.models.response.ZokaResponse;
import mac2015.lavit.domain.repository.AppService;
import mac2015.lavit.domain.repository.ListRepository;
import mac2015.lavit.domain.repository.api.ApiManager;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.mime.TypedFile;

/**
 * Created by noxqs on 23.09.15..
 */
public class ApiManagerImpl implements ApiManager, ListRepository {

    private static final String TAG = "Network";
    private AppService service;
    private String baseUrl;

    public ApiManagerImpl(String baseUrl) {
        this.baseUrl = baseUrl;
        this.init();
    }

    private static final RestAdapter.Log LOG = new RestAdapter.Log() {
        @Override
        public void log(String message) {
            Log.d(TAG, message);
        }
    };

    public void init() {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(baseUrl)
                .setLog(LOG)
                .setLogLevel(RestAdapter.LogLevel.FULL);

        service = builder.build().create(AppService.class);
    }


    @Override
    public AppService getService() {
        return service;
    }

    @Override
    public Response<RegistrationResponse> register(RegistrationModel model) {
        return service.register(model);
    }

    @Override
    public Response<RegistrationResponse> registerGoogle(RegistrationModel model, SocialProfile profile) {
        try {
            int profileType = profile.getType();
            return service.register(model);
        } catch (RetrofitError e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Response<LoginResponse> login(LoginModel model) {
        return service.login(model);
    }

    @Override
    public Response<LoginResponse> loginGoogle(String accessToken, String id, String expires, String authProvider) {
        return null;
    }

    @Override
    public Response<List<ProjectModel>> fetchProjects(String token) {
        return service.fetchProjects(token);
    }

    @Override
    public String sendImage(TypedFile file, long projectId) {
        return service.uploadImage(projectId, file);
    }

    @Override
    public Response<ZokaResponse> fetchFeedback(String token, long projectId) {
        return service.fetchFeedback(token, projectId);
    }

    public Response<String> sendFeedback(FeedbackModel feedbackModel, String token, long projectId) {
        return service.sendFeedback(feedbackModel, token, projectId);
    }
}
