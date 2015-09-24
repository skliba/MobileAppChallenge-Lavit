package mac2015.lavit.domain.models.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by noxqs on 24.09.15..
 */
public class ZokaResponse {

    @SerializedName("project")
    private ProjectRespponseZoka projectResponseZoka;
    @SerializedName("feedback")
    private FeedbackResponse feedbackResponseZoka;

    public ProjectRespponseZoka getProjectResponseZoka() {
        return projectResponseZoka;
    }

    public void setProjectResponseZoka(ProjectRespponseZoka projectResponseZoka) {
        this.projectResponseZoka = projectResponseZoka;
    }

    public FeedbackResponse getFeedbackResponseZoka() {
        return feedbackResponseZoka;
    }

    public void setFeedbackResponseZoka(FeedbackResponse feedbackResponseZoka) {
        this.feedbackResponseZoka = feedbackResponseZoka;
    }
}
