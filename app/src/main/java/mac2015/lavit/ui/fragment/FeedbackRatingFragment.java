package mac2015.lavit.ui.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

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
    @InjectView(R.id.lavitText)
    TextView txtLavit;

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
                Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/LobsterTwo.otf");
                txtLavit.setTypeface(myTypeface);
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
            updateWidth(cast);
            updateTransparency(progress);
            imgFrontHeart.setLayoutParams(params);
            if (progress <= 24) {
                seekBar.setProgress(0);
                txtFeedbackRatingValue.setText("1");
            } else if (progress >= 25 && progress <= 50) {
                seekBar.setProgress(25);
                txtFeedbackRatingValue.setText("2");
            } else if (progress >= 51 && progress <= 70) {
                seekBar.setProgress(51);
                txtFeedbackRatingValue.setText("3");
            } else if (progress >= 71 && progress <= 90) {
                seekBar.setProgress(75);
                txtFeedbackRatingValue.setText("4");
            } else if (progress >= 91) {
                seekBar.setProgress(100);
                txtFeedbackRatingValue.setText("5");
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

            //seekBar.setProgress(seekBar.getProgress() / 5);
//            YoYo.with(Techniques.Tada).duration(800).playOn(imgFrontHeart);
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
