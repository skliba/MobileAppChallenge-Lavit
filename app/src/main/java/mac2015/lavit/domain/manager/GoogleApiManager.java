package mac2015.lavit.domain.manager;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.plus.Plus;

/**
 * Created by noxqs on 23.09.15..
 */
public class GoogleApiManager {

    private static final int RC_SIGN_IN = 0;

    private GoogleApiClient googleApiClient;
    private Context context;
    private boolean isResolving = false;
    private boolean shouldResolve = false;
    private boolean initialized;

    public GoogleApiManager(Context context){
        this.context = context;
    }

    public void init(GoogleApiClient.ConnectionCallbacks callbacks, GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener){
        if(!initialized){
            googleApiClient = new GoogleApiClient.Builder(context)
                    .addConnectionCallbacks(callbacks)
                    .addOnConnectionFailedListener(onConnectionFailedListener)
                    .addApi(Plus.API)
                    .addScope(new Scope(Scopes.PROFILE))
                    .build();
        }

        initialized = true;

    }

    public boolean connect(){
        shouldResolve = true;
        if(!googleApiClient.isConnected()){
            googleApiClient.connect();
            return true;
        }
        return false;
    }

    public boolean isConnected(){
        return googleApiClient.isConnected();
    }

    public boolean resolveFail(Activity activity, ConnectionResult connectionResult){
        try{
            connectionResult.startResolutionForResult(activity, RC_SIGN_IN);
            isResolving = true;
        }
        catch(IntentSender.SendIntentException e){
            Log.e("GOOGLE ERROR", "ConnectionResult.", e);
            isResolving = false;
            googleApiClient.connect();
        }
        return isResolving;
    }

    public void onResult(Activity activity, int requestCode, int resultCode, Intent data){
        Log.d("GOOGLE ERROR", "onResult: " + requestCode + ":" + resultCode + ":" + data);
        if(requestCode == RC_SIGN_IN){
            if(resultCode != Activity.RESULT_OK){
                shouldResolve = false;
            }
            isResolving = false;
            googleApiClient.connect();
        }
    }

    public void connected(){
        shouldResolve = false;
    }

    public void disconnected(){
        shouldResolve = false;
    }

    public boolean canResolve(){
        return shouldResolve && !isResolving;
    }

    public void signOut(){
        if(googleApiClient.isConnected()){
            Plus.AccountApi.clearDefaultAccount(googleApiClient);
            googleApiClient.disconnect();
        }
    }

    public GoogleApiClient getGoogleApiClient() {
        return googleApiClient;
    }
}
