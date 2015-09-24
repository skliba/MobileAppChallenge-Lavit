package mac2015.lavit.domain.interactor;

import java.util.List;

import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.domain.models.response.ProjectResponse;
import mac2015.lavit.domain.models.response.Response;

/**
 * Created by noxqs on 24.09.15..
 */
public interface ProjectInteractor {

    void fetchProjects(Callback callback, String token);

    interface Callback{

        void onProjectFetchFail(String msg);
        void onProjectFetchSuccess(Response<List<ProjectResponse>> projectResponse);
    }
}
