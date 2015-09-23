package mac2015.lavit.ui.activity;

import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;
import mac2015.lavit.R;
import mac2015.lavit.app.BaseActivity;
import mac2015.lavit.domain.models.FeedbackTypes;
import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.domain.models.User;
import mac2015.lavit.ui.custom.tabs.TabAdapter;
import mac2015.lavit.ui.custom.view.NonSwipeablePager;
import mac2015.lavit.ui.fragment.FeedbackCommentFragment;
import mac2015.lavit.ui.fragment.FeedbackFragment;
import mac2015.lavit.ui.fragment.FeedbackGeolocationFragment;
import mac2015.lavit.ui.fragment.FeedbackPhotoFragment;
import mac2015.lavit.ui.fragment.FeedbackRatingFragment;
import mac2015.lavit.ui.presenter.FeedbackPresenter;
import mac2015.lavit.ui.util.IntentUtil;
import mac2015.lavit.ui.view.FeedbackView;

/**
 * Created by dmacan on 23.9.2015..
 */
public class FeedbackActivity extends BaseActivity implements FeedbackView {

    @InjectView(R.id.appbar)
    AppBarLayout appbar;
    @InjectView(R.id.viewPager)
    NonSwipeablePager pager;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.btnFeedbackCancel)
    Button btnFeedbackCancel;
    @InjectView(R.id.btnFeedbackProceed)
    Button btnFeedbackProceed;

    TabAdapter adapter;

    @Inject
    FeedbackRatingFragment feedbackRatingFragment;
    @Inject
    FeedbackCommentFragment feedbackCommentFragment;
    @Inject
    FeedbackPhotoFragment feedbackPhotoFragment;
    @Inject
    FeedbackGeolocationFragment feedbackGeolocationFragment;
    @Inject
    FeedbackPresenter feedbackPresenter;

    List<FeedbackFragment> pages;

    @Override
    protected int provideLayoutResource() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void main(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        feedbackPresenter.setView(this);
        feedbackPresenter.initialize();
        pages = new ArrayList<>();
        adapter = new TabAdapter(getFragmentManager());
        feedbackPresenter.onViewCreate();
    }

    @Override
    public void enableFeedbacks(int... types) {
        Arrays.sort(types);
        for (int type : types) {
            switch (type) {
                case FeedbackTypes.RATING:
                    pages.add(feedbackRatingFragment);
                    break;
                case FeedbackTypes.COMMENT:
                    pages.add(feedbackCommentFragment);
                    break;
                case FeedbackTypes.IMAGE:
                    pages.add(feedbackPhotoFragment);
                    break;
                case FeedbackTypes.GEOLOCATION:
                    pages.add(feedbackGeolocationFragment);
                    break;
            }
        }
        adapter.addTabs(pages);
        pager.setPagingEnabled(false);
        pager.setAdapter(adapter);
    }

    @Override
    public void finishFeedback() {
        Toast.makeText(getBaseContext(), "Finishing feedback...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void cancelFeedback() {
        finish();
    }

    @Override
    public Double getFeedbackRating() {
        return feedbackRatingFragment.getFeedback();
    }

    @Override
    public String getFeedbackComment() {
        return feedbackCommentFragment.getFeedback();
    }

    @Override
    public Location getFeedbackLocation() {
        return feedbackGeolocationFragment.getFeedback();
    }

    @Override
    public File getFeedbackPhoto() {
        return feedbackPhotoFragment.getFeedback();
    }

    @Override
    public ProjectModel getProject() {
        return IntentUtil.fetchProject(this);
    }

    @Override
    public User getUser() {
        return IntentUtil.fetchUser(this);
    }

    @Override
    public void toggleLastPage(boolean lastPage) {
        if (lastPage) {
            btnFeedbackProceed.setText("Finish");
        } else {
            btnFeedbackProceed.setText("Next");
        }
    }

    @OnClick(R.id.btnFeedbackCancel)
    protected void onBtnFeedbackCancelClick() {
        feedbackPresenter.onCancelSelected();
    }

    @OnClick(R.id.btnFeedbackProceed)
    protected void onBtnFeedbackProceedClick() {
        feedbackPresenter.onProceedSelected();
    }

    @Override
    public void toggleFirstPage(boolean firstPage) {
        if (firstPage) {
            btnFeedbackCancel.setText("Cancel");
        } else {
            btnFeedbackCancel.setText("Previous");
        }
    }

    @Override
    public void showConfirmation(String question) {

    }

    @Override
    public void showStatus(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void turnPage(int index) {
        pager.setCurrentItem(index);
    }

}
