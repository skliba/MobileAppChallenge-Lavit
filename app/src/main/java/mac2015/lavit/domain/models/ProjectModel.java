package mac2015.lavit.domain.models;

/**
 * Created by noxqs on 23.09.15..
 */
public class ProjectModel {

    //TODO @SerializedName fali ovdje, nismo dodali jer nema api-ja jos uvijek pa ne znamo imena

    private String name;
    private String coverPicture;
    private String description;
    private String ownerName;
    private String category;
    private String tags;
    private int feedBackType;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

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
}
