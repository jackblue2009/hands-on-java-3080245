package main;

import java.util.Scanner;

import javax.security.auth.login.LoginException;

import bank.Account;
import bank.Authenticator;
import bank.Customer;
import bank.DataSource;
import bank.exceptions.AmountException;

public class Main {
    private Scanner scanner;
    public static void main(String[] args) {
      System.out.println("Welcome to Spencer Int'l Bank!");
      Main mainInstance = new Main();
      mainInstance.scanner = new Scanner(System.in);

      Customer customer = mainInstance.authenticateUser();

      if (customer != null) {
        Account account = DataSource.getAccountById(customer.getAccountId());
        mainInstance.showMenu(customer, account);
      }

      mainInstance.scanner.close();
    }

    private Customer authenticateUser() {
      System.out.print("Enter your username: ");
      String username = scanner.next();
      System.out.print("\nEnter your password: ");
      String password = scanner.next();

      Customer customer = null;

      try {
        customer = Authenticator.login(username, password);
      } catch (LoginException e) {
        System.out.println("\nCould not login. " + e.getMessage());
      }

      return customer;
    }

    private void showMenu(Customer customer, Account account) {
      int selection = 0;

      while (selection != 4 && customer.isAuthenticated()) {
        System.out.println("============================================");
        System.out.println("Select one of the options below:");
        System.out.println("1 | Deposit");
        System.out.println("2 | Withdraw");
        System.out.println("3 | Check Balance");
        System.out.println("4 | Exit");
        System.out.println("============================================");

        selection = scanner.nextInt();
        double amount = 0;

        switch (selection) {
          case 1:
            System.out.println("Specifiy how much to deposit into your account:");
            amount = scanner.nextDouble();
            try {
              account.deposit(amount);
            } catch (AmountException e) {
              System.out.println(e.getMessage());
            }
            break;
          case 2:
            System.out.println("Specifiy how much to withdraw from your account:");
            amount = scanner.nextDouble();
            try {
              account.withdraw(amount);
            } catch (AmountException e) {
              System.out.println(e.getMessage());
            }
            break;
          case 3:
            System.out.println("Current balance: " + account.getBalance());
            break;
          case 4:
            Authenticator.logout(customer);
            System.out.println("Thank you for using Spencer Int'l Bank platform!");
            break;
          default:
            System.out.println("Invalid option! Choose one the options above.");
            break;
        }
      }
    }
}