package mac2015.lavit.domain.interactor;

import com.google.android.gms.maps.model.BitmapDescriptor;

import java.util.List;

import mac2015.lavit.ui.custom.map.PinViewModel;

/**
 * Created by dmacan on 24.9.2015..
 */
public interface SetupGMapMarkersInteractor {

    void start(List<PinViewModel> pins, Callback callback);

    interface Callback {
        void onSuccess(BitmapDescriptor bitmapDescriptor, PinViewModel pin);
    }
}
