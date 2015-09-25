package mac2015.lavit.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.InjectView;
import butterknife.OnClick;
import mac2015.lavit.R;

/**
 * Created by dmacan on 23.9.2015..
 */
public class FeedbackPhotoFragment extends FeedbackFragment<File> {

    private static final String TAG = "DAM_FRAG_PHOTO";
    @InjectView(R.id.imgFeedbackPhoto)
    ImageView imgFeedbackPhoto;
    @InjectView(R.id.txtPicture)
    TextView txtPicture;


    private File photoFile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_feedback_photo, null, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Typeface myTypeface = Typeface.createFromAsset(getActivity().getAssets(), "fonts/LobsterTwo.otf");
        txtPicture.setTypeface(myTypeface);
    }

    @OnClick(R.id.fab)
    protected void onFabClick() {
        takePicture();
    }

    private void takePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePictureIntent, 0);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imgFeedbackPhoto.setImageBitmap(photo);
            try {
                photoFile = (bitmapToFile(photo));
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private File bitmapToFile(Bitmap bitmap) throws IOException {
        //create a file to write bitmap data
        File f = new File(getActivity().getCacheDir(), "sljika");
        f.createNewFile();

//Convert bitmap to byte array
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100/*ignored for PNG*/, bos);
        byte[] bitmapdata = bos.toByteArray();

//write the bytes in file
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(bitmapdata);
        fos.flush();
        fos.close();
        return f;
    }

    @Override
    public File getFeedback() {
        return photoFile;
    }
}
