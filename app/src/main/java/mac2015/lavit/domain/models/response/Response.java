package mac2015.lavit.domain.models.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by noxqs on 23.09.15..
 */
public class Response<T> implements Serializable{

    @SerializedName("status")
    private int status;

    @SerializedName("message")
    private T message;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getMessage() {
        return message;
    }

    public void setMessage(T message) {
        this.message = message;
    }
}
