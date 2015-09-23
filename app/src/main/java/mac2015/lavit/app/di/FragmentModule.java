package mac2015.lavit.app.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import mac2015.lavit.ui.fragment.FeedbackCommentFragment;
import mac2015.lavit.ui.fragment.FeedbackGeolocationFragment;
import mac2015.lavit.ui.fragment.FeedbackPhotoFragment;
import mac2015.lavit.ui.fragment.FeedbackRatingFragment;
import mac2015.lavit.ui.fragment.LoginFragment;
import mac2015.lavit.ui.fragment.ProjectInfoDetailsFragment;
import mac2015.lavit.ui.fragment.ProjectInfoFeedbackFragment;
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

    @Provides
    public ProjectInfoFeedbackFragment provideProjectInfoFeedbackFragment(Context context) {
        ProjectInfoFeedbackFragment projectInfoFeedbackFragment = new ProjectInfoFeedbackFragment();
        projectInfoFeedbackFragment.setLabel("Feedback");
        return projectInfoFeedbackFragment;
    }

    @Provides
    public ProjectInfoDetailsFragment provideProjectInfoDetailsFragment(Context context) {
        ProjectInfoDetailsFragment projectInfoDetailsFragment = new ProjectInfoDetailsFragment();
        projectInfoDetailsFragment.setLabel("Info");
        return projectInfoDetailsFragment;
    }

    @Provides
    public FeedbackRatingFragment provideFeedbackRatingFragment(Context context) {
        return new FeedbackRatingFragment();
    }

    @Provides
    public FeedbackCommentFragment provideFeedbackCommentFragment() {
        return new FeedbackCommentFragment();
    }

    @Provides
    public FeedbackPhotoFragment provideFeedbackPhotoFragment() {
        return new FeedbackPhotoFragment();
    }

    @Provides
    public FeedbackGeolocationFragment provideFeedbackGeolocationFragment() {
        return new FeedbackGeolocationFragment();
    }

}
