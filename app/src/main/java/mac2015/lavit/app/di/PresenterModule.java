package mac2015.lavit.app.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import mac2015.lavit.ui.presenter.LoginPresenter;
import mac2015.lavit.ui.presenter.RegistrationPresenter;
import mac2015.lavit.ui.presenter.implementation.LoginPresenterImpl;
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

    public RegistrationPresenter provideRegistrationPresenter(Context context) {
        return new RegistrationPresenterImpl(context);
    }

}
