package mac2015.lavit.domain.repository.google;

import com.google.android.gms.auth.GoogleAuthException;

import mac2015.lavit.domain.models.User;

/**
 * Created by noxqs on 23.09.15..
 */
public interface GoogleRepository {

    User getUser() throws GoogleAuthException;
}
