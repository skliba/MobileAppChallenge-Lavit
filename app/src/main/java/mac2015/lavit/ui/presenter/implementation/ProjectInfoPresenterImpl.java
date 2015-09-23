package mac2015.lavit.ui.presenter.implementation;

import android.content.Context;

import mac2015.lavit.app.BasePresenter;
import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.domain.models.User;
import mac2015.lavit.ui.presenter.ProjectInfoPresenter;
import mac2015.lavit.ui.view.ProjectInfoView;

/**
 * Created by dmacan on 23.9.2015..
 */
public class ProjectInfoPresenterImpl extends BasePresenter implements ProjectInfoPresenter {

    private User user;
    private ProjectModel projectModel;
    private ProjectInfoView projectInfoView;

    public ProjectInfoPresenterImpl(Context context) {
        super(context);
    }

    @Override
    public void onFeedbackSelected() {
        this.projectInfoView.startFeedback(projectModel);
    }

    @Override
    public void setData(User user, ProjectModel projectModel) {
        this.user = user;
        this.projectModel = projectModel;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void onViewCreate() {
        this.projectInfoView.showBasicInfo(projectModel);
        this.projectInfoView.showDetails(projectModel);
    }

    @Override
    public void onViewResume() {

    }

    @Override
    public void onViewPause() {

    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void setView(ProjectInfoView view) {
        this.projectInfoView = view;
    }
}
