/* file: BetterDiningHallSimulation.java
 * author: Jason Morse
 * date: September 11, 2015
 * class: Object-Oriented Design HW1
 */

package hw.hw1;

import java.util.*;

public class BetterDiningHallSimulation {
    private static final int SIMULATION_TIME = 10000;  // A simulation is for 10,000 seconds.
    private static List<CashRegister> registers = new LinkedList<>();

    public static void main(String[] args) {

        // First, initialize the cash register arrays.
        for (int r=0; r<=1; r++) {
            CashRegister new_reg = new CashRegister();
            registers.add(new_reg);
        }

        // Then perform the simulation for the specified number of seconds.
        for (int t=0; t<SIMULATION_TIME; t++) {
            Customer customer = new Customer();

            if (customer.readyToCheckout()) {
                // The new customer goes into the smaller line.
                int chosenRegister = (registers.get(0).getLineSize() < registers.get(1).getLineSize()) ? 0 : 1;
                registers.get(chosenRegister).addCustomer(t, customer.howManyItems());
            }

            for (int r=0; r<=1; r++)
                registers.get(r).elapseOneSecond(t);  // Simulate each register for one second.
        }

        // Print out the statistics.
        for (int r=0; r<=1; r++) {
            System.out.println("Register " + r);
            registers.get(r).printStats();
        }
    }
}