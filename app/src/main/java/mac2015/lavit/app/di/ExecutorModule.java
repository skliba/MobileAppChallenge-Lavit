package mac2015.lavit.app.di;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mac2015.lavit.executor.InteractorExecutor;
import mac2015.lavit.executor.MainThreadExecutor;
import mac2015.lavit.executor.MainThreadExecutorImpl;
import mac2015.lavit.executor.ThreadExecutor;

/**
 * Created by dmacan on 22.9.2015..
 */
@Module(library = true, complete = false)
public class ExecutorModule {
    @Provides
    @Singleton
    public InteractorExecutor provideInteractorExecutor() {
        return new ThreadExecutor();
    }

    @Provides
    @Singleton
    public MainThreadExecutor provideMainThreadExecutor() {
        return new MainThreadExecutorImpl();
    }
}
