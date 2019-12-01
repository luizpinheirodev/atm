package br.com.uniritter.atm.repository;

import br.com.uniritter.atm.domain.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BankDatabaseInMemoryTest {

    BankDatabaseInMemory bankDatabaseInMemory;

    @Before
    public void setUp() {
        bankDatabaseInMemory = new BankDatabaseInMemory();
    }

    @Test
    public void getAccount() {
        Account account = bankDatabaseInMemory.getAccount(12345);

        Assert.assertEquals(1000, account.getAvailableBalance(), 0);
        Assert.assertEquals(1200, account.getTotalBalance(), 0);
        Assert.assertEquals(12345, account.getAccountNumber());
    }

    @Test
    public void shouldNotAuthenticate() {
        boolean auth = bankDatabaseInMemory.authenticateUser(12345, 12345);

        Assert.assertFalse(auth);
    }

    @Test
    public void shouldAuthenticate() {
        boolean auth = bankDatabaseInMemory.authenticateUser(12345, 54321);

        Assert.assertTrue(auth);
    }

    @Test
    public void getAvailableBalance(){
        double availableBalance = bankDatabaseInMemory.getAvailableBalance(12345);

        Assert.assertEquals(1000, availableBalance, 0);
    }

    @Test
    public void getTotalBalance(){
        double availableBalance = bankDatabaseInMemory.getTotalBalance(12345);

        Assert.assertEquals(1200, availableBalance, 0);
    }

    @Test
    public void credit(){
        bankDatabaseInMemory.credit(12345, 100);

        Assert.assertEquals(1200+100, bankDatabaseInMemory.getTotalBalance(12345), 0);
    }

    @Test
    public void debit(){
        bankDatabaseInMemory.debit(12345, 100);

        Assert.assertEquals(1000-100, bankDatabaseInMemory.getAvailableBalance(12345), 0);
    }

}
