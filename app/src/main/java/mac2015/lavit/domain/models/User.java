package mac2015.lavit.domain.models;

/**
 * Created by noxqs on 23.09.15..
 */
public class User {

    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String token;
    private String profilePicture;
    private SocialProfile socialProfile;

    public SocialProfile getSocialProfile() {
        return socialProfile;
    }

    public void setSocialProfile(SocialProfile socialProfile) {
        this.socialProfile = socialProfile;
    }

    public void setSocialProfile(String id, String token, int type) {
        SocialProfile sp = new SocialProfile();
        sp.setId(id);
        sp.setToken(token);
        sp.setType(type);
        setSocialProfile(sp);
    }


    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

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
