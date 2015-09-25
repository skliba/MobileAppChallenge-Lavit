package mac2015.lavit.ui.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
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

    private static final String TAG = "DAM_ACT_FEEDBACK";
    @InjectView(R.id.viewPager)
    NonSwipeablePager pager;
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
        feedbackPresenter.setView(this);
        feedbackPresenter.initialize();
        pages = new ArrayList<>();
        adapter = new TabAdapter(getFragmentManager());
        feedbackPresenter.onViewCreate();
    }

    @Override
    public void enableFeedbacks(int... types) {
        Arrays.sort(types);
        pages.add(feedbackRatingFragment);
        for (int type : types) {
            switch (type) {
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
        pager.setOffscreenPageLimit(pages.size());
    }

    @Override
    public void finishFeedback() {
        Toast.makeText(getBaseContext(), "Finishing feedback...", Toast.LENGTH_SHORT).show();
        finish();
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
            btnFeedbackProceed.setTextColor(getResources().getColor(R.color.accent));
            btnFeedbackProceed.setText("Finish");
        } else {
            btnFeedbackProceed.setTextColor(getResources().getColor(R.color.accent2));
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
            btnFeedbackCancel.setTextColor(getResources().getColor(R.color.accent));
            btnFeedbackCancel.setText("Cancel");
        } else {
            btnFeedbackCancel.setTextColor(getResources().getColor(R.color.accent2));
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
        Log.i(TAG, "Total items: " + pages.size());
        Log.i(TAG, "Current item: " + index);
        pager.setCurrentItem(index);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "Activity result: " + requestCode);
        if (requestCode == 0) {
            feedbackPhotoFragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
