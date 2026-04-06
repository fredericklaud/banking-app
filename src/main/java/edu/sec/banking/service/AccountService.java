package edu.sec.banking.service;

import edu.sec.banking.model.Account;

import java.util.ArrayList;

public class AccountService {

    public ArrayList<Account> accounts = new ArrayList<>();

    public Account createAccount(Account account){
        Account newAccount = new Account();
        if (account != null){
            newAccount.setAccountType(account.getAccountType());
            newAccount.setAccHolderName(account.getAccHolderName());
            newAccount.setAccountBalance(account.getAccountBalance());

            accounts.add(newAccount);
            IO.println(String.format("Account created successfully!"));
        }
        else {
            System.err.println("account is null");
            return null;
        }
        return newAccount;
    }

    public boolean updateAccount(Account account){
        boolean updateSucces = false;
        if (account != null){
            for (Account acc: accounts) {
                if (acc.getId() == account.getId()){
                    acc.setAccountBalance(account.getAccountBalance());
                    acc.setAccHolderName(account.getAccHolderName());
                    acc.setAccountType(account.getAccountType());

                    // replacing old account details in the account array with new details
                    int accIndex = accounts.indexOf(acc);
                    accounts.set(accIndex, acc);
                    updateSucces = true;
                    break;
                }
            }
        }
        return updateSucces;
    }

    public Account getAccountById(Long id){
        Account account = null;
        for (Account acc : accounts) {
            if (id.equals(acc.getId())) {
                // retrieve account for array list of accounts
                return acc;
            }
        }
        return null;
    }

    public void displayAllAccounts(){
        IO.println("============== Accounts ==============");

        if (accounts != null){
            for (Account acc: accounts) {
                displayAccount(acc);
                IO.println("-----------------------------------------\n");
            }
        }

    }
    public void displayAccount(Account account){
        IO.println(String.format(
                        "AccountId: %d\nAccount Name: %s\nAccount Type: %s\nAccount Balance: $%.3f",
                account.getId(), account.getAccHolderName(),
                account.getAccountType(), account.getAccountBalance()
                )
        );
    }

    public void deleteAccount(Account account){
        boolean deleteSuccess = false;
        if (account != null){
            for (Account acc: accounts){
                if (acc.getId() == account.getId()){
                    deleteSuccess = accounts.remove(acc);
                    break;
                }
            }
        }
        if (deleteSuccess){
            IO.println(String.format("Account deleted successfully"));
        }
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }
}
