package mac2015.lavit.domain.repository.gcm;

/**
 * Created by dmacan on 24.9.2015..
 */

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;

import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * An application-specific {@link android.app.IntentService} responsible for handling communication from GCM service.
 * <p>The abstract methods in this class are called from its worker thread, and hence should run in a
 * limited amount of time. If they execute long operations, they should spawn new threads, otherwise
 * the worker thread will be blocked.</p>
 * <p>Subclasses must provide a public no-arg constructor.</p>
 */
public abstract class GcmIntentService extends IntentService {
    /**
     * @param name Used to name the worker thread, important only for debugging.
     */
    protected GcmIntentService(final String name) {
        super(name);
    }

    @Override
    protected final void onHandleIntent(final Intent intent) {
        final Bundle bundle = intent.getExtras();

        if (!bundle.isEmpty()) {
            final String messageType = GoogleCloudMessaging.getInstance(this).getMessageType(intent);

            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                this.onSendError();
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                this.onMessageDeleted(Integer.parseInt(intent.getStringExtra("total_deleted"))); //$NON-NLS-1$
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
                this.onMessageReceived(intent);
            }
        }
        WakefulBroadcastReceiver.completeWakefulIntent(intent);
    }

    /**
     * Called when there was a error returned by the GCM service.
     */
    protected abstract void onSendError();

    /**
     * Called when the GCM server tells pending messages have been deleted because the device was idle.
     *
     * @param total the number of deleted messages.
     */
    protected abstract void onMessageDeleted(int total);

    /**
     * Called when a cloud message has been received.
     *
     * @param intent intent containing the message payload as extras.
     */
    protected abstract void onMessageReceived(Intent intent);
}
