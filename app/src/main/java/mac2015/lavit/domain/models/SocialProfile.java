package mac2015.lavit.domain.models;

/**
 * Created by noxqs on 23.09.15..
 */
public class SocialProfile {

    private String token;
    private String id;
    private String tokenExpiration;
    private int type;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getTokenExpiration() {
        return tokenExpiration;
    }

    public void setTokenExpiration(String tokenExpiration) {
        this.tokenExpiration = tokenExpiration;
    }

    public static class Type {
        public static final int LAVIT = 0;
        public static final int GOOGLE = 1;

    }
}
