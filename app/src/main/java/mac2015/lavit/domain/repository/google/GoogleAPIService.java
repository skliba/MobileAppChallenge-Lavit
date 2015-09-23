package mac2015.lavit.domain.repository.google;

import mac2015.lavit.domain.models.response.GoogleTokenResponse;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by noxqs on 23.09.15..
 */
public interface GoogleAPIService {

    @GET("/oauth2/v1/tokeninfo")
    GoogleTokenResponse getTokenInformation(@Query("access_token") String token);
}
