package com.company;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class FileManager {
    private static Formatter output;
    private static Scanner input;

    private static String CustomerFile = "customers.txt";


    public static void AddCustomerToFile(Customer customer) {
        OpenFileToWrite(CustomerFile);
        output.format("%s %s %s %c%c-%s-%s-%s %s%s\n", customer.firstName, customer.lastName, customer.email, customer.firstName.charAt(0), customer.lastName.charAt(0), customer.firstName.length() + customer.lastName.length(), BankingAction.countFName, BankingAction.countLName, BankingAction.countFName, BankingAction.countLName);
//        output.format("%s %s %s %s %n",customer.firstName, customer.lastName, customer.email, customer.pin);
        CloseFile();

    }

    public static void AddTransactionToFile(BankingTransaction _bankingTransaction){
        if (_bankingTransaction.typeAccount == AppConstants.CURRENT_ACCOUNT){
            OpenFileToWrite(_bankingTransaction.customer.getAccount() + "-current.txt");
            if(!_bankingTransaction.customer.getCurrentAccount().setBalance(_bankingTransaction.typeTransaction, _bankingTransaction.amount)){
                System.out.println(" The current balance must have plus then 0.  ");
                CloseFile();
                return;
            }

            output.format("%s %s %f %f %n", date(), _bankingTransaction.typeTransaction, _bankingTransaction.amount, _bankingTransaction.customer.getCurrentAccount().getBalance());

        }
        else{
            OpenFileToWrite(_bankingTransaction.customer.getAccount() + "-savings.txt");
            //ts.customer.getSa().setBalance(ts.typeTransaction, ts.amount);

            if(!_bankingTransaction.customer.getCurrentAccount().setBalance(_bankingTransaction.typeTransaction, _bankingTransaction.amount)){
                System.out.println(" The savings balance must have plus then 0.  ");
                CloseFile();
                return;
            }

            output.format("%s %s %f %f %n", date(), _bankingTransaction.typeTransaction, _bankingTransaction.amount, _bankingTransaction.customer.getSavingsAccount().getBalance());

        }

        CloseFile();

    }

    public static Customer getCustomer(String firstName, String lastName, String account, String pin)
    {
        OpenFileToRead();

        try {
            while (input.hasNext())
            {
                //System.out.printf("%s %s %s %d %d%n",input.next(),input.next(),input.nextInt(),input.nextInt());
                String _firstName, _lastName, _email, _account, _pin;
                _firstName = input.next();
                _lastName = input.next();
                _email = input.next();
                _account = input.next();
                _pin = input.next();

                if(
                        firstName.equals(_firstName) &&
                                lastName.equals(_lastName) &&
                                account.equals(_account) &&
                                pin.equals(_pin)
                )
                {

                    return new Customer(_firstName, _lastName, _email);
                }

            }
        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating");

            System.exit(1);
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating");

            System.exit(1);

        }

        CloseFile();

        return null;
    }

    public static Customer getCustomer(String account)
    {
        OpenFileToRead();

        try {
            while (input.hasNext())
            {
                //System.out.printf("%s %s %s %d %d%n",input.next(),input.next(),input.nextInt(),input.nextInt());
                String _firstName, _lastName, _email, _account;
                _firstName = input.next();
                _lastName = input.next();
                _email = input.next();
                _account = input.next();

                if( account.equals(_account))
                {
                    return new Customer(_firstName, _lastName, _email);
                }
                else
                {
                    return null;
                }

            }
        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating");

            System.exit(1);
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating");

            System.exit(1);

        }

        CloseFile();

        return null;
    }

    public static void CreateCustomerAccount(String customerAccountName) {
        OpenFileToWrite(customerAccountName);

//        output.format("testing customer account %n");
        CloseFile();
    }

    public static void ListCustomers() {

        OpenFileToRead();

        try {
            while (input.hasNext()) {
                System.out.printf("%s %s %n", input.next(), input.next());
            }
//        } catch (NoSuchElementException elementException) {
//            System.err.println("File improperly formed. Terminating");
//            System.exit(1);
//        } catch (IllegalStateException stateException) {
//            System.err.println("Error reading from file. Terminating");
//            System.exit(1);
//
//        }

            CloseFile();
        }
        finally {

        }

    }

    public static void ListCustomersBalance() {

        OpenFileToRead();

        try {
            while (input.hasNext()) {
                String firstName, lastName, email, account, pin;
                firstName = input.next();
                lastName = input.next();
                email = input.next();
                account = input.next();
                pin = input.next();

                double currentAccount = total(account, AppConstants.CURRENT_ACCOUNT);
                double savingAccount = total(account, AppConstants.SAVING_ACCOUNT);

                System.out.printf("%s %s %s %s current: %f savings: %f %n", firstName, lastName, email, account, currentAccount, savingAccount);
            }
//        } catch (NoSuchElementException elementException) {
//            System.err.println("File improperly formed. Terminating");
//            System.exit(1);
//        } catch (IllegalStateException stateException) {
//            System.err.println("Error reading from file. Terminating");
//            System.exit(1);
//
//        }

            CloseFile();
        }
        finally {

        }
    }

    private static boolean hasFile(String name)
    {
        try {
            input = new Scanner(Paths.get(name));
            return true;
        } catch (IOException ioException) {
            System.err.println("Error opening "+ name +".");
            return false;
        }
    }


    public static double total(String account, int _type)
    {

        String filename;
        double total = 0;

        if(_type == AppConstants.SAVING_ACCOUNT)
            filename = account + "-savings.txt";
        else
            filename = account + "-current.txt";

        if(hasFile(filename))
            OpenFileToRead(filename);
        else
            return total;


        try {
            while (input.hasNext())
            {
                //output.format("%s %s %f %f %n", date(), ts.typeTransaction, ts.amount, ts.customer.getCa().getBalance());

                String date, transaction;
                double amount, balance;
                date = input.next();
                transaction = input.next();
                amount = input.nextDouble();
                balance = input.nextDouble();

                total += amount;

                //System.out.printf("total: %s %s %f %f %n", date ,  transaction, amount, balance);

            }
        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating");

            System.exit(1);
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating");

            System.exit(1);

        }

        CloseFile();

        return total;
    }

    public static void transactionList(String account, int _type){
        String filename;

        if(_type == AppConstants.SAVING_ACCOUNT)
            filename = account + "-savings.txt";
        else
            filename = account + "-current.txt";

        if(hasFile(filename))
            OpenFileToRead(filename);


        try {
            while (input.hasNext())
            {
                //output.format("%s %s %f %f %n", date(), ts.typeTransaction, ts.amount, ts.customer.getCa().getBalance());

                String date, transaction;
                double amount, balance;
                date = input.next();
                transaction = input.next();
                amount = input.nextDouble();
                balance = input.nextDouble();

                System.out.printf("%s %s %f %f %n", date , (transaction.equals("L"))?"Lodge":"Withdraw", amount, balance);

            }
        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating");

            System.exit(1);
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating");

            System.exit(1);

        }

        CloseFile();


    }


    private static void OpenFileToWrite(String fileToOpen) {

        try {
            FileWriter f = new FileWriter(fileToOpen, true);
            output = new Formatter(f);
        } catch (SecurityException securityException) {
            System.out.println("Write permission denied");
            System.exit(1);
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("Error opening file, Terminating");
            System.exit(1);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }

    private static void CloseFile() {

        if (output != null) {
            output.close();
        }


    }

    public static void OpenFileToRead() {

        try {
            input = new Scanner(Paths.get(CustomerFile));
        } catch (IOException ioException) {
            System.err.println("Error opening file. Terminating");
            System.exit(1);
        }

    }

    private static void OpenFileToRead(String file) {

        try {
            input = new Scanner(Paths.get(file));
        } catch (IOException ioException) {
            System.err.println("Error opening "+ file +". Terminating");
            System.exit(1);
        }

    }

    public static String date()
    {
        //SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy-MM-dd 'at' HH:mm:ss z");
        SimpleDateFormat formatter= new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        //System.out.println(formatter.format(date));
        return formatter.format(date);
    }


    public static boolean hasCustomer(String firstName, String lastName, String accountNumber, String pin){
        OpenFileToRead();

        try {
            while (input.hasNext())
            {
                //System.out.printf("%s %s %s %d %d%n",input.next(),input.next(),input.nextInt(),input.nextInt());
                String firstName_, lastName_, email_, accountNumber_, pin_;
                firstName_ = input.next();
                lastName_ = input.next();
                email_ = input.next();
                accountNumber_ = input.next();
                pin_ = input.next();
                //System.out.println("'" + fn + "' '" +  ln + "' '" + ac + "' '" +  pi + "' ");
                if(
                        firstName.equals(firstName_) &&
                                lastName.equals(lastName_) &&
                                accountNumber.equals(accountNumber_) &&
                                pin.equals(pin_)
                )
                {

                    return true;
                }

            }
        } catch (NoSuchElementException elementException) {
            System.err.println("File improperly formed. Terminating");

            System.exit(1);
        } catch (IllegalStateException stateException) {
            System.err.println("Error reading from file. Terminating");

            System.exit(1);

        }

        CloseFile();

        return false;
    }


}

