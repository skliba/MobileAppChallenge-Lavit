package mac2015.lavit.domain.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import mac2015.lavit.domain.models.response.FeedbackResponse;

/**
 * Created by noxqs on 23.09.15..
 */
public class ProjectModel {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("active")
    private boolean active;
    private String coverPicture = "http://blog.caranddriver.com/wp-content/uploads/2009/08/2009-Lamborghini-Gallardo-LP60-4.jpg";
    @SerializedName("description")
    private String description;
    @SerializedName("tags")
    List<Tag> tags;
    private String ownerName;
    private String category;
    private int feedBackType;
    @SerializedName("allowedTypes")
    private transient int[] feedbackTypes;
    private transient FeedbackResponse feedbackModel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoverPicture() {
        return coverPicture;
    }

    public void setCoverPicture(String coverPicture) {
        this.coverPicture = coverPicture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getFeedBackType() {
        return feedBackType;
    }

    public void setFeedBackType(int feedBackType) {
        this.feedBackType = feedBackType;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public class Tag {
        private long id;
        private String name;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public int[] getFeedbackTypes() {
        return feedbackTypes;
    }

    public void setFeedbackTypes(int[] feedbackTypes) {
        this.feedbackTypes = feedbackTypes;
    }

    public FeedbackResponse getFeedbackModel() {
        return feedbackModel;
    }

    public void setFeedbackModel(FeedbackResponse feedbackModel) {
        this.feedbackModel = feedbackModel;
    }
}
