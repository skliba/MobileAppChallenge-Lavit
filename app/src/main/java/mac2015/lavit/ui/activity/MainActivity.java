package mac2015.lavit.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.InjectView;
import mac2015.lavit.R;
import mac2015.lavit.app.BaseActivity;

/**
 * Created by dmacan on 23.9.2015..
 */
public class MainActivity extends BaseActivity {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected int provideLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void main(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
    }
}
