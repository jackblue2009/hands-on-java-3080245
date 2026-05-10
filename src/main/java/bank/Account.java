package bank;

import bank.exceptions.AmountException;

public class Account {
    private int id;
    private String type;
    private double balance;

    public Account(int i, String t, double d) {
      this.setId(i);
      this.setType(t);
      this.setBalance(d);
    }

    public int getId() {
      return this.id;
    }

    public String getType() {
      return this.type;
    }

    public double getBalance() {
      return this.balance;
    }

    public void setId(int i) {
      this.id = i;
    }

    public void setType(String t) {
      this.type = t;
    }

    public void setBalance(double d) {
      this.balance = d;
    }

    public void deposit(double amount) throws AmountException {
      if (amount <= 0) {
        throw new AmountException("The minimum deposit is 1.");
      } else {
        double newBalance = this.getBalance() + amount;
        this.setBalance(newBalance);
        DataSource.updateAccountBalance(this.getId(), newBalance);
        System.out.println("Your deposit has been successfully added to your account!");
      }
    }

    public void withdraw(double amount) throws AmountException {
      if (amount <= 0) {
        throw new AmountException("The minimum withdraw is 1.");
      } else {
        if (amount > this.getBalance()) {
          throw new AmountException("Insufficient funds available.");
        } else {
          double newBalance = this.getBalance() - amount;
          this.setBalance(newBalance);
          DataSource.updateAccountBalance(this.getId(), newBalance);
          System.out.println("Your withdrawal has been successfully made from your account!");
        }
      }
    }
}