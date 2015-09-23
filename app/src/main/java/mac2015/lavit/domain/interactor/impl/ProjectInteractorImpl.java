package mac2015.lavit.domain.interactor.impl;

import mac2015.lavit.domain.interactor.AbstractInteractor;
import mac2015.lavit.domain.interactor.ProjectInteractor;
import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.domain.repository.ListRepository;
import mac2015.lavit.executor.InteractorExecutor;
import mac2015.lavit.executor.MainThreadExecutor;
import retrofit.RetrofitError;

/**
 * Created by noxqs on 24.09.15..
 */
public class ProjectInteractorImpl extends AbstractInteractor implements ProjectInteractor {

    private ListRepository listRepository;
    private ProjectModel projectModel;
    private Callback callback;


    public ProjectInteractorImpl(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, ListRepository listRepository) {
        super(interactorExecutor, mainThreadExecutor);
        this.listRepository = listRepository;
    }

    @Override
    public void fetchProjects(Callback callback, ProjectModel model) {
        this.callback = callback;
        this.projectModel = model;

        getInteractorExecutor().execute(this);
    }

    @Override
    public void run() {
        try{
            // final Response<ProjectResponse> projectResponse = listRepository.fetchProjects(projectModel);

            notifySuccess();
        }
        catch(RetrofitError e){
            notifyError(e.getMessage());
        }

    }

    private void notifyError(final String message) {
        getMainThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                callback.onProjectFetchFail(message);
            }
        });
    }

    private void notifySuccess() {
        getMainThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                //TODO TEMP varijabla koju sam stavio samo da ne baca errore u buildu, treba ici lista ili neka druga pizda materina
                //TODO napravi response modele za sve! Odkomentiraj AppService, ListRepository i pazi na ApiManagerImpl kada odkomentiras ListRepository
                ProjectModel model = new ProjectModel();
                callback.onProjectFetchSuccess(model);
            }
        });
    }
}
