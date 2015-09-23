package mac2015.lavit.ui.activity;

import android.os.Bundle;

import mac2015.lavit.R;
import mac2015.lavit.app.BaseActivity;
import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.ui.view.ProjectInfoView;

/**
 * Created by dmacan on 23.9.2015..
 */
public class ProjectInfoActivity extends BaseActivity implements ProjectInfoView {
    @Override
    protected int provideLayoutResource() {
        return R.layout.activity_project;
    }

    @Override
    protected void main(Bundle savedInstanceState) {

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
