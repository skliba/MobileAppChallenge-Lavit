package mac2015.lavit.domain.repository.google;

import android.util.Log;

import com.google.android.gms.auth.GoogleAuthException;
import com.google.android.gms.auth.GoogleAuthUtil;
import com.google.android.gms.auth.UserRecoverableAuthException;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.Person;

import java.io.IOException;

import mac2015.lavit.domain.models.SocialProfile;
import mac2015.lavit.domain.models.User;
import mac2015.lavit.domain.models.response.GoogleTokenResponse;
import retrofit.RestAdapter;

/**
 * Created by noxqs on 23.09.15..
 */
public class GoogleAPIRepository implements GoogleRepository {

    private static final String TAG = "DAM_REPO_GOOGLE";
    private GoogleApiClient googleApiClient;
    private String endpoint;
    private GoogleAPIService googleAPIService;

    public GoogleAPIRepository(GoogleApiClient googleApiClient, String endpoint) {
        this.googleApiClient = googleApiClient;
        this.endpoint = endpoint;
        this.init();
    }

    private void init() {
        RestAdapter adapter = new RestAdapter.Builder()
                .setEndpoint(endpoint)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setLog(new RestAdapter.Log() {
                    @Override
                    public void log(String message) {
                        Log.d("LAVIT_API_GOOGLE", message);
                    }
                })
                .build();

        googleAPIService = adapter.create(GoogleAPIService.class);
    }


    @Override
    public User getUser() throws GoogleAuthException, UserRecoverableAuthException {
        try {
            Person person = getPerson();
            String email = getEmail();
            String token = getTokenV2(email);
            User user = new User();
            user.setEmail(email);
            user.setSocialProfile(person.getId(), token, SocialProfile.Type.GOOGLE);
            user.setFirstName(person.getName().getGivenName());
            user.setLastName(person.getName().getFamilyName());
            String url = person.getImage().getUrl();
            url = url.substring(0, url.lastIndexOf("?"));
            url += "?sz=300";
            user.setProfilePicture(url);
            return user;
        } catch (UserRecoverableAuthException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            //throw new GetUserException(C.Code.Error.GET_USER);
        }
        return null;
    }

    private Person getPerson() {
        return Plus.PeopleApi.getCurrentPerson(googleApiClient);
    }

    private String getEmail() {
        return Plus.AccountApi.getAccountName(googleApiClient);
    }

    private String getToken(String email) throws GoogleAuthException, IOException {
        return GoogleAuthUtil.getToken(googleApiClient.getContext(), email, "oauth2:" + Scopes.PLUS_LOGIN + " https://www.googleapis.com/auth/plus.profile.emails.read");
    }

    private String getTokenV2(String email) throws IOException, GoogleAuthException {
        // get Access Token with Scopes.PLUS_PROFILE
        String sAccessToken;
        return GoogleAuthUtil.getToken(
                googleApiClient.getContext(),
                email + "",
                "oauth2:"
                        + Scopes.PROFILE + " "
                        + "https://www.googleapis.com/auth/plus.login" + " "
                        + "https://www.googleapis.com/auth/plus.profile.emails.read");
    }

    private GoogleTokenResponse getTokenInformation(String token) {
        return googleAPIService.getTokenInformation(token);
    }

    private String getTokenExpiration(String token) {
        return String.valueOf(googleAPIService.getTokenInformation(token).getExpiresIn());
    }
}
