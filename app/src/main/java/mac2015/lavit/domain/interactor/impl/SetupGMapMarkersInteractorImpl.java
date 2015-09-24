package mac2015.lavit.domain.interactor.impl;

import android.content.Context;
import android.graphics.Bitmap;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import mac2015.lavit.R;
import mac2015.lavit.domain.interactor.AbstractInteractor;
import mac2015.lavit.domain.interactor.SetupGMapMarkersInteractor;
import mac2015.lavit.domain.util.BitmapUtil;
import mac2015.lavit.executor.InteractorExecutor;
import mac2015.lavit.executor.MainThreadExecutor;
import mac2015.lavit.ui.custom.map.PinViewModel;

/**
 * Created by dmacan on 24.9.2015..
 */
public class SetupGMapMarkersInteractorImpl extends AbstractInteractor implements SetupGMapMarkersInteractor {

    private static final String TAG = "DAM_INTER_GMAPMARKER";
    private static Bitmap back;
    private static int size = -1;
    private Callback callback;
    private List<PinViewModel> pins;
    private Context context;

    public SetupGMapMarkersInteractorImpl(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, Context context) {
        super(interactorExecutor, mainThreadExecutor);
        this.context = context;
    }

    @Override
    public void start(List<PinViewModel> pins, Callback callback) {
        this.pins = pins;
        this.callback = callback;
        getInteractorExecutor().execute(this);
    }

    @Override
    public void run() {
        for (PinViewModel model : pins) {
            try {
                if (back == null)
                    back = Picasso.with(context).load(R.drawable.ic_pin_bg).noFade().get();
                if (size < 0)
                    size = (int) (back.getWidth() * 0.85);
                Bitmap front = Picasso.with(context).load(model.getAvatar()).noFade().resize(size, size).get();
                front = BitmapUtil.circularize(front);
                Bitmap bitmap = BitmapUtil.mergeToPin(back, front);
                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
                notifySuccess(bitmapDescriptor, model);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void notifySuccess(final BitmapDescriptor bitmapDescriptor, final PinViewModel pinViewModel) {
        getMainThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(bitmapDescriptor, pinViewModel);
            }
        });
    }

}
