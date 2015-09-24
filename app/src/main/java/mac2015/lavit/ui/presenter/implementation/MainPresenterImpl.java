package mac2015.lavit.ui.presenter.implementation;

import android.content.Context;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import mac2015.lavit.app.BasePresenter;
import mac2015.lavit.domain.interactor.ProjectInteractor;
import mac2015.lavit.domain.manager.Preferences;
import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.domain.models.User;
import mac2015.lavit.ui.presenter.MainPresenter;
import mac2015.lavit.ui.view.MainView;

/**
 * Created by dmacan on 23.9.2015..
 */
public class MainPresenterImpl extends BasePresenter implements MainPresenter, ProjectInteractor.Callback {

    private static final String TAG = "DAM_PRES_MAIN";
    @Inject
    ProjectInteractor projectInteractor;
    @Inject
    Preferences preferences;

    private MainView mainView;
    private User user;

    public MainPresenterImpl(Context context) {
        super(context);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void onViewCreate() {
        mainView.showProfileInfo(user);
        projectInteractor.fetchProjects(this, preferences.getToken());
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
    public void setView(MainView view) {
        this.mainView = view;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void projectSelected(ProjectModel projectModel) {
        this.mainView.openProject(user, projectModel);
    }

    @Override
    public void onProjectFetchFail(String msg) {
        mainView.showError(msg);
    }

    @Override
    public void onProjectFetchSuccess(List<ProjectModel> data) {
        mainView.showProjects(data);
    }
}
