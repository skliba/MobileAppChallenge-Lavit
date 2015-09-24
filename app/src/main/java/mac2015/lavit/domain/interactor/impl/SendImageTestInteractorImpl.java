package mac2015.lavit.domain.interactor.impl;

import java.io.File;

import mac2015.lavit.domain.interactor.AbstractInteractor;
import mac2015.lavit.domain.interactor.SendImageTestInteractor;
import mac2015.lavit.domain.repository.ListRepository;
import mac2015.lavit.domain.repository.api.impl.ApiManagerImpl;
import mac2015.lavit.executor.InteractorExecutor;
import mac2015.lavit.executor.MainThreadExecutor;
import retrofit.RetrofitError;
import retrofit.mime.TypedFile;

/**
 * Created by noxqs on 24.09.15..
 */
public class SendImageTestInteractorImpl extends AbstractInteractor implements SendImageTestInteractor {

    private ListRepository listRepository;
    private File file;
    private TypedFile typedFile;
    private Callback callback;
    private String projectId;


    public SendImageTestInteractorImpl(InteractorExecutor interactorExecutor, MainThreadExecutor mainThreadExecutor, ListRepository listRepository) {
        super(interactorExecutor, mainThreadExecutor);
        this.listRepository = listRepository;
    }

    @Override
    public void run() {
        try{
            typedFile = new TypedFile("image/jpeg", file);
            String response = listRepository.sendImage(typedFile, projectId);

            notifySuccess(response);
        }
        catch(RetrofitError e){
            notifyError();
        }
    }


    private void notifySuccess(final String response) {
        getMainThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                callback.onImageSendSuccess(response);
            }
        });
    }

    private void notifyError() {
        getMainThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                callback.onImageSendError("Error on sending image");
            }
        });
    }

    @Override
    public void sendImage(Callback callback, File file, String projectId) {
        this.callback = callback;
        this.file = file;
        this.projectId = projectId;

        getInteractorExecutor().execute(this);
    }
}
