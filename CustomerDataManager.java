package server.controller;

import java.util.ArrayList;
import java.util.List;

import model.Customer;

/**
 * This class manages customer data
 * 
 * Table 1: Description of methods for the class that manages the customer data
 * Method Description
 * Method 1 This method creates a list of samples of customer data.
 * Method 2 This method searches a customer based on the customer’s name from a list of customers.
 * Method 3 This method searches a customer based on the customer’s id from a list of customers.
 * Method 4 This method will return a list of customers.
 * Constructor 1 This constructor will call Method 1.
 * 
 * Additional methods might be needed to support the required functionality.
 * 
 * @author emalianakasmuri
 *
 */
public class CustomerDataManager {

	private List<Customer> customers;

	/**
	 * Constructor 1: This constructor will call Method 1 to create a list of samples of customer data.
	 */
	public CustomerDataManager() {
		customers = new ArrayList<>();
		createSampleCustomers();
	}

	/**
	 * Method 1: This method creates a list of samples of customer data.
	 * The list will contain 10 customers.
	 * This is a private method.
	 */
	private void createSampleCustomers() {
		// Create sample customers
		for (int i = 1; i <= 10; i++) {
			Customer customer = new Customer();
			customer.setCustomerId(i);
			customer.setName("Customer " + i);
			customers.add(customer);
		}
	}

	/**
	 * Method 2: This method searches a customer based on the customer's name from a list of customers.
	 * This method will receive a parameter that represents a customer's name.
	 * The customer's name can be either a full or partial name.
	 * The method will return a Customer object if the name exists, otherwise, it will return null.
	 * This is a public method.
	 *
	 * @param name the customer's name (full or partial) to search for
	 * @return the found Customer object or null if not found
	 */
	public Customer searchCustomerByName(String name) {
		// Search for customer by name
		for (Customer customer : customers) {
			if (customer.getName().toLowerCase().contains(name.toLowerCase())) {
				return customer;
			}
		}
		return null; // Customer not found
	}

	/**
	 * Method 3: This method searches a customer based on the customer's id from a list of customers.
	 * This method will receive a parameter that represents a customer's id.
	 * The method will return a Customer object if the id exists, otherwise, it will return null.
	 * This is a public method.
	 *
	 * @param id the customer's id to search for
	 * @return the found Customer object or null if not found
	 */
	public Customer searchCustomerById(int id) {
		// Search for customer by id
		for (Customer customer : customers) {
			if (customer.getCustomerId() == id) {
				return customer;
			}
		}
		return null; // Customer not found
	}

	/**
	 * Method 4: This method will return a list of customers.
	 * This is a public method.
	 *
	 * @return the list of customers
	 */
	public List<Customer> getCustomers() {
		return customers;
	}

	// Additional methods can be added here to support other functionalities
}
