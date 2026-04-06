package edu.sec.banking.service;

import edu.sec.banking.model.Account;

import java.util.ArrayList;

public class AccountService {

    public ArrayList<Account> accounts = new ArrayList<>();

    public Account createAccount(String accountName, String accountType, Double accountBalance){
        Account newAccount = new Account();

        newAccount.setAccountType(accountType);
        newAccount.setAccHolderName(accountName);
        newAccount.setAccountBalance(accountBalance);

        accounts.add(newAccount);
        IO.println("Account created successfully!");

        return newAccount;
    }

    public boolean updateAccount(Account account){
        boolean updateSucces = false;
        if (account != null){
            for (Account acc: accounts) {
                if (account.getId().equals(acc.getId())){
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
        if (id == null) return null;

        for (Account acc : accounts) {
            if (id.equals(acc.getId())) {
                IO.println("Obtained account by id");
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
        if (account == null) return;
        for (Account acc: accounts){
            if (account.getId().equals(acc.getId())){
                deleteSuccess = accounts.remove(acc);
                break;
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
