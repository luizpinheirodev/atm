package br.com.uniritter.atm.service;

import br.com.uniritter.atm.repository.BankDatabaseInMemory;
import br.com.uniritter.atm.ui.DepositSlot;
import br.com.uniritter.atm.ui.Keypad;
import br.com.uniritter.atm.ui.Screen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class DepositTest {

    Deposit deposit;
    Screen screen;
    BankDatabaseInMemory bankDatabaseInMemory;
    Keypad keypad;
    DepositSlot depositSlot;

    @Before
    public void setUp() {
        screen = new Screen();
        bankDatabaseInMemory = new BankDatabaseInMemory();
        keypad = Mockito.mock(Keypad.class);
        depositSlot = new DepositSlot();
        deposit = new Deposit(12345, screen,
                bankDatabaseInMemory, keypad,
                depositSlot);
    }

    @Test
    public void shouldReturnCanceled() {

        Mockito.when(keypad.getInput()).thenReturn(0);
        double v = deposit.promptForDepositAmount();

        Assert.assertEquals((double) 0, v, 0);
    }

    @Test
    public void shouldReturnValue() {

        Mockito.when(keypad.getInput()).thenReturn(100);
        double v = deposit.promptForDepositAmount();

        Assert.assertEquals((double) 1, v, 0);
    }
}
