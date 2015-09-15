package hw.hw1;

import java.util.*;

public class DiningHallSimulation {
    private static final int SIMULATION_TIME = 10000;  // A simulation is for 10,000 seconds.
    private static final int CUST_ARRIVAL_PCT = 19;    // There is a 19% chance a customer arrives each second.

    // info about customers waiting at the two cash registers
    private static List<Integer>[] arrivalTimes = (List<Integer>[]) new List[2];
    private static List<Integer>[] serviceTimes = (List<Integer>[]) new List[2];

    // statistics about the two cash registers
    private static int[] customersServed = new int[2];
    private static int[] totalWaitTimes = new int[2];

    public static void main(String[] args) {
        // First, initialize the cash register arrays.
        for (int r=0; r<=1; r++) {
            arrivalTimes[r] = new LinkedList<Integer>();
            serviceTimes[r] = new LinkedList<Integer>();
            customersServed[r] = 0;
            totalWaitTimes[r]  = 0;
        }

        // Then perform the simulation for the specified number of seconds.
        for (int t=0; t<SIMULATION_TIME; t++) {
            if (aCustomerArrives()) {
                // The new customer goes into the smaller line.
                int chosenRegister = arrivalTimes[0].size() < arrivalTimes[1].size() ? 0 : 1;
                arrivalTimes[chosenRegister].add(t);
                serviceTimes[chosenRegister].add(howManyItems() + 5);
            }

            for (int r=0; r<=1; r++)
                elapseOneSecond(r, t);  // Simulate each register for one second.
        }

        // Print out the statistics.
        for (int r=0; r<=1; r++) {
            System.out.println("Register " + r);
            System.out.println("\tNumber of arrivals = " + customersServed[r]);
            System.out.println("\tAverage wait time = " + (totalWaitTimes[r] / customersServed[r]));
        }
    }

    private static boolean aCustomerArrives() {
        int n = (int) (Math.random() * 100);  // an integer between 0 and 99
        return n < CUST_ARRIVAL_PCT;
    }

    private static int howManyItems() {
        int n = (int) (Math.random() * 10);
        return n + 1;
    }

    private static void elapseOneSecond(int reg, int currentTime) {
        // If the list is empty, there are no customers to process.
        if (arrivalTimes[reg].size() == 0)
            return;

        // Otherwise, the first customer in line gets processed.
        int timeLeft = serviceTimes[reg].get(0) - 1;
        if (timeLeft > 0) {
            serviceTimes[reg].set(0, timeLeft);
        }
        else { // We are done with this customer.
            // First update the register's statistics.
            customersServed[reg]++;
            totalWaitTimes[reg] += currentTime - arrivalTimes[reg].get(0);

            // Then remove the customer.
            arrivalTimes[reg].remove(0);
            serviceTimes[reg].remove(0);
        }
    }
}