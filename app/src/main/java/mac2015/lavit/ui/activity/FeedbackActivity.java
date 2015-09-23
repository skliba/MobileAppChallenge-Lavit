package mac2015.lavit.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;

import butterknife.InjectView;
import mac2015.lavit.R;
import mac2015.lavit.app.BaseActivity;
import mac2015.lavit.ui.custom.view.NonSwipeablePager;

/**
 * Created by dmacan on 23.9.2015..
 */
public class FeedbackActivity extends BaseActivity {

    @InjectView(R.id.appbar)
    AppBarLayout appbar;
    @InjectView(R.id.viewPager)
    NonSwipeablePager pager;

    @Override
    protected int provideLayoutResource() {
        return R.layout.activity_feedback;
    }

    @Override
    protected void main(Bundle savedInstanceState) {

    }
}
