package mac2015.lavit.app.di;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.nlopez.smartadapters.SmartAdapter;
import io.nlopez.smartadapters.adapters.RecyclerMultiAdapter;
import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.ui.view.implementation.ProjectViewImpl;

/**
 * Created by dmacan on 23.9.2015..
 */
@Module(library = true, complete = false)
public class AdapterModule {

    @Provides
    @Named("projects")
    public RecyclerMultiAdapter provideProjectCollectionAdapter() {
        return SmartAdapter.empty().map(ProjectModel.class, ProjectViewImpl.class).recyclerAdapter();
    }

}
