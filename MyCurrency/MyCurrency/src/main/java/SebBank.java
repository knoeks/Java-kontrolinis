import lt.itakademija.exam.*;

import java.util.ArrayList;

public class SebBank implements Bank {
  private SequenceGenerator customerSequenceGenerator;
  private SequenceGenerator AccountSequenceGenerator;
  private SequenceGenerator TransferSequenceGenerator;
  private ArrayList<Customer> customers;
  private CurrencyConverter converter;

  public SebBank(CurrencyConverter converter) {
    this.customerSequenceGenerator = new SequenceGenerator();
    this.AccountSequenceGenerator = new SequenceGenerator();
    TransferSequenceGenerator =new SequenceGenerator();
    customers = new ArrayList<>();
    this.converter = converter;
  }

  @Override
  public Customer createCustomer(PersonCode personCode, PersonName personName) {
    if (personCode == null || personName == null) {
      throw new NullPointerException("personCode personName cannot be null");
    }
    if (customerExists(personCode)) {
      throw new CustomerCreateException("Client with given code already exists");
    }
    Customer customer = new Customer(customerSequenceGenerator.getNext(), personCode, personName);
    customers.add(customer);
    return customer;
  }

  public Boolean customerExists(PersonCode personCode) {
    return customers.stream().anyMatch(item -> item.getPersonCode().equals(personCode));
  }

  @Override
  public Account createAccount(Customer customer, Currency currency) {
    if (customer == null || currency == null) {
      throw new NullPointerException("customer and currency cannot be null");
    }
    if (!customers.contains(customer)) {
      throw new AccountCreateException("Given customer doesnt have account in this bank.");
    }
    Account account = new Account(AccountSequenceGenerator.getNext(), customer, currency, new Money(0));
    customer.addAccount(account);
    return account;
  }

  @Override
  public Operation transferMoney(Account account, Account account1, Money money) {
    if (account == null || account1 == null) {
      throw new NullPointerException("One of the accounts is null.");
    }

    if (account.getBalance().isLessThan(money)) {
      throw new InsufficientFundsException("Account cannot transfer more money than they have.");
    }

    if (account.getCurrency().equals(account1.getCurrency())) {
      account.getBalance().substract(money);
      account1.getBalance().add(money);
    } else {
      System.out.println("from: " + account.getBalance() + " to: " + account1.getBalance() + " money: " + money);
      account.setBalance(account.getBalance().substract(money));
      account1.setBalance(account1.getBalance().add(converter.convert(account.getCurrency(), account1.getCurrency(), money)));

//      account.getBalance().substract(money);
//      account1.getBalance().add();
//      System.out.println("from: " + account.getBalance() + " to: " + account1.getBalance() + " money: " + money);

    }

    return new Operation(TransferSequenceGenerator.getNext(), account, account1, money);
  }

  @Override
  public Money getBalance(Currency currency) {
    return customers.stream().map(customer -> customer.getAccounts().stream().map(account -> account.convert(item.getCurrency(), account1.getCurrency(), money)));
  }
}
