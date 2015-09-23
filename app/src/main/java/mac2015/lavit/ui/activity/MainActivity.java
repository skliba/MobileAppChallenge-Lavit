package mac2015.lavit.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.InjectView;
import mac2015.lavit.R;
import mac2015.lavit.app.BaseActivity;
import mac2015.lavit.domain.models.User;
import mac2015.lavit.ui.presenter.MainPresenter;
import mac2015.lavit.ui.util.IntentUtil;
import mac2015.lavit.ui.view.MainView;

/**
 * Created by dmacan on 23.9.2015..
 */
public class MainActivity extends BaseActivity implements MainView {

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.imgAccountAvatar)
    ImageView imgAccountAvatar;
    @InjectView(R.id.txtAccountName)
    TextView txtAccountName;
    @InjectView(R.id.txtAccountEmail)
    TextView txtAccountEmail;

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected int provideLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void main(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        mainPresenter.initialize();
        mainPresenter.setView(this);
        mainPresenter.setUser(IntentUtil.fetchUser(this));
        mainPresenter.onViewCreate();
    }

    @Override
    public void showProfileInfo(User user) {
        Picasso.with(this).load("http://41.media.tumblr.com/18a5ac1fc70360ea1b2ef476ec9f15f2/tumblr_mgblfiaRi21qarlxmo1_400.png").into(imgAccountAvatar);
        txtAccountEmail.setText(user.getEmail());
        txtAccountName.setText("John Doe");
    }
}
