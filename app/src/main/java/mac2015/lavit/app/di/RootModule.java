package mac2015.lavit.app.di;

import android.content.Context;
import android.view.LayoutInflater;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mac2015.lavit.app.MyApp;
import mac2015.lavit.ui.activity.FeedbackActivity;
import mac2015.lavit.ui.activity.LoginActivity;
import mac2015.lavit.ui.activity.MainActivity;
import mac2015.lavit.ui.activity.ProjectInfoActivity;
import mac2015.lavit.ui.fragment.FeedbackCommentFragment;
import mac2015.lavit.ui.fragment.FeedbackGeolocationFragment;
import mac2015.lavit.ui.fragment.FeedbackPhotoFragment;
import mac2015.lavit.ui.fragment.FeedbackRatingFragment;
import mac2015.lavit.ui.fragment.LoginFragment;
import mac2015.lavit.ui.fragment.ProjectInfoDetailsFragment;
import mac2015.lavit.ui.fragment.ProjectInfoFeedbackFragment;
import mac2015.lavit.ui.fragment.RegistrationFragment;
import mac2015.lavit.ui.presenter.implementation.FeedbackPresenterImpl;
import mac2015.lavit.ui.presenter.implementation.LoginPresenterImpl;
import mac2015.lavit.ui.presenter.implementation.MainPresenterImpl;
import mac2015.lavit.ui.presenter.implementation.ProjectInfoDescriptionPresenterImpl;
import mac2015.lavit.ui.presenter.implementation.ProjectInfoPresenterImpl;
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
                FragmentModule.class,
                AdapterModule.class
        },
        injects = {
                // App
                MyApp.class,
                // Activity                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
                LoginActivity.class,
                MainActivity.class,
                ProjectInfoActivity.class,
                FeedbackActivity.class,
                // Fragment
                LoginFragment.class,
                RegistrationFragment.class,
                ProjectInfoDetailsFragment.class,
                ProjectInfoFeedbackFragment.class,
                FeedbackCommentFragment.class,
                FeedbackRatingFragment.class,
                FeedbackPhotoFragment.class,
                FeedbackGeolocationFragment.class,
                // View
                // Presenter
                LoginPresenterImpl.class,
                RegistrationPresenterImpl.class,
                MainPresenterImpl.class,
                ProjectInfoPresenterImpl.class,
                ProjectInfoDescriptionPresenterImpl.class,
                FeedbackPresenterImpl.class
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
