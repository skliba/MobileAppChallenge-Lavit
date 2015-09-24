package mac2015.lavit.domain.repository.gcm.simple;

import android.content.Context;
import android.util.Log;

import mac2015.lavit.domain.manager.Preferences;
import mac2015.lavit.domain.repository.gcm.GcmRegistar;
import mac2015.lavit.domain.repository.gcm.listener.OnErrorListener;
import mac2015.lavit.domain.repository.gcm.listener.OnGcmRegisteredListener;

/**
 * Created by dmacan on 24.9.2015..
 */
public class SimpleGcmRegistar extends GcmRegistar {
    private Context context;
    private OnGcmRegisteredListener onGcmRegisteredListener;
    private OnErrorListener onErrorListener;

    /**
     * Creates a new instance of {@link com.look4me.mediator.gcm.GcmRegistrar}.
     *
     * @param context application's context.
     */
    public SimpleGcmRegistar(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onRegister(String registrationId) {
        new Preferences(context).saveRegistrationId(registrationId);
        if (this.onGcmRegisteredListener != null)
            this.onGcmRegisteredListener.onGcmRegistered(true, registrationId);
    }

    @Override
    protected void onError(Exception exception) {
        Log.e("SimpleGCM", "Exception: " + exception.getMessage());
        if (this.onErrorListener != null)
            this.onErrorListener.onError(exception.getMessage());
    }

    public void setOnGcmRegisteredListener(OnGcmRegisteredListener onGcmRegisteredListener) {
        this.onGcmRegisteredListener = onGcmRegisteredListener;
    }

    public void setOnErrorListener(OnErrorListener onErrorListener) {
        this.onErrorListener = onErrorListener;
    }
}