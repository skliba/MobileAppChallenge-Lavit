package mac2015.lavit.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnPageChange;
import mac2015.lavit.R;
import mac2015.lavit.app.BaseActivity;
import mac2015.lavit.ui.custom.tabs.TabAdapter;
import mac2015.lavit.ui.fragment.LoginFragment;
import mac2015.lavit.ui.fragment.RegistrationFragment;

/**
 * Created by dmacan on 22.9.2015..
 */
public class LoginActivity extends BaseActivity {

    @Inject
    LoginFragment loginFragment;
    @Inject
    RegistrationFragment registrationFragment;

    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.viewpager)
    ViewPager viewPager;
    @InjectView(R.id.tabs)
    TabLayout tabs;
    TabAdapter tabAdapter;

    @Override
    protected int provideLayoutResource() {
        return R.layout.activity_login;
    }

    @Override
    protected void main(Bundle savedInstanceState) {
        setSupportActionBar(toolbar);
        setupTabs();
    }

    private void setupTabs() {
        tabAdapter = new TabAdapter(getFragmentManager());
        tabAdapter.addTabs(Arrays.asList(loginFragment, registrationFragment));
        viewPager.setAdapter(tabAdapter);
        tabs.setupWithViewPager(viewPager);
    }

    @OnPageChange(R.id.viewpager)
    protected void onPageChanged(int position) {
        switch (position) {
            case 0: // Login
                loginFragment.onFocusChanged(true);
                loginFragment.onFocusChanged(false);
                break;
            case 1: // Registration
                registrationFragment.onFocusChanged(true);
                registrationFragment.onFocusChanged(false);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
