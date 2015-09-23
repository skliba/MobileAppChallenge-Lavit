package mac2015.lavit.ui.view;

import mac2015.lavit.domain.models.ProjectModel;

/**
 * Created by dmacan on 23.9.2015..
 */
public interface ProjectInfoView extends View {

    void showBasicInfo(ProjectModel projectModel);

    void showDetails(ProjectModel projectModel);

    void startFeedback(ProjectModel projectModel);


}
