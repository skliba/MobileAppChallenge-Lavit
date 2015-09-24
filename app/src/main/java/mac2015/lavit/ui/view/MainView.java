package mac2015.lavit.ui.view;

import java.util.List;

import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.domain.models.User;

/**
 * Created by dmacan on 23.9.2015..
 */
public interface MainView extends View {

    void showProfileInfo(User user);

    void showProjects(List<ProjectModel> projects);

    void openProject(User user, ProjectModel project);

    void showError(String msg);
}
