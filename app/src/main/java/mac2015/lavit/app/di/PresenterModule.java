package mac2015.lavit.app.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import mac2015.lavit.ui.presenter.LoginPresenter;
import mac2015.lavit.ui.presenter.MainPresenter;
import mac2015.lavit.ui.presenter.ProjectInfoDescriptionPresenter;
import mac2015.lavit.ui.presenter.ProjectInfoPresenter;
import mac2015.lavit.ui.presenter.RegistrationPresenter;
import mac2015.lavit.ui.presenter.implementation.LoginPresenterImpl;
import mac2015.lavit.ui.presenter.implementation.MainPresenterImpl;
import mac2015.lavit.ui.presenter.implementation.ProjectInfoDescriptionPresenterImpl;
import mac2015.lavit.ui.presenter.implementation.ProjectInfoPresenterImpl;
import mac2015.lavit.ui.presenter.implementation.RegistrationPresenterImpl;

/**
 * Created by dmacan on 23.9.2015..
 */
@Module(library = true, complete = false)
public class PresenterModule {

    @Provides
    public LoginPresenter provideLoginPresenter(Context context) {
        return new LoginPresenterImpl(context);
    }

    @Provides
    public RegistrationPresenter provideRegistrationPresenter(Context context) {
        return new RegistrationPresenterImpl(context);
    }

    @Provides
    public MainPresenter provideMainPresenter(Context context) {
        return new MainPresenterImpl(context);
    }

    @Provides
    public ProjectInfoPresenter provideProjectInfoPresenter(Context context) {
        return new ProjectInfoPresenterImpl(context);
    }

    @Provides
    public ProjectInfoDescriptionPresenter provideProjectInfoDescriptionPresenter(Context context) {
        return new ProjectInfoDescriptionPresenterImpl(context);
    }

}
