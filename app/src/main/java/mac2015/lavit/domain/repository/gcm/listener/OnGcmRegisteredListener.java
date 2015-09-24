package mac2015.lavit.domain.repository.gcm.listener;

/**
 * Created by dmacan on 24.9.2015..
 */
public interface OnGcmRegisteredListener {

    public void onGcmRegistered(boolean success, String registrationId);

}
