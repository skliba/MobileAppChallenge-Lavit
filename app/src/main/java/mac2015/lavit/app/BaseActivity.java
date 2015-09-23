package mac2015.lavit.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by dmacan on 22.9.2015..
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideLayoutResource());
        injectViews();
        injectDependencies();
        main(savedInstanceState);
    }

    protected abstract int provideLayoutResource();

    protected abstract void main(Bundle savedInstanceState);

    private void injectViews() {
        ButterKnife.inject(this);
    }

    private void injectDependencies() {
        MyApp app = MyApp.get(this);
        app.inject(this);
    }

}
