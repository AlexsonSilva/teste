package com.company;

public class Customer {
    String firstName,lastName, email, pin;
    String account;

    CurrentAccount currentAccount;
    SavingsAccount savingsAccount;



    public Customer(String _firstName, String _lastName, String _email) {
        firstName = _firstName;
        lastName = _lastName;
        email = _email;

        currentAccount = new CurrentAccount(account);
        savingsAccount = new SavingsAccount(account);
    }
    public String getAccount() {
        return account;
    }

    public void setAccount(String _account) {
         account = _account;
    }

    public CurrentAccount getCurrentAccount() {
        return currentAccount;
    }

    public void setCurrentAccount(CurrentAccount _currentAccount) {
        currentAccount = _currentAccount;
    }

    public SavingsAccount getSavingsAccount() {
        return savingsAccount;
    }

    public void setSavingsAccount(SavingsAccount _savingsAccount) {
        savingsAccount= _savingsAccount;
    }
}
