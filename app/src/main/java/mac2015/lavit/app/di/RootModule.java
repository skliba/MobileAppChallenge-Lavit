package mac2015.lavit.app.di;

import android.content.Context;
import android.view.LayoutInflater;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mac2015.lavit.app.MyApp;
import mac2015.lavit.ui.activity.LoginActivity;
import mac2015.lavit.ui.activity.MainActivity;
import mac2015.lavit.ui.fragment.LoginFragment;
import mac2015.lavit.ui.fragment.RegistrationFragment;
import mac2015.lavit.ui.presenter.implementation.LoginPresenterImpl;
import mac2015.lavit.ui.presenter.implementation.RegistrationPresenterImpl;

/**
 * Created by dmacan on 22.9.2015..
 */
@Module(
        includes = {
                ExecutorModule.class,
                ManagerModule.class,
                PresenterModule.class,
                InteractorModule.class,
                RepositoryModule.class,
                FragmentModule.class
        },
        injects = {
                // App
                MyApp.class,
                // Activity
                LoginActivity.class,
                MainActivity.class,
                // Fragment
                LoginFragment.class,
                RegistrationFragment.class,
                // View
                // Presenter
                LoginPresenterImpl.class,
                RegistrationPresenterImpl.class
                // Service
        },
        library = true
)
public class RootModule {

    private final Context context;

    public RootModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideApplicationContext() {
        return context;
    }

    @Provides
    public LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(context);
    }

}
