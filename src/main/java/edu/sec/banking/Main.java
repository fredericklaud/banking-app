package edu.sec.banking;

import edu.sec.banking.model.Account;
import edu.sec.banking.service.AccountService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {



        IO.println("Press 1 to create Account, 2 to update account and 3 to delete account please." +
                                " Press 0 to see the options again."
        );

        boolean appRunning = true;

        AccountService accountService = new AccountService();

        // Dummy data
        Account dummyAccount = accountService.createAccount("Freddie", "CHECKING", 450000000000.00);

        while (appRunning){

            Scanner input = new Scanner(System.in);
            IO.println("==================================");
            IO.println("     SLogics Banking System! ");
            IO.println("==================================");

            IO.println("================ Menu ================");
            IO.println("Press 0 to show all accounts.");
            IO.println("Press 1 to create an account");
            IO.println("Press 2 to update an account");
            IO.println("Press 3 to delete an account");
            IO.println("Press 4 to exit");

            int option = input.nextInt();
            input.nextLine(); //

            switch (option){
                case 0:
                    accountService.displayAllAccounts();
                    break;
                case 1:
                    createNewAccount(input, accountService);
                    break;
                case 2:
                    updateExistingAccount(input, accountService);
                    break;
                case 3:
                    deleteExistingAccount(input, accountService);
                    break;
                case 4:
                    appRunning = false;
                    IO.println("Closing system. Come back later!");
                    return;

                default:
                    IO.println("Invalid option.");
            }
        }


    }

    private static void deleteExistingAccount(Scanner input, AccountService accountService) {
        IO.println("Please enter account id to remove please");
        Long id = input.nextLong();
        Account accountToDelete = accountService.getAccountById(id);
        accountService.deleteAccount(accountToDelete);
    }

    public static void createNewAccount(Scanner input, AccountService accountService){
        IO.println("Please enter account name please");
        String accountName = input.nextLine().trim();

        String accountType = "CHECKING";
        accountType = chooseAccountType(input, accountType);

        IO.println("Please enter account balance please");
        Double accountBalance = input.nextDouble();

        // creating account
        Account newAccount = accountService.createAccount(accountName, accountType, accountBalance);
        // Print account details
        IO.println("New account Details:");
        IO.println(String.format(
                        "AccountId: %d\nAccount Name: %s\nAccount Type: %s\nAccount Balance: $%.3f",
                        newAccount.getId(), newAccount.getAccHolderName(),
                        newAccount.getAccountType(), newAccount.getAccountBalance()
                )
        );
    }

    public static String chooseAccountType(Scanner input){
        return chooseAccountType(input, "CHECKING");
    }

    public static String chooseAccountType(Scanner input, String accountType){
        boolean isAccountOption = false;
//        String accountType = "CHECKING";
        while (!isAccountOption) {
            IO.println("Please Choose account type: 1 for checking, 2 for saving please.");
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
                IO.println("Wrong option");
                isAccountOption = false;
            }
        }
        return accountType;
    }

    public static void updateExistingAccount(Scanner input, AccountService accountService){
        boolean updateStatus = true;

        // Retrieve account to update
        IO.println("Enter Account id");

        if(!input.hasNextLong()) {
            IO.println("Invalid Input.");

            input.nextLine(); //Clears the invalid input
            return;
        }

        Long id = input.nextLong();
        input.nextLine();

        Account accountToUpdate = accountService.getAccountById(id);
        IO.println(String.format("Account number entered is %d", id));

        if (accountToUpdate == null){
            IO.println("Account to update is null");
            return;
        }
        else {
            IO.println("Account to update: " + accountToUpdate + " " +id);
            accountService.displayAccount(accountToUpdate);
        }

        while (updateStatus) {
            IO.println("=============== Update options ===============");
            IO.println("Press 1 to enter new Account name");
            IO.println("Press 2 to enter new Account type");
            IO.println("Press 3 to enter new Account Balance");
            IO.println("Press 4 to finish update.");

            int updateOption = input.nextInt();
            input.nextLine();

            switch (updateOption) {
                case 1:
                    IO.println("Please enter account name");
                    String accountName = input.nextLine().trim();
                    accountToUpdate.setAccHolderName(accountName);
                    IO.println("Account name changed to " + accountName);
                    break;
                case 2:
                    String accountType = chooseAccountType(input);
                    accountToUpdate.setAccountType(accountType);
                    IO.println("Account type changed to " + accountType);
                    break;
                case 3:
                    IO.println("Please enter account balance");
                    Double balance = input.nextDouble();
                    accountToUpdate.setAccountBalance(balance);
                    IO.println("Account balance changed to " + balance);
                    break;
                case 4:
                    accountService.updateAccount(accountToUpdate);
                    IO.println("Account Updated");
                    accountService.displayAccount(accountToUpdate);
                    return;
                default:
                    IO.println("Invalid option.");
            }
        }
    }
}
