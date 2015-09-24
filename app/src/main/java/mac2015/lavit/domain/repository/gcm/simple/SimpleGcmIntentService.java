package mac2015.lavit.domain.repository.gcm.simple;

import android.content.Intent;

import mac2015.lavit.domain.repository.gcm.GcmIntentService;
import mac2015.lavit.domain.repository.gcm.GcmObserver;
import mac2015.lavit.domain.repository.gcm.listener.OnGcmMessageReceivedListener;

/**
 * Created by dmacan on 24.9.2015..
 */
public class SimpleGcmIntentService extends GcmIntentService {

    public SimpleGcmIntentService() {
        this("MOJGCMINTENTSERVICE_BRE");
    }

    /**
     * @param name Used to name the worker thread, important only for debugging.
     */
    protected SimpleGcmIntentService(String name) {
        super(name);
    }

    @Override
    protected void onSendError() {
        OnGcmMessageReceivedListener observer = GcmObserver.getInstance().getObserver();
        if (observer != null)
            observer.onGcmMessageReceived(false, null);
    }

    @Override
    protected void onMessageDeleted(int total) {
        OnGcmMessageReceivedListener observer = GcmObserver.getInstance().getObserver();
        if (observer != null)
            observer.onGcmMessageReceived(true, null);
    }

    @Override
    protected void onMessageReceived(Intent intent) {
        OnGcmMessageReceivedListener observer = GcmObserver.getInstance().getObserver();
        if (observer != null)
            observer.onGcmMessageReceived(true, intent);
    }

}