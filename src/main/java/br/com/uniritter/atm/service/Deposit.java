package br.com.uniritter.atm.service;
// Deposit.java
// Represents a deposit ATM transaction

import br.com.uniritter.atm.repository.BankDatabaseInMemory;
import br.com.uniritter.atm.ui.DepositSlot;
import br.com.uniritter.atm.ui.Keypad;
import br.com.uniritter.atm.ui.Screen;
import br.com.uniritter.atm.ui.Transaction;

public class Deposit extends Transaction {
    private double amount; // amount to deposit
    private Keypad keypad; // reference to keypad
    private DepositSlot depositSlot; // reference to deposit slot
    private final static int CANCELED = 0; // constant for cancel option

    // Deposit constructor
    public Deposit(int userAccountNumber, Screen screen,
                   BankDatabaseInMemory atmBankDatabase, Keypad atmKeypad,
                   DepositSlot atmDepositSlot) {
        // initialize superclass variables
        super(userAccountNumber, screen, atmBankDatabase);

        // initialize references to keypad and deposit slot
        keypad = atmKeypad;
        depositSlot = atmDepositSlot;
    } // end Deposit constructor

    // perform transaction
    @Override
    public void execute() {
        BankDatabaseInMemory bankDatabase = getBankDatabase(); // get reference

        amount = promptForDepositAmount(); // get deposit amount from user

        Screen screen = getScreen();

        // check whether user entered a deposit amount or canceled
        if (amount != CANCELED) {
            // request deposit envelope containing specified amount
            screen.displayMessage(
                    "\nPlease insert a deposit envelope containing ");
            screen.displayDollarAmount(amount);
            screen.displayMessageLine(".");

            // receive deposit envelope
            boolean envelopeReceived = depositSlot.isEnvelopeReceived();

            // check whether deposit envelope was received
            if (envelopeReceived) {
                screen.displayMessageLine("\nYour envelope has been " +
                        "received.\nNOTE: The money just deposited will not " +
                        "be available until we verify the amount of any " +
                        "enclosed cash and your checks clear.");

                // credit account to reflect the deposit
                bankDatabase.credit(getAccountNumber(), amount);
            } // end if
            else // deposit envelope not received
            {
                screen.displayMessageLine("\nYou did not insert an " +
                        "envelope, so the ATM has canceled your transaction.");
            } // end else
        } // end if
        else // user canceled instead of entering amount
        {
            screen.displayMessageLine("\nCanceling transaction...");
        } // end else
    } // end method execute

    // prompt user to enter a deposit amount in cents
    protected double promptForDepositAmount() {
        Screen screen = getScreen();

        // display the prompt
        screen.displayMessage("\nPlease enter a deposit amount in " +
                "CENTS (or 0 to cancel): ");
        int input = keypad.getInput(); // receive input of deposit amount

        // check whether the user canceled or entered a valid amount
        if (input == CANCELED)
            return CANCELED;
        else {
            return (double) input / 100; // return dollar amount
        } // end else
    } // end method promptForDepositAmount
} // end class Deposit


/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/