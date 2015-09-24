package mac2015.lavit.ui.fragment;

import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.InjectView;
import mac2015.lavit.R;
import mac2015.lavit.ui.custom.map.PinGMapAdapter;

/**
 * Created by dmacan on 23.9.2015..
 */
public class FeedbackGeolocationFragment extends FeedbackFragment<Location> {

    @InjectView(R.id.layoutLocation)
    LinearLayout layoutLocation;
    @InjectView(R.id.mapView)
    MapView mapView;
    @InjectView(R.id.txtLat)
    TextView txtLat;
    @InjectView(R.id.txtLng)
    TextView txtLng;
    @InjectView(R.id.txtLocation)
    TextView txtLocation;

    @Inject
    PinGMapAdapter adapter;
    GoogleMap googleMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feedback_geolocation, null, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView.onCreate(savedInstanceState);
        setupMap();
        if (getOnFragmentReadyListener() != null) {
            setActive(true);
            getOnFragmentReadyListener().onFragmentReady(this);
        }
    }

    private void setupMap() {
        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(final GoogleMap googleMap) {
                FeedbackGeolocationFragment.this.googleMap = googleMap;
                adapter.setGoogleMap(googleMap);
                adapter.setCurrentLocationEnabled(true);
                adapter.setAnimationEnabled(true);
                googleMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                    @Override
                    public void onMyLocationChange(Location location) {
                        setFeedback(location);
                        zoomIn(googleMap, location);
                        showInfo();
                        googleMap.setOnMyLocationChangeListener(null);
                    }
                });
            }
        });
    }

    private void showInfo() {
        YoYo.with(Techniques.BounceInUp).playOn(layoutLocation);
        layoutLocation.setVisibility(View.VISIBLE);
        txtLat.setText("Latitude: " + getFeedback().getLatitude());
        txtLng.setText("Longitude: " + getFeedback().getLongitude());
        try {
            txtLocation.setText(getName(getFeedback()));
        } catch (Exception e) {
            txtLocation.setVisibility(View.GONE);
        }
    }

    private String getName(Location location) throws IOException {
        Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        Address obj = addresses.get(0);
        return obj.getLocality();
    }

    private void zoomIn(GoogleMap googleMap, Location location) {
        CameraUpdate center = CameraUpdateFactory.newLatLng(new LatLng(location.getLatitude(), location.getLongitude()));
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(11);
        googleMap.moveCamera(center);
        googleMap.animateCamera(zoom);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

}
