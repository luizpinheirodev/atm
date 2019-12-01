package br.com.uniritter.atm.service;

import br.com.uniritter.atm.repository.BankDatabaseInMemory;
import br.com.uniritter.atm.ui.DepositSlot;
import br.com.uniritter.atm.ui.Keypad;
import br.com.uniritter.atm.ui.Screen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class BalanceInquiryTest {

    BalanceInquiry balanceInquiry;
    Screen screen;
    BankDatabaseInMemory bankDatabaseInMemory;

    @Before
    public void setUp() {
        screen = new Screen();
        bankDatabaseInMemory = new BankDatabaseInMemory();
        balanceInquiry = new BalanceInquiry(123456, screen,
                bankDatabaseInMemory);
    }

    @Test
    public void shouldReturnBalance(){
        double availableBalance = bankDatabaseInMemory.getAvailableBalance(12345);

        Assert.assertEquals(1000, availableBalance, 0);
    }

}
