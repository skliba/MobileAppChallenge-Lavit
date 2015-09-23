package mac2015.lavit.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.joanzapata.iconify.fonts.MaterialIcons;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.InjectView;
import mac2015.lavit.R;
import mac2015.lavit.app.BaseActivity;
import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.ui.custom.behavior.AppbarOffsetHandler;
import mac2015.lavit.ui.custom.tabs.IconTabAdapter;
import mac2015.lavit.ui.custom.view.ControllableAppBarLayout;
import mac2015.lavit.ui.fragment.ProjectInfoDetailsFragment;
import mac2015.lavit.ui.fragment.ProjectInfoFeedbackFragment;
import mac2015.lavit.ui.presenter.ProjectInfoPresenter;
import mac2015.lavit.ui.util.IntentUtil;
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
    @Inject
    ProjectInfoDetailsFragment projectInfoDetailsFragment;
    @Inject
    ProjectInfoFeedbackFragment projectInfoFeedbackFragment;
    @Inject
    ProjectInfoPresenter projectInfoPresenter;

    IconTabAdapter adapter;

    @Override
    protected int provideLayoutResource() {
        return R.layout.activity_project;
    }

    @Override
    protected void main(Bundle savedInstanceState) {
        setupToolbar();
        projectInfoPresenter.initialize();
        projectInfoPresenter.setView(this);
        projectInfoPresenter.setData(IntentUtil.fetchUser(this), IntentUtil.fetchProject(this));
        projectInfoPresenter.onViewCreate();
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

    private void setupTabs() {
        adapter = new IconTabAdapter(getFragmentManager(), getBaseContext());
        adapter.addTabs(Arrays.asList(projectInfoDetailsFragment, projectInfoFeedbackFragment));
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
        tabs.getTabAt(0).setCustomView(adapter.createView(MaterialIcons.md_info, R.layout.tab_iconic_bottom_padding));
        tabs.getTabAt(1).setCustomView(adapter.createView(MaterialIcons.md_people, R.layout.tab_iconic_bottom_padding));

    }

    @Override
    public void showBasicInfo(ProjectModel projectModel) {
        Picasso.with(getBaseContext()).load(projectModel.getCoverPicture()).into(imgBackdrop);
        setProfileName(projectModel.getName());
    }

    @Override
    public void showDetails(ProjectModel projectModel) {
        projectInfoDetailsFragment.setData(projectModel);
        projectInfoFeedbackFragment.setData(projectModel);
        setupTabs();
    }

    @Override
    public void startFeedback(ProjectModel projectModel) {

    }

    private void setProfileName(String title) {
        int color = getResources().getColor(android.R.color.white);
        collapsingToolbar.setTitle(title);
        collapsingToolbar.setExpandedTitleColor(color);
        collapsingToolbar.setCollapsedTitleTextColor(color);
    }

}
