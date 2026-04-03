package edu.sec.banking;

import edu.sec.banking.model.Account;
import edu.sec.banking.service.AccountService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {



        IO.println(
                String.format(
                        "Press 1 to create Account, 2 to update account and 3 to delete account." +
                                " Press 0 to see the options again.")
        );

        boolean appRunning = true;

        AccountService accountService = new AccountService();

        // Dummy data
        Account dummyAccount = accountService.createAccount(
                new Account("Freddie", "CHECKING", 450000000000.00)
        );

        while (appRunning){

            Scanner input = new Scanner(System.in);
            IO.println(String.format("=================================="));
            IO.println(String.format("     SLogics Banking System! "));
            IO.println(String.format("=================================="));

            IO.println(String.format("================ Menu ================"));
            IO.println(String.format("Press 1 to create an account"));
            IO.println(String.format("Press 2 to update an account"));
            IO.println(String.format("Press 3 to delete an account"));
            IO.println(String.format("Press 4 to exit"));

            int option = input.nextInt();
            input.nextLine(); //

            switch (option){
                case 1:
                    IO.println(String.format("Please enter account name"));
                    String accountName = input.nextLine().trim();

                    boolean isAccountOption = false;
                    String accountType = "CHECKING";
                    while (!isAccountOption) {
                        IO.println(String.format("Please Choose account type: 1 for checking, 2 for saving."));
                        int accType = input.nextInt();

                        if (accType == 1) {
                            accountType = "CHECKING";
                            isAccountOption = true;
                            break;
                        } else if (accType == 2) {
                            accountType = "SAVING";
                            isAccountOption = true;
                            break;
                        } else {
                            IO.println(String.format("Wrong option"));
                            isAccountOption = false;
                        }
                    }

                    IO.println(String.format("Please enter account balance"));
                    Double accountBalance = input.nextDouble();

                    // creating account
                    Account newAccount = accountService.createAccount(
                            new Account(accountName, accountType, accountBalance)
                    );
                    // Print account details
                    IO.println(String.format("New account Details:"));
                    IO.println(String.format(
                            "AccountId: %d\nAccount Name: %s\nAccount Type: %s\nAccount Balance: $%.3f",
                            newAccount.getId(), newAccount.getAccHolderName(),
                            newAccount.getAccountType(), newAccount.getAccountBalance()
                            )
                    );

                    break;
                case 2:
                    IO.println(String.format("Coming up"));
                case 4:
                    appRunning = false;
                    IO.println(String.format("Closing system. Come back later!"));
                    return;

                default:
                    IO.println(String.format("Invalid option."));
            }
        }


    }
}
