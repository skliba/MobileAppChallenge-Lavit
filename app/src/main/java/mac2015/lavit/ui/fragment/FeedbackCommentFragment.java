package mac2015.lavit.ui.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.InjectView;
import mac2015.lavit.R;

/**
 * Created by dmacan on 23.9.2015..
 */
public class FeedbackCommentFragment extends FeedbackFragment<String> {

    @InjectView(R.id.etFeedbackComment)
    EditText etFeedbackComment;
    @InjectView(R.id.txtFeedbackComment)
    TextView txtFeedbackComment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feedback_comment, null, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/LobsterTwo.otf");
        txtFeedbackComment.setTypeface(myTypeface);
    }

    @Override
    public String getFeedback() {
        if (etFeedbackComment != null && etFeedbackComment.getText() != null)
            return etFeedbackComment.getText().toString();
        return null;
    }
}
