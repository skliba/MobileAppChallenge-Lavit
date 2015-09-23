package mac2015.lavit.app;

import android.content.Context;

/**
 * Created by dmacan on 22.9.2015..
 */
public abstract class BasePresenter {

    private Context context;

    public BasePresenter(Context context) {
        MyApp.get(context).inject(this);
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

}
