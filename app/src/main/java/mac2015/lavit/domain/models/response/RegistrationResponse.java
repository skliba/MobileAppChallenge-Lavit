package mac2015.lavit.domain.models.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by noxqs on 23.09.15..
 */
public class RegistrationResponse implements Serializable{

    @SerializedName("username")
    private String username;

    @SerializedName("email")
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
