package mac2015.lavit.ui.presenter.implementation;

import android.content.Context;

import mac2015.lavit.app.BasePresenter;
import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.domain.models.User;
import mac2015.lavit.domain.util.Mock;
import mac2015.lavit.ui.presenter.MainPresenter;
import mac2015.lavit.ui.view.MainView;

/**
 * Created by dmacan on 23.9.2015..
 */
public class MainPresenterImpl extends BasePresenter implements MainPresenter {

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
        mainView.showProjects(Mock.mockProjects());
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
}
