package mac2015.lavit.domain.interactor.impl;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import mac2015.lavit.domain.interactor.AbstractInteractor;
import mac2015.lavit.domain.interactor.LoginGoogleInteractor;
import mac2015.lavit.domain.interactor.LoginInteractor;
import mac2015.lavit.domain.manager.GoogleApiManager;
import mac2015.lavit.domain.models.LoginModel;
import mac2015.lavit.domain.models.RegistrationModel;
import mac2015.lavit.domain.models.SocialProfile;
import mac2015.lavit.domain.models.User;
import mac2015.lavit.domain.models.response.LoginResponse;
import mac2015.lavit.domain.models.response.RegistrationResponse;
import mac2015.lavit.domain.models.response.Response;
import mac2015.lavit.domain.repository.ListRepository;
import mac2015.lavit.domain.repository.google.GoogleAPIRepository;
import mac2015.lavit.domain.repository.google.GoogleRepository;
import mac2015.lavit.executor.InteractorExecutor;
import mac2015.lavit.executor.MainThreadExecutor;

/**
 * Created by noxqs on 23.09.15..
 */
public class LoginGoogleInteractorImpl extends AbstractInteractor implements LoginGoogleInteractor, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener{

    private static final String TAG = "GOOGLE_INTERACTOR";
    private GoogleApiManager googleApiManager;
    private GoogleRepository googleRepository;
    private ListRepository listRepository;
    private LoginModel loginModel;
    private RegistrationModel registrationModel;
    private Activity activity;
    private Callback callback;
    private String endpoint;


    public LoginGoogleInteractorImpl(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, ListRepository listRepository, String endpoint) {
        super(interactorExecutor, mainThreadExecutor);
        this.listRepository = listRepository;
        this.endpoint = endpoint;
    }

    @Override
    public void onConnected(Bundle bundle) {
        getInteractorExecutor().execute(this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        if(googleApiManager.canResolve()){
            if(connectionResult.hasResolution()){
                googleApiManager.resolveFail(activity, connectionResult);
            }

        }
    }

    @Override
    public void run() {
        try{
            final User googleUser = googleRepository.getUser();
            googleUser.setProfilePicture("http://www.vecernji.hr/media/slika/89/442815.jpg");
            registrationModel = fillRegistrationModel(googleUser);
            final Response<RegistrationResponse> registrationResponse = this.listRepository.registerGoogle(registrationModel, googleUser.getSocialProfile());
            final Response<LoginResponse> loginResponse = this.listRepository.loginGoogle(googleUser.getSocialProfile().getToken(),
                    googleUser.getSocialProfile().getId(),
                    googleUser.getSocialProfile().getTokenExpiration(),
                    String.valueOf(SocialProfile.Type.GOOGLE));

        }
        catch(Exception e){
            Log.e(TAG, "Google login error", e);

        }


    }


    private RegistrationModel fillRegistrationModel(final User googleUser) {
        registrationModel.setEmail(googleUser.getEmail());
        registrationModel.setPassword(googleUser.getPassword());
        registrationModel.setFirstName(googleUser.getFirstName());
        registrationModel.setLastName(googleUser.getLastName());
        return registrationModel;
    }

    @Override
    public void login(Callback callback, Activity activity, GoogleApiManager googleApiManager) {
        this.callback = callback;
        this.activity = activity;
        this.googleApiManager = googleApiManager;
        this.googleApiManager.init(this, this);
        this.googleRepository = new GoogleAPIRepository(this.googleApiManager.getGoogleApiClient(), endpoint);
        if(!this.googleApiManager.isConnected()){
            this.googleApiManager.connect();
        }
        else{
            getInteractorExecutor().execute(this);
        }
    }
}
