package mac2015.lavit.ui.custom.map;

import android.content.Context;
import android.location.Location;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import mac2015.lavit.domain.interactor.SetupGMapMarkersInteractor;
import mac2015.lavit.domain.interactor.impl.SetupGMapMarkersInteractorImpl;
import mac2015.lavit.executor.InteractorExecutor;
import mac2015.lavit.executor.MainThreadExecutor;
import mac2015.lavit.executor.MainThreadExecutorImpl;
import mac2015.lavit.executor.ThreadExecutor;


/**
 * Created by dmacan on 24.9.2015..
 */
public class PinGMapAdapterImpl implements PinGMapAdapter, GoogleMap.OnMarkerClickListener, GoogleMap.OnInfoWindowClickListener {

    private static final String TAG = "DAM_ADAPTER_GMAP";
    private HashMap<PinViewModel, Marker> pins;
    private GoogleMap googleMap;
    private boolean isAnimationEnabled;
    private InteractorExecutor interactorExecutor;
    private MainThreadExecutor mainThreadExecutor;
    private Context context;
    private MarkerAnimator markerAnimator;
    private OnPinMarkerSelectedListener onPinMarkerSelectedListener;
    private OnPinInfoSelectedListener onPinInfoSelectedListener;

    public PinGMapAdapterImpl(Context context) {
        this(context, new ThreadExecutor(), new MainThreadExecutorImpl());
    }

    public PinGMapAdapterImpl(Context context, InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor) {
        this.context = context;
        this.interactorExecutor = interactorExecutor;
        this.mainThreadExecutor = mainThreadExecutor;
        this.pins = new LinkedHashMap<>();
        this.markerAnimator = new MarkerAnimator();
    }

    private static PinViewModel getPin(HashMap<PinViewModel, Marker> map, Marker value) {
        for (Map.Entry<PinViewModel, Marker> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public void toCurrentLocation() {
        Location location = googleMap.getMyLocation();
        googleMap.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude())));
    }

    @Override
    public void setCurrentLocationEnabled(boolean enabled) {
        googleMap.setMyLocationEnabled(enabled);
    }

    @Override
    public void addPins(PinViewModel... pinViewModels) {
        if (pinViewModels.length > 0) {
            List<PinViewModel> pinViewModelList = new ArrayList<>(Arrays.asList(pinViewModels));
            addPins(pinViewModelList);
        }
    }

    @Override
    public void addPins(List<PinViewModel> pinViewModelList) {
        SetupGMapMarkersInteractor interacor = new SetupGMapMarkersInteractorImpl(interactorExecutor, mainThreadExecutor, context);
        interacor.start(pinViewModelList, new SetupGMapMarkersInteractor.Callback() {
            @Override
            public void onSuccess(BitmapDescriptor bitmapDescriptor, PinViewModel pin) {
                MarkerOptions options = toMarkerOption(pin);
                options.icon(bitmapDescriptor);
                Marker marker = googleMap.addMarker(options);
                if (isAnimationEnabled) {
                    markerAnimator.animate(options.getPosition(), marker, 300);
                }
                pins.put(pin, marker);
            }
        });
    }

    @Override
    public void removePins(PinViewModel... pinViewModels) {
        for (PinViewModel pinViewModel : pinViewModels) {
            pins.remove(pinViewModel).remove();
        }
    }

    @Override
    public void removePins(int... positions) {
        ArrayList<PinViewModel> keys = new ArrayList<>(pins.keySet());
        for (int position : positions) {
            PinViewModel toRemove = keys.get(position);
            pins.remove(toRemove).remove();
        }
    }

    @Override
    public void removeAllPins() {
        pins.clear();
        if (googleMap != null)
            googleMap.clear();
    }

    @Override
    public boolean isAnimationEnabled() {
        return isAnimationEnabled;
    }

    @Override
    public void setAnimationEnabled(boolean enabled) {
        this.isAnimationEnabled = enabled;
    }

    @Override
    public GoogleMap getGoogleMap() {
        return googleMap;
    }

    @Override
    public void setGoogleMap(GoogleMap googleMap) {
        this.googleMap = googleMap;
        this.markerAnimator.setGoogleMap(googleMap);
    }

    @Override
    public PinViewModel getPin(int index) {
        ArrayList<PinViewModel> models = new ArrayList<>(pins.keySet());
        return models.get(index);
    }

    @Override
    public List<PinViewModel> getPins() {
        return new ArrayList<>(pins.keySet());
    }

    @Override
    public List<PinViewModel> getPins(int... indexes) {
        List<PinViewModel> wantedPins = new ArrayList<>();
        List<PinViewModel> pinViewModels = new ArrayList<>(pins.keySet());
        for (int i : indexes) {
            wantedPins.add(pinViewModels.get(i));
        }
        return wantedPins;
    }

    @Override
    public void setOnPinMarkerSelectedListener(OnPinMarkerSelectedListener listener) {
        this.onPinMarkerSelectedListener = listener;
        this.googleMap.setOnMarkerClickListener(this);
    }

    @Override
    public void setOnPinInfoSelectedListener(OnPinInfoSelectedListener listener) {
        this.onPinInfoSelectedListener = listener;
        this.googleMap.setOnInfoWindowClickListener(this);
    }

    private MarkerOptions toMarkerOption(PinViewModel pinViewModel) {
        final MarkerOptions options = new MarkerOptions();
        options.title(pinViewModel.getName());
        options.position(new LatLng(pinViewModel.getLatitude(), pinViewModel.getLongitude()));
        return options;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (onPinMarkerSelectedListener != null) {
            PinViewModel viewModel = getPin(pins, marker);
            List<PinViewModel> models = new ArrayList<>(pins.keySet());
            onPinMarkerSelectedListener.onPinMarkerSelected(models.indexOf(viewModel), viewModel, marker);
            return true;
        }
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        if (onPinInfoSelectedListener != null) {
            PinViewModel viewModel = getPin(pins, marker);
            List<PinViewModel> models = new ArrayList<>(pins.keySet());
            onPinInfoSelectedListener.onPinInfoSelected(models.indexOf(viewModel), viewModel, marker);
        }
    }
}
