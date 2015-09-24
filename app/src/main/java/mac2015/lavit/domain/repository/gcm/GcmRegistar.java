package mac2015.lavit.domain.repository.gcm;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

/**
 * The base class responsible for handling registration with the GCM service.
 */
public abstract class GcmRegistar {
    public static final String PREF_REGISTRATION_ID = "GCM_REGISTRATION_ID"; //$NON-NLS-1$

    private final Context context;
    private final SharedPreferences preferences;

    /**
     * @param context application's context.
     */
    public GcmRegistar(final Context context) {
        this.context = context;
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Returns the registration ID previously returned by the GCM service.
     *
     * @return the registration ID previously returned by the GCM service.
     */
    public final String getRegistrationId() {
        return this.preferences.getString(GcmRegistar.PREF_REGISTRATION_ID, null);
    }

    /**
     * Registers the device with the GCM service.
     *
     * @param senderId the sender ID of the GCM service.
     */
    public void registerInBackground(final String senderId) {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(final Message message) {
                if (message.what == 0) {
                    GcmRegistar.this.onRegister((String) message.obj);
                } else {
                    GcmRegistar.this.onError((Exception) message.obj);
                }
            }
        };

        new AsyncTask<String, Void, Object>() {
            @SuppressWarnings("synthetic-access")
            @Override
            protected Object doInBackground(final String... params) {
                try {
                    final String registrationId = GoogleCloudMessaging.getInstance(GcmRegistar.this.context).register(params[0]);

                    if (!TextUtils.isEmpty(registrationId)) {
                        GcmRegistar.this.preferences.edit().putString(GcmRegistar.PREF_REGISTRATION_ID, registrationId).commit();
                    }

                    return registrationId;
                } catch (final IOException e) {
                    return e;
                }
            }

            @Override
            protected void onPostExecute(final Object result) {
                if (result instanceof String) {
                    handler.sendMessage(handler.obtainMessage(0, result));
                } else {
                    handler.sendMessage(handler.obtainMessage(1, result));
                }
            }
        }.execute(senderId);
    }

    /**
     * Called after a device has been registered.
     *
     * @param registrationId the registration ID returned by the GCM service.
     */
    protected abstract void onRegister(String registrationId);

    /**
     * Called on registration error.
     *
     * @param exception the exception thrown during registration.
     */
    protected abstract void onError(Exception exception);
}
