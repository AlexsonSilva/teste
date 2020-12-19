package com.company;

import java.util.Scanner;

public class MenuBuilder {
    private static int startMenu() {

        int selection;
        Scanner input = new Scanner(System.in);
        /***************************************************/

        String MENU = "\nPlease select one of the following options:\n" +
                "1: Bank Employee.\n" +
                "2: Customer.\n" +
                "3: Exit program.\n\n" +
                "> ";
        System.out.println(MENU);

        selection = input.nextInt();
        return selection;

    }

    private static int customerMenu() {

        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("-------------------------\n");
        System.out.println("1 - Login");
        System.out.println("2 - customerMenuOption 2");
        System.out.println("3 - customerMenuOption 3");
        System.out.println("4 - customerMenuQuit");

        selection = input.nextInt();
        return selection;

    }

    private static String  getCustomerPin() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
//        System.out.println("Enter PIN");
//
//        String pin = myObj.nextLine();  // Read user input
//        System.out.println("PIN is: " + pin);  // Output user input
//
//        return pin;
        System.out.println("Enter your First Name");
        String firstName = myObj.nextLine();
        System.out.println("First Name is: " + firstName);

        System.out.println("Enter your Last Name");
        String lastName = myObj.nextLine();
        System.out.println("Last Name is: " + lastName);

//        System.out.println("Enter your Account Number");
//        String accountNumber = myObj.nextLine();
//        System.out.println("Account Number is: " + accountNumber);

        return firstName + "" + lastName;
    }

    private static Customer AddCustomerForm() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter First Name");

        String firstName = myObj.nextLine();  // Read user input
        System.out.println("FirstName  is: " + firstName);  // Output user input

        System.out.println("Enter Last Name");
        String lastName = myObj.nextLine();  // Read user input
        System.out.println("LastName  is: " + lastName);  // Output user input

        System.out.println("Enter email");
        String email = myObj.nextLine();  // Read user input
        System.out.println("Email  is: " + email);  // Output user input

        // You might validate here.....

        Customer customer = new Customer(firstName, lastName, email);
        // And if happy /// create the customer

        return customer;
    }

    private static int transactionMenu() {

        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("-------------------------\n");
        System.out.println("1 - Lodge Money");
        System.out.println("2 - Withdraw Money");


        selection = input.nextInt();
        return selection;

    }

    private static int transactionLodgeMenu(){
        int selection;
        Scanner input = new Scanner(System.in);
        System.out.println("-------------------------\n");
        System.out.println("1 - Current Account");
        System.out.println("2 - Saving Account");

        selection = input.nextInt();
        return selection;

    }

    private static BankingTransaction CreateTransactionLodgeForm() {

//        System.out.println("Transaction lodge form goes here");

        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter the account:");
        String account = myObj.nextLine();

        System.out.println("Enter the amount:");
        double amount = myObj.nextDouble();

        System.out.println("Enter the type account (1 - Current / 2 - Savings)");
        int typeAccount = myObj.nextInt();

        Customer customer = FileManager.getCustomer(account);

        if (customer == null)
        {
            System.out.println("Customer account: "+ account +" doesn't exist");
            return null;
        }

        System.out.println("Transaction lodge - account: "+ account +" amount: "+ amount +" typeAccount: "+ typeAccount +" ");


        BankingTransactionLodge btl = new BankingTransactionLodge(customer, amount, typeAccount);


        return btl;

    }

    private static BankingTransaction CreateTransactionWithdrawForm() {

        //System.out.println("Transaction withdraw form goes here");
        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter the account:");
        String account = myObj.nextLine();

        System.out.println("Enter the amount:");

        double amount = myObj.nextDouble();

        System.out.println("Enter the type account (1 - Current / 2 - Savings)");
        int typeAccount = myObj.nextInt();

        System.out.println("Transaction lodge amount: "+ amount +" typeAccount: "+ typeAccount +" ");

        Customer customer = FileManager.getCustomer(account);

        if (customer == null)
        {
            System.out.println("Customer account: "+ account +" doesn't exist");
            return null;
        }

        BankingTransactionWithdraw btl = new BankingTransactionWithdraw(customer, amount, typeAccount);

        return btl;
    }

    private static  void DeleteCustomer() {


    }

    private static int createTransactionListForm(Customer customer) {

        System.out.println("Customer Transaction list");

        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter the type account (1 - Current / 2 - Savings)");
        int typeAccount = myObj.nextInt();

        //System.out.println("Transaction list amount: "+ c.getAccount() +" typeAccount: "+ typeAccount +" ");

        return typeAccount;

    }


    private static int bankingMenu() {

        int selection;
        Scanner input = new Scanner(System.in);

        System.out.println("-------------------------\n");
        System.out.println("1 - Add Customer");
        System.out.println("2 - Delete Customer");
        System.out.println("3 - Create Transaction");
        System.out.println("4 - List Customers");
        System.out.println("5 - Quit");

        selection = input.nextInt();
        return selection;

    }

    private static String  getBankEmployeePin() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter PIN");

        String pin = myObj.nextLine();  // Read user input
        System.out.println("PIN is: " + pin);  // Output user input

        return pin;

    }

    private static String[] getCustomerCredentials() {

        String customer[] = new String[4];
        Scanner myObj = new Scanner(System.in);

        System.out.println("Enter first name");
        customer[0] = myObj.nextLine(); //first name

        System.out.println("Enter last name");
        customer[1] = myObj.nextLine(); //last name

        System.out.println("Enter bank account");
        customer[2] = myObj.nextLine(); //account name

        System.out.println("Enter PIN");
        customer[3] = myObj.nextLine(); //pin name

        return customer;
    }


    public static void Run() {

        int userChoice = MenuBuilder.startMenu();

        switch (userChoice) {

            case 1:

                // Get the banking Pin from employee
                String pin = getBankEmployeePin();
                SecurityBanking sb = new SecurityBanking();
                boolean isAuthenticated = sb.login(pin);

                // add logic here if isAuthenticated

                if (isAuthenticated) {

                    int bankEmployeeAction = MenuBuilder.bankingMenu();


                    switch (bankEmployeeAction) {

                        case AppConstants.CUSTOMER_CREATE:
                            Customer newCustomer = AddCustomerForm();
                            BankingAction.AddCustomer(newCustomer);
                            break;
                        case 2: break;
                        case AppConstants.TRANSACTION_CREATE:
                            int transactionChoice = MenuBuilder.transactionMenu();

                            switch(transactionChoice) {
                                case AppConstants.TRANSACTION_LODGE:

                                    BankingTransaction btl = CreateTransactionLodgeForm();
                                    BankingAction.CreateTransaction(btl);
                                    bankingMenu();
                                    break;
                                case AppConstants.TRANSACTION_WITHDRAW:
                                    BankingTransaction btw = CreateTransactionWithdrawForm();
                                    BankingAction.CreateTransaction(btw);
                                    bankingMenu();
                                    break;
                            }
                            break;
                        case AppConstants.CUSTOMER_LIST:
                            System.out.println("Listing customers");
                            BankingAction.listCustomerBalance();
                            break; // AppConstants.CUSTOMER_LIST
                    }
                } else {

                    return;
                }
                break;
            case 2:
                 //Get the customer Pin from customer
//                 pin = getCustomerPin();
                 SecurityCustomer sc = new SecurityCustomer();
                 String[] credentials = getCustomerCredentials();
                  isAuthenticated = sc.getCredentials(credentials[0], credentials[1], credentials[2], credentials[3]);

                 //add logic here if isAuthenticated

                 if (isAuthenticated) {

                 int customerAction = MenuBuilder.customerMenu();

                userChoice = MenuBuilder.customerMenu();

                switch (customerAction){
                    case AppConstants.TRANSACTION_LODGE:
                        BankingTransaction btl = MenuBuilder.CreateTransactionLodgeForm();
                        BankingAction.CreateTransaction(btl);
                        break;
                    case AppConstants.TRANSACTION_WITHDRAW:
                        BankingTransaction btw = MenuBuilder.CreateTransactionWithdrawForm();
                        BankingAction.CreateTransaction(btw);
                        break;
                    case AppConstants.TRANSACTION_LIST:
//                        int type = createTransactionListForm();
//                        BankingAction.ListTransactionHistory();
                        break;
                    case AppConstants.QUIT:
                        startMenu();
                        break;
                    default:
                        System.out.println("Customer Action invalid!");

                }

                 } else {
                 System.out.println("error....");
                  return;
                  }


                break;
            case 3:
                System.out.println("Exiting program...");
                break;
            case 4:
                return;
        }

    }
}
