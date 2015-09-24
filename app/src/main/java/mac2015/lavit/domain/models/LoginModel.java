package mac2015.lavit.domain.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by noxqs on 23.09.15..
 */
public class LoginModel implements Serializable {

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    @SerializedName("registrationId")
    private String regId;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegId() {
        return regId;
    }

    public void setRegId(String regId) {
        this.regId = regId;
    }
}
