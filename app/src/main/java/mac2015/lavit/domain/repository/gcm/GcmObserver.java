package mac2015.lavit.domain.repository.gcm;

import mac2015.lavit.domain.repository.gcm.listener.OnGcmMessageReceivedListener;

/**
 * Created by dmacan on 24.9.2015..
 */
public class GcmObserver {

    private OnGcmMessageReceivedListener onGcmMessageReceivedListener;
    private static GcmObserver instance;

    private GcmObserver() {

    }

    public static GcmObserver getInstance() {
        if (instance == null)
            instance = new GcmObserver();
        return instance;
    }

    public void register(OnGcmMessageReceivedListener onGcmMessageReceivedListener) {
        this.onGcmMessageReceivedListener = onGcmMessageReceivedListener;
    }

    public OnGcmMessageReceivedListener getObserver() {
        return this.onGcmMessageReceivedListener;
    }

}