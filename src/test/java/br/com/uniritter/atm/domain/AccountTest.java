package br.com.uniritter.atm.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {

    Account account;

    @Before
    public void setUp(){
        account = new Account(1, 2, 100, 120);
    }

    @Test
    public void validatePinOk(){
        boolean pin = account.validatePIN(2);

        Assert.assertTrue(pin);
    }
    @Test
    public void validatePinNotOk(){
        boolean pin = account.validatePIN(1);

        Assert.assertFalse(pin);
    }

    @Test
    public void shouldReturnAvailableBalance(){
        double availableBalance = account.getAvailableBalance();

        Assert.assertEquals(100, availableBalance, 0);
    }

    @Test
    public void shouldReturnTotalBalance(){
        double availableBalance = account.getTotalBalance();

        Assert.assertEquals(120, availableBalance, 0);
    }

    @Test
    public void shouldCreditAccount(){
        double totalBalance = account.getTotalBalance();
        account.credit(10.0);
        double newTotalBalance = account.getTotalBalance();

        Assert.assertEquals(totalBalance + 10, newTotalBalance, 0);
    }

    @Test
    public void shouldDebitAccount(){
        double availableBalance = account.getAvailableBalance();
        account.debit(10.0);
        double newAvailableBalance = account.getAvailableBalance();

        Assert.assertEquals(availableBalance -10, newAvailableBalance, 0);
    }



}
