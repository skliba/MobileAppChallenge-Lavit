package mac2015.lavit.app.di;

import android.content.Context;

import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import mac2015.lavit.domain.manager.GoogleApiManager;
import mac2015.lavit.domain.manager.Preferences;
import mac2015.lavit.domain.manager.ValidationManager;
import mac2015.lavit.ui.custom.behavior.AppbarOffsetHandler;

/**
 * Created by dmacan on 23.9.2015..
 */
@Module(library = true, complete = false)
public class ManagerModule {

    @Provides
    public ValidationManager provideValidationManager(Context context) {
        return new ValidationManager(context);
    }

    @Provides
    public AppbarOffsetHandler provideAppbarOffsetHandler() {
        return new AppbarOffsetHandler();
    }

    @Provides
    GoogleApiManager provideGoogleApiManager(Context context) {
        return new GoogleApiManager(context);
    }

    @Provides
    Preferences provideSharedPreferences(Context context) {
        return new Preferences(context);
    }

    @Provides
    public Picasso provideSafePicasso(Context context, final Preferences preferences) {
        OkHttpClient picassoClient = new OkHttpClient();
        picassoClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder()
                        .addHeader("X-Api-Token", preferences.getToken())
                        .build();
                return chain.proceed(newRequest);
            }
        });
        return new Picasso.Builder(context).downloader(new OkHttpDownloader(picassoClient)).build();
    }
}
