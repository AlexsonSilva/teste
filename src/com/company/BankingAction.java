package com.company;

import java.util.Locale;

public class BankingAction {
        public static int countFName = 0;
        public static int countLName = 0;

        public static void AddCustomer(Customer customer) {
            char[] letters = {
                    'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
                    'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
            };

            for (int i = 0; i < letters.length; i++) {
                if (Character.toLowerCase(customer.firstName.charAt(0)) == letters[i]) {
                    countFName = (i + 1);
                }
                if (Character.toLowerCase(customer.lastName.charAt(0)) == letters[i]) {
                    countLName = (i + 1);
                }

            }

            System.out.printf("Creating customer: %s %s\n", customer.firstName, customer.lastName);
            System.out.printf("The account number is: %c%c-%s-%s-%s, pin code is %s%s\n", customer.firstName.charAt(0), customer.lastName.charAt(0), customer.firstName.length() + customer.lastName.length(), countFName, countLName, countFName, countLName);
            FileManager.AddCustomerToFile(customer);

            String savingsFile,  currentFile;
            savingsFile =  GenerateAccountName( customer) + ".savings.txt";
            currentFile = GenerateAccountName( customer) + ".current.txt";

            FileManager.CreateCustomerAccount(savingsFile);
            FileManager.CreateCustomerAccount(currentFile);
            //Then you write the customer details out to the file

        }

        public static String GenerateAccountName (Customer customer) {
            /// up to you to write the overall logic
            String fullName = customer.firstName + customer.lastName;
            return customer.firstName.charAt(0) + "" + customer.lastName.charAt(0) + "-" + (customer.firstName.length() + customer.lastName.length()) + "-" + countFName + "-" + countLName;
            //return customer.firstName + customer.lastName + "-" + fullName.length();


        }


//            System.out.println("Creating " + customer.firstName);
//            System.out.println("Creating " + customer.lastName);
            // you can add in all the other parameters for a customer


            //Then you write the customer details out to the file

//        }

        public static void DeleteCustomer() {

        }

        public static void CreateTransaction(BankingTransaction transaction) {
            if (transaction.customer != null)
            {
                System.out.println(" value: "+ transaction.amount +" account: "+ transaction.customer.getAccount() +" type: "+ transaction.typeTransaction+" ");

                FileManager.AddTransactionToFile(transaction);
            }
        }

        public static void ListCustomers() {
            FileManager.ListCustomers();

        }

        public static void listCustomerBalance(){
            FileManager.ListCustomersBalance();
        }

        public static void ListTransactionHistory(String account, int type){
            FileManager.transactionList(account, type);

        }

}
