package mac2015.lavit.domain.interactor.impl;

import java.util.List;

import mac2015.lavit.domain.interactor.AbstractInteractor;
import mac2015.lavit.domain.interactor.ProjectInteractor;
import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.domain.models.response.Response;
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
    private String token;


    public ProjectInteractorImpl(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, ListRepository listRepository) {
        super(interactorExecutor, mainThreadExecutor);
        this.listRepository = listRepository;
    }

    @Override
    public void run() {
        try {
            Response<List<ProjectModel>> projectResponse = listRepository.fetchProjects(token);
            notifySuccess(projectResponse.getData());
        } catch (RetrofitError e) {
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

    private void notifySuccess(final List<ProjectModel> data) {
        getMainThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                //TODO TEMP varijabla koju sam stavio samo da ne baca errore u buildu, treba ici lista ili neka druga pizda materina
                //TODO napravi response modele za sve! Odkomentiraj AppService, ListRepository i pazi na ApiManagerImpl kada odkomentiras ListRepository
                // Stari moj, al si ga ti zakomplicirao s ovim response ugnježđivanjima :D
                callback.onProjectFetchSuccess(data);
            }
        });
    }

    @Override
    public void fetchProjects(Callback callback, String token) {
        this.callback = callback;
        this.token = token;
        getInteractorExecutor().execute(this);
    }
}
