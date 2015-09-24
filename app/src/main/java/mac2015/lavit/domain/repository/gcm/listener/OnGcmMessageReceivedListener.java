package mac2015.lavit.domain.repository.gcm.listener;

import android.content.Intent;

/**
 * Created by dmacan on 24.9.2015..
 */
public interface OnGcmMessageReceivedListener {

    public void onGcmMessageReceived(boolean success, Intent gcmMessage);

}
