package mac2015.lavit.domain.interactor;


import mac2015.lavit.executor.Interactor;
import mac2015.lavit.executor.InteractorExecutor;
import mac2015.lavit.executor.MainThreadExecutor;

/**
 * Created by dmacan on 22.9.2015..
 */
public abstract class AbstractInteractor implements Interactor {

    private InteractorExecutor interactorExecutor;
    private MainThreadExecutor mainThreadExecutor;

    public AbstractInteractor(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor) {
        this.interactorExecutor = interactorExecutor;
        this.mainThreadExecutor = mainThreadExecutor;
    }

    public InteractorExecutor getInteractorExecutor() {
        return interactorExecutor;
    }

    public void setInteractorExecutor(InteractorExecutor interactorExecutor) {
        this.interactorExecutor = interactorExecutor;
    }

    public MainThreadExecutor getMainThreadExecutor() {
        return mainThreadExecutor;
    }

    public void setMainThreadExecutor(MainThreadExecutor mainThreadExecutor) {
        this.mainThreadExecutor = mainThreadExecutor;
    }

}
