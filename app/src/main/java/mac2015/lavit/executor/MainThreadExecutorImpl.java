package mac2015.lavit.executor;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by dmacan on 22.9.2015..
 */
public class MainThreadExecutorImpl implements MainThreadExecutor {
    private Handler handler;

    public MainThreadExecutorImpl() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(Runnable runnable) {
        handler.post(runnable);
    }
}
