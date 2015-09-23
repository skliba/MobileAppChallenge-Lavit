package mac2015.lavit.domain.manager;

import android.content.Context;

/**
 * Created by dmacan on 22.9.2015..
 */
public class ValidationManager {

    private Context context; // TODO later use strings.xml for retVal

    public ValidationManager(Context context) {
        this.context = context;
    }

    public String validateEmail(String email) {
        boolean emailEmpty = isEmpty(email);
        boolean emailFormatValid = isEmailFormatted(email);
        String retVal;
        if (emailEmpty) {
            retVal = "Email is empty";
        } else if (!emailFormatValid) {
            retVal = "Email format is invalid";
        } else {
            retVal = null;
        }
        return retVal;
    }

    public String validatePassword(String password) {
        boolean passwordEmpty = isEmpty(password);
        boolean passwordShort = !isPasswordLongEnough(password);
        String retVal;
        if (passwordEmpty) {
            retVal = "Password is empty";
        } else if (passwordShort) {
            retVal = "Password is too short";
        } else {
            retVal = null;
        }
        return retVal;
    }


    public String validatePasswordConfirm(String password, String passwordConfirm) {
        boolean passwordsMatch = isPasswordMatching(password, passwordConfirm);
        String retVal;
        if (!passwordsMatch) {
            retVal = "Passwords do not match";
        } else {
            retVal = null;
        }
        return retVal;
    }

    public String validateFirstName(String firstName) {
        boolean firstNameEmpty = isEmpty(firstName);
        String retVal;
        if (firstNameEmpty) {
            retVal = "First name is empty";
        } else {
            retVal = null;
        }
        return retVal;
    }

    public String validateLastName(String lastName) {
        boolean lastNameEmpty = isEmpty(lastName);
        String retVal;
        if (lastNameEmpty) {
            retVal = "Last name is empty";
        } else {
            retVal = null;
        }
        return retVal;
    }

    public boolean isEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }

    public boolean isEmailFormatted(String email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public boolean isPasswordLongEnough(String password) {
        return password.length() >= 6;
    }

    public boolean isPasswordMatching(String password, String passwordConfirm) {
        return password.equals(passwordConfirm);
    }
}
