package mac2015.lavit.ui.presenter;

import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.domain.models.User;
import mac2015.lavit.ui.view.MainView;

/**
 * Created by dmacan on 23.9.2015..
 */
public interface MainPresenter extends Presenter<MainView> {

    void setUser(User user);

    void projectSelected(ProjectModel projectModel);

    void onLogoutSelected();
}
