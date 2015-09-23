package mac2015.lavit.ui.presenter;


import mac2015.lavit.ui.view.View;

/**
 * Created by dmacan on 22.9.2015..
 */
public interface Presenter<T extends View> {

    void initialize();

    void onViewCreate();

    void onViewResume();

    void onViewPause();

    void onViewDestroy();

    void setView(T view);
}
