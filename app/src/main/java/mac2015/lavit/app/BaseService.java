package mac2015.lavit.app;

import android.app.Service;

/**
 * Created by dmacan on 22.9.2015..
 */
public abstract class BaseService extends Service {

    @Override
    public void onCreate() {
        super.onCreate();
        injectDependencies();
    }

    private void injectDependencies() {
        MyApp app = MyApp.get(this);
        app.inject(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
