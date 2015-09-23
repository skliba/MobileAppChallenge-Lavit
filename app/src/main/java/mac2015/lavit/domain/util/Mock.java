package mac2015.lavit.domain.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mac2015.lavit.domain.models.ProjectModel;
import mac2015.lavit.domain.models.User;

/**
 * Created by dmacan on 23.9.2015..
 */
public class Mock {

    public static User mockUser() {
        User user = new User();
        user.setEmail("john.doe@gmail.com");
        user.setPassword("xxx123");
        return user;
    }

    public static List<ProjectModel> mockProjects() {
        ProjectModel p1 = new ProjectModel();
        p1.setCategory("Cat1");
        p1.setDescription("Bacon ipsum dolor amet landjaeger turkey jerky, meatloaf capicola biltong venison. Shoulder ground round alcatra rump picanha pork chop ribeye. Bresaola swine jerky bacon kielbasa pork strip steak spare ribs, alcatra cow. Fatback boudin biltong spare ribs turkey bacon strip steak andouille frankfurter tongue ham chicken prosciutto.");
        p1.setName("Project1");
        p1.setOwnerName("Owner1");
        p1.setCoverPicture("http://investorplace.com/wp-content/uploads/2014/02/bacon.jpg");
        ProjectModel p2 = new ProjectModel();
        p2.setCategory("Cat2");
        p2.setDescription("Bacon ipsum dolor amet landjaeger turkey jerky, meatloaf capicola biltong venison. Shoulder ground round alcatra rump picanha pork chop ribeye. Bresaola swine jerky bacon kielbasa pork strip steak spare ribs, alcatra cow. Fatback boudin biltong spare ribs turkey bacon strip steak andouille frankfurter tongue ham chicken prosciutto.");
        p2.setName("Project2");
        p2.setOwnerName("Owner2");
        p2.setCoverPicture("http://sprdex.com/wp-content/uploads/2012/02/janjetina.jpg");
        ProjectModel p3 = new ProjectModel();
        p3.setCategory("Cat3");
        p3.setDescription("Bacon ipsum dolor amet landjaeger turkey jerky, meatloaf capicola biltong venison. Shoulder ground round alcatra rump picanha pork chop ribeye. Bresaola swine jerky bacon kielbasa pork strip steak spare ribs, alcatra cow. Fatback boudin biltong spare ribs turkey bacon strip steak andouille frankfurter tongue ham chicken prosciutto.");
        p3.setName("Project3");
        p3.setOwnerName("Owner3");
        p3.setCoverPicture("http://www.vecernji.hr/media/slika/89/442815.jpg");
        return new ArrayList<>(Arrays.asList(p1, p2, p3));
    }

}
