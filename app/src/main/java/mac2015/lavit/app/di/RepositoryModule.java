package mac2015.lavit.app.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import mac2015.lavit.domain.repository.ListRepository;
import mac2015.lavit.domain.repository.api.impl.ApiManagerImpl;

/**
 * Created by noxqs on 23.09.15..
 */
@Module(library = true, complete = false)
public class RepositoryModule {

    @Provides
    @Named("api_base_url")
    public String provideBaseApiUrl() {
        return "http://178.62.255.25:8000";
    }

    @Provides
    @Named("api_google_url")
    public String provideBaseGoogleUrl() {
        return "https://www.googleapis.com";
    }

    @Provides
    @Named("production_api")
    public ListRepository provideListRepository(@Named("zoka_image_url") String endpoint) {
        return new ApiManagerImpl(endpoint);
    }

    @Provides
    @Named("zoka_image_url")
    public String provideZokaUrl() {
        return "https://oazugwvbnh.localtunnel.me";
    }

    @Provides
    @Named("test_image_url")
    public String provideImageBaseUrl() {
        return "https://hlslnvjwnd.localtunnel.me";
    }

}
