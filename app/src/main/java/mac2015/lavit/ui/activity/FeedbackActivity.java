package mac2015.lavit.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import mac2015.lavit.R;
import mac2015.lavit.app.BaseActivity;
import mac2015.lavit.ui.custom.view.NonSwipeablePager;
import mac2015.lavit.ui.fragment.FeedbackCommentFragment;
import mac2015.lavit.ui.fragment.FeedbackFragment;
import mac2015.lavit.ui.fragment.FeedbackGeolocationFragment;
import mac2015.lavit.ui.fragment.FeedbackPhotoFragment;
import mac2015.lavit.ui.fragment.FeedbackRatingFragment;

/**
 * Created by dmacan on 23.9.2015..
 */
public class FeedbackActivity extends BaseActivity {

    @InjectView(R.id.appbar)
    AppBarLayout appbar;
    @InjectView(R.id.viewPager)
    NonSwipeablePager pager;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    FeedbackRatingFragment feedbackRatingFragment;
    @Inject
    FeedbackCommentFragment feedbackCommentFragment;
    @Inject
    FeedbackPhotoFragment feedbackPhotoFragment;
    @Inject
    FeedbackGeolocationFragment feedbackGeolocationFragment;

    List<FeedbackFragment> pages;

    @Override
    protected int provideLayoutResource() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void main(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        pages = new ArrayList<>();
    }
}
