package mac2015.lavit.domain.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by noxqs on 23.09.15..
 */
public class GoogleTokenResponse {

    @SerializedName("issued_to")
    @Expose
    private String issuedTo;
    @Expose
    private String audience;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @Expose
    private String scope;
    @SerializedName("expires_in")
    @Expose
    private long expiresIn;
    @SerializedName("access_type")
    @Expose
    private String accessType;

    public String getIssuedTo() {
        return issuedTo;
    }

    public void setIssuedTo(String issuedTo) {
        this.issuedTo = issuedTo;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getAccessType() {
        return accessType;
    }

    public void setAccessType(String accessType) {
        this.accessType = accessType;
    }
}
