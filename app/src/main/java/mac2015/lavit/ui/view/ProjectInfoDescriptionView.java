package mac2015.lavit.ui.view;

import mac2015.lavit.domain.models.ProjectModel;

/**
 * Created by dmacan on 23.9.2015..
 */
public interface ProjectInfoDescriptionView extends View {

    void showDescription(ProjectModel projectModel);

    void showOwners(ProjectModel projectModel);

    void showOverallScore(double score);

    void showTags(ProjectModel.Tag[] tags);
}
