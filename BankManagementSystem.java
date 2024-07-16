import java.util.HashMap;
import java.util.Scanner;

class Customer {
    private String name;
    private String accountNumber;
    private double balance;
    private String phoneNumber;
    private String address;
    private double loanAmount;

    public Customer(String name, String accountNumber, double initialDeposit, String phoneNumber, String address) {
        if (!accountNumber.matches("ACC\\d{8}")) {
            throw new IllegalArgumentException("Account number must be in the format ACC12345678 with 8 digits.");
        }
        if (phoneNumber.length() != 10) {
            throw new IllegalArgumentException("Phone number must be exactly 10 digits.");
        }
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = initialDeposit;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.loanAmount = 0.0;
    }

    public String getName() {
        return name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposit successful! New balance: " + balance);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful! New balance: " + balance);
        } else {
            System.out.println("Insufficient funds or invalid amount.");
        }
    }

    public void takeLoan(double amount) {
        if (amount > 0) {
            loanAmount += amount;
            balance += amount;
            System.out.println("Loan successful! New balance: " + balance + " | Total loan amount: " + loanAmount);
        } else {
            System.out.println("Loan amount must be positive.");
        }
    }

    public void repayLoan(double amount) {
        if (amount > 0 && amount <= balance && amount <= loanAmount) {
            loanAmount -= amount;
            balance -= amount;
            System.out.println("Loan repayment successful! Remaining loan amount: " + loanAmount + " | New balance: " + balance);
        } else {
            System.out.println("Invalid repayment amount.");
        }
    }
}

class Bank {
    private HashMap<String, Customer> customers;

    public Bank() {
        customers = new HashMap<>();
    }

    public void addCustomer(Customer customer) {
        if (!customers.containsKey(customer.getAccountNumber())) {
            customers.put(customer.getAccountNumber(), customer);
            System.out.println("Customer added successfully.");
        } else {
            System.out.println("Account number already exists.");
        }
    }

    public Customer getCustomer(String accountNumber) {
        return customers.get(accountNumber);
    }

    public void deposit(String accountNumber, double amount) {
        Customer customer = getCustomer(accountNumber);
        if (customer != null) {
            customer.deposit(amount);
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void withdraw(String accountNumber, double amount) {
        Customer customer = getCustomer(accountNumber);
        if (customer != null) {
            customer.withdraw(amount);
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void displayBalance(String accountNumber) {
        Customer customer = getCustomer(accountNumber);
        if (customer != null) {
            System.out.println("Account balance: " + customer.getBalance());
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void takeLoan(String accountNumber, double amount) {
        Customer customer = getCustomer(accountNumber);
        if (customer != null) {
            customer.takeLoan(amount);
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void repayLoan(String accountNumber, double amount) {
        Customer customer = getCustomer(accountNumber);
        if (customer != null) {
            customer.repayLoan(amount);
        } else {
            System.out.println("Customer not found.");
        }
    }
}

public class BankManagementSystem {
    public static void main(String[] args) {
        Bank bank = new Bank();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Welcome to the Bank Management System");
            System.out.println("1. Add Customer");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Check Balance");
            System.out.println("5. Take Loan");
            System.out.println("6. Repay Loan");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter name: ");
                    String name = scanner.next();
                    System.out.print("Enter account number: ");
                    String accountNumber = scanner.next();
                    System.out.print("Enter initial deposit: ");
                    double initialDeposit = scanner.nextDouble();
                    System.out.print("Enter phone number: ");
                    String phoneNumber = scanner.next();
                    System.out.print("Enter address: ");
                    String address = scanner.next();
                    bank.addCustomer(new Customer(name, accountNumber, initialDeposit, phoneNumber, address));
                    break;
                case 2:
                    System.out.print("Enter account number: ");
                    String depositAccount = scanner.next();
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    bank.deposit(depositAccount, depositAmount);
                    break;
                case 3:
                    System.out.print("Enter account number: ");
                    String withdrawAccount = scanner.next();
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    bank.withdraw(withdrawAccount, withdrawAmount);
                    break;
                case 4:
                    System.out.print("Enter account number: ");
                    String balanceAccount = scanner.next();
                    bank.displayBalance(balanceAccount);
                    break;
                case 5:
                    System.out.print("Enter account number: ");
                    String loanAccount = scanner.next();
                    System.out.print("Enter loan amount: ");
                    double loanAmount = scanner.nextDouble();
                    bank.takeLoan(loanAccount, loanAmount);
                    break;
                case 6:
                    System.out.print("Enter account number: ");
                    String repayAccount = scanner.next();
                    System.out.print("Enter repayment amount: ");
                    double repaymentAmount = scanner.nextDouble();
                    bank.repayLoan(repayAccount, repaymentAmount);
                    break;
                case 7:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }
}