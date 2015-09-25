package mac2015.lavit.domain.repository.gcm.simple;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import mac2015.lavit.R;
import mac2015.lavit.domain.repository.gcm.GcmIntentService;
import mac2015.lavit.domain.repository.gcm.GcmObserver;
import mac2015.lavit.domain.repository.gcm.listener.OnGcmMessageReceivedListener;
import mac2015.lavit.ui.activity.ProjectInfoActivity;

/**
 * Created by dmacan on 24.9.2015..
 */
public class SimpleGcmIntentService extends GcmIntentService {

    private static final String TAG = "DAM_SERV_GCM";

    public SimpleGcmIntentService() {
        this("MOJGCMINTENTSERVICE_BRE");
        Log.i(TAG, "GCMI start");
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
        Log.i(TAG, "Message received");
        OnGcmMessageReceivedListener observer = GcmObserver.getInstance().getObserver();
        if (observer != null)
            observer.onGcmMessageReceived(true, intent);
        notification(intent);
    }

    private void notification(Intent data) {
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.logo_heart) // notification icon
                .setContentTitle("Lavit update") // title for notification
                .setContentText(data.getStringExtra("message")) // message for notification
                .setAutoCancel(true); // clear notification after click
        Intent intent = new Intent(getBaseContext(), ProjectInfoActivity.class);
        intent.putExtra("mac2015.lavit.project", data.getStringExtra("project"));
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        mBuilder.setContentIntent(pi);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(0, mBuilder.build());
    }

}