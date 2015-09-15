/* file: CashRegister.java
 * author: Jason Morse
 * date: September 11, 2015
 * class: Object-Oriented Design HW1
 */

package hw.hw1;

import java.util.*;

public class CashRegister {

    private List<Customer> customers = new LinkedList<>();              // List of customers at the register
    private int customersServed = 0;                                    // Statistics about the two cash registers
    private int totalWaitTimes = 0;

    public int getLineSize() {                                          // Return number of customers in line
        return customers.size();
    }

    public void addCustomer(int arrivalTime, int serviceTime) {         // Add a customer to the line
        Customer new_cust = new Customer(arrivalTime, serviceTime);
        customers.add(new_cust);
    }

    public void elapseOneSecond(int currentTime) {                      // Elapse one second and recalculate totals
        // If the list is empty, there are no customers to process.
        if (customers.size() == 0)
            return;

        // Otherwise, the first customer in line gets processed.
        int timeLeft = customers.get(0).getServiceTime() - 1;
        if (timeLeft > 0) {
            customers.get(0).setServiceTime(timeLeft);
        }
        else { // We are done with this customer.
            // First update the register's statistics.
            customersServed++;
            totalWaitTimes += currentTime - customers.get(0).getArrivalTime();

            // Then remove the customer.
            customers.remove(0);
        }
    }

    public void printStats() {                                          // Print register statistics
        System.out.println("\tNumber of arrivals = " + customersServed);
        System.out.println("\tAverage wait time = " + totalWaitTimes / customersServed);
    }
}
