package br.com.uniritter.atm.domain;

public interface BankDatabase {

    boolean authenticateUser(int userAccountNumber, int userPIN);
    double getAvailableBalance(int userAccountNumber);
    double getTotalBalance(int userAccountNumber);
    void credit(int userAccountNumber, double amount);
    void debit(int userAccountNumber, double amount);

}
