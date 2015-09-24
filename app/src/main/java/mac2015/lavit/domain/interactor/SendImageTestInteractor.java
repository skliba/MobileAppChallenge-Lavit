package mac2015.lavit.domain.interactor;

import java.io.File;

import retrofit.mime.TypedFile;

/**
 * Created by noxqs on 24.09.15..
 */
public interface SendImageTestInteractor {

    void sendImage(Callback callback, File file, long projectId);

    interface Callback{

        void onImageSendError(String msg);
        void onImageSendSuccess(String msg);
    }


}
