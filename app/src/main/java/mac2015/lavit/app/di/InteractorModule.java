package mac2015.lavit.app.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import mac2015.lavit.domain.interactor.FetchFeedbackInteractor;
import mac2015.lavit.domain.interactor.LoginGoogleInteractor;
import mac2015.lavit.domain.interactor.LoginInteractor;
import mac2015.lavit.domain.interactor.ProjectInteractor;
import mac2015.lavit.domain.interactor.RegistrationInteractor;
import mac2015.lavit.domain.interactor.SendFeedbackInteractor;
import mac2015.lavit.domain.interactor.impl.FetchFeedbackInteractorImpl;
import mac2015.lavit.domain.interactor.impl.LoginGoogleInteractorImpl;
import mac2015.lavit.domain.interactor.impl.LoginInteractorImpl;
import mac2015.lavit.domain.interactor.impl.ProjectInteractorImpl;
import mac2015.lavit.domain.interactor.impl.RegistrationInteractorImpl;
import mac2015.lavit.domain.interactor.impl.SendFeedbackInteractorImpl;
import mac2015.lavit.domain.repository.ListRepository;
import mac2015.lavit.domain.repository.api.impl.ApiManagerImpl;
import mac2015.lavit.executor.InteractorExecutor;
import mac2015.lavit.executor.MainThreadExecutor;

/**
 * Created by noxqs on 23.09.15..
 */

@Module(library = true, complete = false)
public class InteractorModule {

    @Provides
    public RegistrationInteractor provideRegistrationInteractor(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, @Named("production_api") ListRepository listRepository) {
        return new RegistrationInteractorImpl(interactorExecutor, mainThreadExecutor, listRepository);
    }

    @Provides
    public LoginInteractor provideLoginInteractor(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, @Named("production_api") ListRepository listRepository) {
        return new LoginInteractorImpl(interactorExecutor, mainThreadExecutor, listRepository);
    }

    @Provides
    public LoginGoogleInteractor provideLoginGoogleInteractor(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, @Named("production_api") ListRepository listRepository, @Named("api_google_url") String endpoint) {
        return new LoginGoogleInteractorImpl(interactorExecutor, mainThreadExecutor, listRepository, endpoint);
    }

    @Provides
    public ProjectInteractor provideProjectInteractor(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, @Named("production_api") ListRepository listRepository) {
        return new ProjectInteractorImpl(interactorExecutor, mainThreadExecutor, listRepository);
    }

    @Provides
    public SendFeedbackInteractor provideSendFeedbackInteractor(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, @Named("api_base_url") String endpoint) {
        return new SendFeedbackInteractorImpl(interactorExecutor, mainThreadExecutor, new ApiManagerImpl(endpoint));
    }

    @Provides
    public FetchFeedbackInteractor provideFetchFeedbackInteractor(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, @Named("production_api") ListRepository listRepository){
        return new FetchFeedbackInteractorImpl(interactorExecutor, mainThreadExecutor, listRepository);
    }
}
