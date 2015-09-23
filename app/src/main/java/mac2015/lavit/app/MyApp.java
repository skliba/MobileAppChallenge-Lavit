package mac2015.lavit.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.MaterialModule;

import dagger.ObjectGraph;
import mac2015.lavit.app.di.RootModule;

/**
 * Created by dmacan on 22.9.2015..
 */
public class MyApp extends Application {

    private ObjectGraph objectGraph;

    public static MyApp get(Context context) {
        return (MyApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Iconify.with(new MaterialModule());
        objectGraph = ObjectGraph.create(new RootModule(this));
        objectGraph.inject(this);
    }

    public void inject(Object object) {
        objectGraph.inject(object);
    }

    public void addModules(Object... modules) {
        objectGraph.plus(modules);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
