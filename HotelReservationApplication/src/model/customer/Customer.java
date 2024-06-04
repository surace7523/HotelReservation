
package model.customer;

import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;
    private static final String EMAIL_REGEX = "^(.+)@(.+).(.+)$";
    private static final Pattern pattern = Pattern.compile("^(.+)@(.+).(.+)$");

    public Customer(String firstName, String lastName, String email) {
        if (!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Invalid email address");
        } else {
            this.firstName = firstName;
            this.lastName = lastName;
            this.email = email;
        }
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public String toString() {
        return "Customer{firstName='" + this.firstName + "', lastName='" + this.lastName + "', email='" + this.email + "'}";
    }
}
