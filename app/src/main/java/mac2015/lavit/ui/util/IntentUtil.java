package mac2015.lavit.ui.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import mac2015.lavit.domain.models.User;
import mac2015.lavit.domain.util.Serializator;
import mac2015.lavit.ui.activity.MainActivity;

/**
 * Created by dmacan on 23.9.2015..
 */
public class IntentUtil {

    private static final String KEY_USER = "mac2015.lavit.user";

    public static Intent startMainActivity(Context context, User user) {
        String userJSON = Serializator.serialize(user);
        Intent mainActivityIntent = new Intent(context, MainActivity.class);
        mainActivityIntent.putExtra(KEY_USER, userJSON);
        return mainActivityIntent;
    }

    public static User fetchUser(Activity activity) {
        String userJSON = activity.getIntent().getStringExtra(KEY_USER);
        return Serializator.deserialize(userJSON, User.class);
    }
}
