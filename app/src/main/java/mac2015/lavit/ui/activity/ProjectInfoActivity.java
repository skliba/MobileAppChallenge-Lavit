package mac2015.lavit.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import javax.inject.Inject;

import butterknife.InjectView;
import mac2015.lavit.R;
import mac2015.lavit.app.BaseActivity;
import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.ui.custom.behavior.AppbarOffsetHandler;
import mac2015.lavit.ui.custom.tabs.IconTabAdapter;
import mac2015.lavit.ui.custom.view.ControllableAppBarLayout;
import mac2015.lavit.ui.view.ProjectInfoView;

/**
 * Created by dmacan on 23.9.2015..
 */
public class ProjectInfoActivity extends BaseActivity implements ProjectInfoView {


    @InjectView(R.id.imgBackdrop)
    ImageView imgBackdrop;
    @InjectView(R.id.fab)
    FloatingActionButton fab;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.tabs)
    TabLayout tabs;
    @InjectView(R.id.viewPager)
    ViewPager viewPager;
    @InjectView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @InjectView(R.id.appbar)
    ControllableAppBarLayout appbar;

    @Inject
    AppbarOffsetHandler appbarOffsetHandler;

    IconTabAdapter adapter;

    @Override
    protected int provideLayoutResource() {
        return R.layout.activity_project;
    }

    @Override
    protected void main(Bundle savedInstanceState) {
        setupToolbar();
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            appbarOffsetHandler.setOnTresholdListener(new AppbarOffsetHandler.OnTresholdListener() {
                @Override
                public void onTreshold(boolean within) {
                    if (within) {
                        fab.show();
                    } else {
                        fab.hide();
                    }
                }
            });
            appbar.addOnOffsetChangedListener(appbarOffsetHandler);
        }
    }

    @Override
    public void showBasicInfo(ProjectModel projectModel) {

    }

    @Override
    public void showDetails(ProjectModel projectModel) {

    }

    @Override
    public void startFeedback(ProjectModel projectModel) {

    }
}
