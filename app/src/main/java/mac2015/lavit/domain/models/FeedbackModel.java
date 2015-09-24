package mac2015.lavit.domain.models;

import java.io.File;

/**
 * Created by noxqs on 23.09.15..
 */
public class FeedbackModel {

    //TODO ovdje isto fali @SerializedName
    private String user;
    private double rating;

    //TODO atributi ako je dopusten takav nacin feedbacka, ovisi o postu
    private String comment;
    private File image;
    private double latitude;
    private double longitude;
    private ProjectModel project;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public File getImage() {
        return image;
    }

    public void setImage(File image) {
        this.image = image;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public ProjectModel getProject() {
        return project;
    }

    public void setProject(ProjectModel project) {
        this.project = project;
    }
}
