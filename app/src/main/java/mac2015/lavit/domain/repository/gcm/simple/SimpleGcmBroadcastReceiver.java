package mac2015.lavit.domain.repository.gcm.simple;

import android.content.Intent;

import mac2015.lavit.domain.repository.gcm.GcmBroadcastReceiver;

/**
 * Created by dmacan on 24.9.2015..
 */
public class SimpleGcmBroadcastReceiver extends GcmBroadcastReceiver {
    @Override
    protected String getGcmIntentServiceClassName(Intent intent) {
        return SimpleGcmIntentService.class.getName();
    }
}