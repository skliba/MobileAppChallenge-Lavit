package mac2015.lavit.domain.models.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by noxqs on 24.09.15..
 */
public class ProjectRespponseZoka {

    @SerializedName("id")
    private int projectId;

    @SerializedName("name")
    private String projectName;

    @SerializedName("description")
    private String projectDescription;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
}