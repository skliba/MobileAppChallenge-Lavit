package mac2015.lavit.domain.interactor;

import mac2015.lavit.domain.models.ProjectModel;

/**
 * Created by noxqs on 24.09.15..
 */
public interface ProjectInteractor {

    void fetchProjects(Callback callback, ProjectModel model);

    interface Callback{

        void onProjectFetchFail(String msg);
        void onProjectFetchSuccess(ProjectModel projectModel);
    }
}
