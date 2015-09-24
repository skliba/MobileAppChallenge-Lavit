package mac2015.lavit.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import butterknife.InjectView;
import mac2015.lavit.R;

/**
 * Created by dmacan on 23.9.2015..
 */
public class FeedbackRatingFragment extends FeedbackFragment<Double> {

    private static final String TAG = "DAM_FRAG_FBRATING";
    @InjectView(R.id.imgBackHeart)
    ImageView imgBackHeart;
    @InjectView(R.id.imgFrontHeart)
    ImageView imgFrontHeart;
    @InjectView(R.id.seekBar)
    SeekBar seekBar;
    @InjectView(R.id.txtFeedbackRatingValue)
    TextView txtFeedbackRatingValue;

    RelativeLayout.LayoutParams params;

    private int borderWidth;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feedback_rating, null, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        seekBar.setOnSeekBarChangeListener(onSeekBarChanged);
        imgBackHeart.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // Ensure you call it only once :
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                    imgBackHeart.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    imgBackHeart.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                borderWidth = imgBackHeart.getWidth();
                seekBar.setMax(100);
                seekBar.setProgress(50);
            }
        });
    }

    SeekBar.OnSeekBarChangeListener onSeekBarChanged = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            borderWidth = imgBackHeart.getWidth();
            double progressDouble = progress + 7;
            double bwDouble = borderWidth;
            double calculated = (progressDouble / 100) * (bwDouble);
            int cast = (int) calculated;
            Log.i(TAG, "Progress: " + progress);
            Log.i(TAG, "BW: " + borderWidth);
            Log.i(TAG, "Calculated: " + calculated);
            Log.i(TAG, "Cast: " + cast);
            updateWidth(cast);
            updateTransparency(progress);
            imgFrontHeart.setLayoutParams(params);
            txtFeedbackRatingValue.setText(progress + "%");
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            YoYo.with(Techniques.Tada).duration(800).playOn(imgFrontHeart);
        }
    };

    private void updateTransparency(int value) {
        // Ensure you call it only once :
        if (value < 10) {
            value = 10;
        }
        float percent = ((float) value) / 100;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            imgFrontHeart.setImageAlpha((int) (255 * percent));
        } else {
            imgFrontHeart.setAlpha(percent);
        }
    }

    private void updateWidth(int dp) {
        params = new RelativeLayout.LayoutParams(dp, dp);
        params.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
    }

    @Override
    public Double getFeedback() {
        return (double) seekBar.getProgress();
    }
}
