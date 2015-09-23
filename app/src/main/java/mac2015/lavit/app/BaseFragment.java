package mac2015.lavit.app;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by dmacan on 22.9.2015..
 */
public abstract class BaseFragment extends Fragment {

    private OnFragmentReadyListener onFragmentReadyListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectViews();
        if (onFragmentReadyListener != null) {
            onFragmentReadyListener.onFragmentReady(this);
        }
    }

    private void injectViews() {
        ButterKnife.inject(this, getView());
    }

    private void injectDependencies() {
        MyApp app = MyApp.get(getActivity());
        app.inject(this);
    }

    protected void toggleEnabled(boolean enabled, View... views) {
        for (View v : views) {
            v.setEnabled(enabled);
        }
    }

    public interface OnFragmentReadyListener {
        void onFragmentReady(Fragment fragment);
    }

    public OnFragmentReadyListener getOnFragmentReadyListener() {
        return onFragmentReadyListener;
    }

    public void setOnFragmentReadyListener(OnFragmentReadyListener onFragmentReadyListener) {
        this.onFragmentReadyListener = onFragmentReadyListener;
    }
}
