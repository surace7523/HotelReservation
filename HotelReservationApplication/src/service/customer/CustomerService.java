
package service.customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import model.customer.Customer;

public class CustomerService {
    private static CustomerService instance = new CustomerService();
    private Map<String, Customer> customers = new HashMap();

    private CustomerService() {
    }

    public static CustomerService getInstance() {
        return instance;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(firstName, lastName, email);
        this.customers.put(email, customer);
    }

    public Customer getCustomer(String customerEmail) {
        return (Customer)this.customers.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {
        return this.customers.values();
    }
}
