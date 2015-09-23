package mac2015.lavit.app.di;

import java.util.List;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import mac2015.lavit.domain.interactor.api.impl.ApiManagerImpl;
import mac2015.lavit.domain.repository.ListRepository;

/**
 * Created by noxqs on 23.09.15..
 */
@Module(library = true, complete = false)
public class RepositoryModule {

    @Provides
    @Named("api_base_url")
    public String provideBaseApiUrl() {
        return "NEKA_PIZDA_MATERINA";
    }

    @Provides
    @Named("production_api")
    public ListRepository provideListRepository(@Named("api_base_url") String endpoint){
        return new ApiManagerImpl(endpoint);
    }
}
