package mac2015.lavit.domain.util;

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

}
