package mac2015.lavit.ui.view;

import android.location.Location;

import java.io.File;

import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.domain.models.User;

/**
 * Created by dmacan on 24.9.2015..
 */
public interface FeedbackView extends View {

    void enableFeedbacks(int... types);

    void finishFeedback();

    void cancelFeedback();

    Double getFeedbackRating();

    String getFeedbackComment();

    Location getFeedbackLocation();

    File getFeedbackPhoto();

    ProjectModel getProject();

    User getUser();

    void toggleLastPage(boolean lastPage);

    void toggleFirstPage(boolean firstPage);

    void showConfirmation(String question);

    void showStatus(String message);

    void turnPage(int index);

}
