package mac2015.lavit.domain.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by noxqs on 23.09.15..
 */
public class RegistrationModel implements Serializable{

    @SerializedName("name")
    private String firstName;

    @SerializedName("surname")
    private String lastName;

    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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
}
