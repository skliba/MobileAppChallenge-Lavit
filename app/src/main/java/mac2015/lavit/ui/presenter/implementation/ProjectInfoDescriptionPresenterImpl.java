package mac2015.lavit.ui.presenter.implementation;

import android.content.Context;

import mac2015.lavit.app.BasePresenter;
import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.ui.presenter.ProjectInfoDescriptionPresenter;
import mac2015.lavit.ui.view.ProjectInfoDescriptionView;

/**
 * Created by dmacan on 23.9.2015..
 */
public class ProjectInfoDescriptionPresenterImpl extends BasePresenter implements ProjectInfoDescriptionPresenter {

    private ProjectInfoDescriptionView projectInfoDescriptionView;
    private ProjectModel projectModel;

    public ProjectInfoDescriptionPresenterImpl(Context context) {
        super(context);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void onViewCreate() {
        this.projectInfoDescriptionView.showDescription(projectModel);
        this.projectInfoDescriptionView.showOwners(projectModel);
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
    public void setView(ProjectInfoDescriptionView view) {
        this.projectInfoDescriptionView = view;
    }

    @Override
    public void setData(ProjectModel projectModel) {
        this.projectModel = projectModel;
    }
}
