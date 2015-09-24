package mac2015.lavit.domain.interactor;

import retrofit.mime.TypedFile;

/**
 * Created by noxqs on 24.09.15..
 */
public interface SendImageTestInteractor {

    void sendImage(Callback callback, TypedFile file, String projectId);

    interface Callback{

        void onImageSendError(String msg);
        void onImageSendSuccess(String msg);
    }


}
