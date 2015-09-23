package mac2015.lavit.ui.presenter;

import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.domain.models.User;
import mac2015.lavit.ui.view.ProjectInfoView;

/**
 * Created by dmacan on 23.9.2015..
 */
public interface ProjectInfoPresenter extends Presenter<ProjectInfoView> {

    void onFeedbackSelected();

    void setData(User user, ProjectModel projectModel);

}
