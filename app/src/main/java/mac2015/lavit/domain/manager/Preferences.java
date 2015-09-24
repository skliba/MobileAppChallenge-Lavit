package mac2015.lavit.domain.manager;

import android.content.Context;
import android.content.SharedPreferences;

import mac2015.lavit.domain.models.User;
import mac2015.lavit.domain.util.Serializator;

/**
 * Created by dmacan on 24.9.2015..
 */
public class Preferences {

    private static final String KEY_USER = "mac2015.lavit.user";
    private static final String KEY_TOKEN = "mac2015.lavit.token";

    private Context context;

    public Preferences(Context context) {
        this.context = context;
    }

    public static SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences("mac2015.lavit.preferences.DEFAULT", Context.MODE_PRIVATE);
    }

    public void storeUser(User user) {
        editor().putString(KEY_USER, Serializator.serialize(user)).commit();
        storeToken(user.getToken());
    }

    public void storeToken(String token) {
        editor().putString(KEY_TOKEN, token).commit();
    }

    public User getUser() {
        return Serializator.deserialize(preferences().getString(KEY_USER, null), User.class);
    }

    public String getToken() {
        return preferences().getString(KEY_TOKEN, null);
    }

    public boolean isUserStored() {
        return getUser() != null && getToken() != null;
    }

    public void clear() {
        editor().clear().commit();
    }

    public SharedPreferences preferences() {
        return getPreferences(context);
    }

    public SharedPreferences.Editor editor() {
        return preferences().edit();
    }

}
