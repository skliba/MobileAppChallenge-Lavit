package mac2015.lavit.app.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import mac2015.lavit.domain.repository.api.impl.ApiManagerImpl;
import mac2015.lavit.domain.repository.ListRepository;

/**
 * Created by noxqs on 23.09.15..
 */
@Module(library = true, complete = false)
public class RepositoryModule {

    @Provides
    @Named("api_base_url")
    public String provideBaseApiUrl() {
        return "http://178.62.255.25";
    }

    @Provides
    @Named("api_google_url")
    public String provideBaseGoogleUrl() {
        return "https://www.googleapis.com";
    }

    @Provides
    @Named("production_api")
    public ListRepository provideListRepository(@Named("api_base_url") String endpoint){
        return new ApiManagerImpl(endpoint);
    }

}
