package mac2015.lavit.ui.presenter;

import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.ui.view.ProjectInfoDescriptionView;

/**
 * Created by dmacan on 23.9.2015..
 */
public interface ProjectInfoDescriptionPresenter extends Presenter<ProjectInfoDescriptionView> {

    void setData(ProjectModel projectModel);

}
