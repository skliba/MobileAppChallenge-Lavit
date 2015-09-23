package mac2015.lavit.app.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import mac2015.lavit.domain.interactor.LoginGoogleInteractor;
import mac2015.lavit.domain.interactor.LoginInteractor;
import mac2015.lavit.domain.interactor.RegistrationInteractor;
import mac2015.lavit.domain.interactor.impl.LoginGoogleInteractorImpl;
import mac2015.lavit.domain.interactor.impl.LoginInteractorImpl;
import mac2015.lavit.domain.interactor.impl.RegistrationInteractorImpl;
import mac2015.lavit.domain.repository.ListRepository;
import mac2015.lavit.executor.InteractorExecutor;
import mac2015.lavit.executor.MainThreadExecutor;

/**
 * Created by noxqs on 23.09.15..
 */

@Module(library = true, complete = false)
public class InteractorModule {

    @Provides
    @Named("interactor_registration_standard")
    public RegistrationInteractor provideRegistrationInteractor (InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, @Named("production_api")ListRepository listRepository){
        return new RegistrationInteractorImpl(interactorExecutor, mainThreadExecutor, listRepository);
    }

    @Provides
    @Named("interactor_login_standard")
    public LoginInteractor provideLoginInteractor(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, @Named("production_api") ListRepository listRepository){
        return new LoginInteractorImpl(interactorExecutor,mainThreadExecutor, listRepository);
    }

    @Provides
    @Named("interactor_login_google")
    public LoginGoogleInteractor provideLoginGoogleInteractor(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, @Named("google_api") ListRepository listRepository, @Named("api_google_url") String endpoint){
        return new LoginGoogleInteractorImpl(interactorExecutor, mainThreadExecutor, listRepository, endpoint);
    }
}
