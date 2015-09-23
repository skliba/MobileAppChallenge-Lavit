package mac2015.lavit.domain.repository.api.impl;

import android.util.Log;

import mac2015.lavit.domain.repository.api.ApiManager;
import mac2015.lavit.domain.models.LoginModel;
import mac2015.lavit.domain.models.RegistrationModel;
import mac2015.lavit.domain.models.Response.LoginResponse;
import mac2015.lavit.domain.models.Response.RegistrationResponse;
import mac2015.lavit.domain.models.Response.Response;
import mac2015.lavit.domain.repository.AppService;
import mac2015.lavit.domain.repository.ListRepository;
import retrofit.RestAdapter;

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

    public void init(){
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
    public Response<LoginResponse> login(LoginModel model) {
        return service.login(model);
    }
}
