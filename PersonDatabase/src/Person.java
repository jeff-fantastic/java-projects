/*
    PERSON CLASS

    Stores all information about a person.
 */

import java.text.SimpleDateFormat;
import java.util.Date;

public class Person {
    // Declare constants
    final private String[] workGroupString = { "(0)Customer", "(1)Junior", "(2)Senior", "(3)Supervisor" };

    // Declare variables
    private String firstName;
    private String lastName;
    private int userGroup;
    private Date birthday;

    // Sets birthday.
    public void setBirthday(Date newBirthday) {
        this.birthday = newBirthday;
    }

    // Sets first and last name.
    public void setName(String fN, String lN) {
        this.firstName = fN;
        this.lastName = lN;
    }

    // Sets Usergroup.
    public void setUserGroup(int newValue) {
        this.userGroup = newValue;
    }

    // Returns string equivalent of birthday.
    public String getBirthdayString() {
        return new SimpleDateFormat("MM/dd/yyyy").format(birthday);
    }

    // Returns first name.
    public String getFirstName() {
        return this.firstName;
    }

    // Returns last name.
    public String getLastName() {
        return this.lastName;
    }

    // Returns workgroup.
    public int getUserGroup() {
        return this.userGroup;
    }

    // Returns string formatted workgroup.
    public String getWorkGroupFormatted() {
        return workGroupString[this.userGroup];
    }
}
