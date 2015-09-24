package mac2015.lavit.ui.custom.map;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

import java.util.List;

/**
 * Created by dmacan on 24.9.2015..
 */
public interface PinGMapAdapter {

    void toCurrentLocation();

    void setCurrentLocationEnabled(boolean enabled);

    void addPins(PinViewModel... pinViewModel);

    void addPins(List<PinViewModel> pinViewModelList);

    void removePins(PinViewModel... pinViewModels);

    void removePins(int... positions);

    void removeAllPins();

    boolean isAnimationEnabled();

    void setAnimationEnabled(boolean enabled);

    GoogleMap getGoogleMap();

    void setGoogleMap(GoogleMap googleMap);

    PinViewModel getPin(int index);

    List<PinViewModel> getPins();

    List<PinViewModel> getPins(int... indexes);

    void setOnPinMarkerSelectedListener(OnPinMarkerSelectedListener listener);

    void setOnPinInfoSelectedListener(OnPinInfoSelectedListener listener);

    interface OnPinMarkerSelectedListener {
        void onPinMarkerSelected(int index, PinViewModel pin, Marker marker);
    }

    interface OnPinInfoSelectedListener {
        void onPinInfoSelected(int index, PinViewModel pin, Marker marker);
    }

}
