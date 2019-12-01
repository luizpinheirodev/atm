package br.com.uniritter.atm.service;

import br.com.uniritter.atm.repository.BankDatabaseInMemory;
import br.com.uniritter.atm.ui.CashDispenser;
import br.com.uniritter.atm.ui.Keypad;
import br.com.uniritter.atm.ui.Screen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class WithdrawalTest {

    Withdrawal withdrawal;
    Screen screen;
    BankDatabaseInMemory bankDatabaseInMemory;
    Keypad keypad;
    CashDispenser cashDispenser;

    @Before
    public void setUp() {
        screen = new Screen();
        bankDatabaseInMemory = new BankDatabaseInMemory();
        keypad = Mockito.mock(Keypad.class);
        cashDispenser = new CashDispenser();

        withdrawal = new Withdrawal(12345, screen,
                bankDatabaseInMemory, keypad, cashDispenser);
    }

    @Test
    public void shouldWithdrawal100(){
        double availableBalance = bankDatabaseInMemory.getAvailableBalance(12345);
        Mockito.when(keypad.getInput()).thenReturn(4); // 100
        withdrawal.execute();

        double newAvailableBalance = bankDatabaseInMemory.getAvailableBalance(12345);

        Assert.assertEquals(availableBalance - 100, newAvailableBalance, 0);
    }

}
