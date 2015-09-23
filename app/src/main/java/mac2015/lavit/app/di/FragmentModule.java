package mac2015.lavit.app.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import mac2015.lavit.ui.fragment.LoginFragment;
import mac2015.lavit.ui.fragment.RegistrationFragment;

/**
 * Created by dmacan on 23.9.2015..
 */
@Module(library = true, complete = false)
public class FragmentModule {

    @Provides
    public LoginFragment provideLoginFragment(Context context) {
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setLabel("Sign in");
        return loginFragment;
    }

    @Provides
    public RegistrationFragment provideRegistrationFragment(Context context) {
        RegistrationFragment registrationFragment = new RegistrationFragment();
        registrationFragment.setLabel("Sign up");
        return registrationFragment;
    }

}
