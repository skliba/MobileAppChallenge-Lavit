package mac2015.lavit.ui.custom.behavior;

import android.support.design.widget.AppBarLayout;


/**
 * Created by dmacan on 17.9.2015..
 */
public class AppbarOffsetHandler implements AppBarLayout.OnOffsetChangedListener {

    private static final String TAG = "DAM_BEHAVIOR_OFFSET";
    private int treshold = -1;
    private float tresholdPercentage = 0.5f;
    private boolean tresholdOutside;
    private OnTresholdListener onTresholdListener;

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int offset) {
        if (onTresholdListener != null) {
            if (treshold < 0) {
                treshold = (int) (appBarLayout.getHeight() * tresholdPercentage);
            }
            if (offset < 0) {
                offset *= -1;
            }
            if (!tresholdOutside && (offset > treshold)) {
                tresholdOutside = true;
                onTresholdListener.onTreshold(false);
            } else if (tresholdOutside && (offset <= treshold)) {
                tresholdOutside = false;
                onTresholdListener.onTreshold(true);
            }
        }
    }

    public void setTresholdPercentage(int tresholdPercentage) {
        this.tresholdPercentage = tresholdPercentage / 100;
    }

    public void setOnTresholdListener(OnTresholdListener onTresholdListener) {
        this.onTresholdListener = onTresholdListener;
    }

    public interface OnTresholdListener {
        void onTreshold(boolean within);
    }
}
