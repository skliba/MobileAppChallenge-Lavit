package mac2015.lavit.app.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import mac2015.lavit.domain.interactor.RegistrationInteractor;
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
    public RegistrationInteractor provideRegistrationInteractor (InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, @Named("production_api")ListRepository listRepository){
        return new RegistrationInteractorImpl(interactorExecutor, mainThreadExecutor, listRepository);
    }
}
