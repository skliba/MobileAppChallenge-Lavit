package mac2015.lavit.domain.models.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by noxqs on 24.09.15..
 */
public class ZokaResponse {

    @SerializedName("project")
    private ProjectRespponseZoka projectResponseZoka;
    @SerializedName("feedback")
    private List<FeedbackResponse> feedbackResponseZoka;

    public ProjectRespponseZoka getProjectResponseZoka() {
        return projectResponseZoka;
    }

    public void setProjectResponseZoka(ProjectRespponseZoka projectResponseZoka) {
        this.projectResponseZoka = projectResponseZoka;
    }

    public List<FeedbackResponse> getFeedbackResponseZoka() {
        return feedbackResponseZoka;
    }

    public void setFeedbackResponseZoka(List<FeedbackResponse> feedbackResponseZoka) {
        this.feedbackResponseZoka = feedbackResponseZoka;
    }
}
