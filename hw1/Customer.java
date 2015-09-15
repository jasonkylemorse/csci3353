/* file: Customer.java
 * author: Jason Morse
 * date: September 11, 2015
 * class: Object-Oriented Design HW1
 */

package hw.hw1;

public class Customer {

    private static final int CUST_ARRIVAL_PCT = 19;         // Chance a customer is ready to checkout
    private int arrivalTime, serviceTime;                   // Arrival time and service time for the customer

    public Customer() { }                                   // Constructors

    public Customer(int arrivalTime, int serviceTime) {
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime + 5;
    }

    public boolean readyToCheckout() {                      // 19% chance a customer is ready to checkout each second
        int n = (int) (Math.random() * 100);
        return n < CUST_ARRIVAL_PCT;
    }

    public int howManyItems() {                             // Number of items a customer purchases (random betw. 1-10)
        int n = (int) (Math.random() * 10);
        return n + 1;
    }

    public int getServiceTime() {                           // Returns how long it takes for a customer to check out
        return serviceTime;
    }

    public void setServiceTime(int serviceTime) {           // Set how long it takes for a customer to check out
        this.serviceTime = serviceTime;
    }

    public int getArrivalTime() {                           // Return the time when a customer arrived
        return arrivalTime;
    }

}