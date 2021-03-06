package mac2015.lavit.app.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import mac2015.lavit.domain.manager.ValidationManager;

/**
 * Created by dmacan on 23.9.2015..
 */
@Module(library = true, complete = false)
public class ManagerModule {

    @Provides
    public ValidationManager provideValidationManager(Context context) {
        return new ValidationManager(context);
    }

}
