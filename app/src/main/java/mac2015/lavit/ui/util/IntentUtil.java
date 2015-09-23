package mac2015.lavit.ui.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.domain.models.User;
import mac2015.lavit.domain.util.Serializator;
import mac2015.lavit.ui.activity.FeedbackActivity;
import mac2015.lavit.ui.activity.MainActivity;
import mac2015.lavit.ui.activity.ProjectInfoActivity;

/**
 * Created by dmacan on 23.9.2015..
 */
public class IntentUtil {

    private static final String KEY_USER = "mac2015.lavit.user";
    private static final String KEY_PROJECT = "mac2015.lavit.project";

    public static Intent startMainActivity(Context context, User user) {
        String userJSON = Serializator.serialize(user);
        Intent mainActivityIntent = new Intent(context, MainActivity.class);
        mainActivityIntent.putExtra(KEY_USER, userJSON);
        return mainActivityIntent;
    }

    public static User fetchUser(Activity activity) {
        String userJSON = activity.getIntent().getStringExtra(KEY_USER);
        return Serializator.deserialize(userJSON, User.class);
    }

    public static Intent startProjectActivity(Context context, User user, ProjectModel project) {
        String userJSON = Serializator.serialize(user);
        String projectJSON = Serializator.serialize(project);
        Intent projectActivityIntent = new Intent(context, ProjectInfoActivity.class);
        projectActivityIntent.putExtra(KEY_USER, userJSON);
        projectActivityIntent.putExtra(KEY_PROJECT, projectJSON);
        return projectActivityIntent;
    }


    public static ProjectModel fetchProject(Activity activity) {
        String projectJSON = activity.getIntent().getStringExtra(KEY_PROJECT);
        return Serializator.deserialize(projectJSON, ProjectModel.class);
    }

    public static Intent startFeedbackActivity(Context context, ProjectModel project) {
        String projectJSON = Serializator.serialize(project);
        Intent feedbackActivityIntent = new Intent(context, FeedbackActivity.class);
        feedbackActivityIntent.putExtra(KEY_PROJECT, projectJSON);
        return feedbackActivityIntent;
    }
}
