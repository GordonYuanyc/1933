public class BankAccount {
    String name;
    private String password;
    private double balance;
    
    public BankAccount(String initName, String initPassword, double initBalance){
        this.name = initName;
        this.password = initPassword;
	    this.balance = initBalance;
    }


    public void withdraw(String enteredPassword, double amount){
        if (password.equals(enteredPassword) && balance >= amount){
            balance = balance - amount;
        }
    }
    
    public void deposit(String enteredPassword, double amount){
        if (password.equals(enteredPassword)){
            balance = balance + amount;
        }
    }

    public double getBalance(String enteredPassword){
        if (password.equals(enteredPassword)){
	    return balance;
	} else {
	    return -1;
	}
    }

    public boolean setPassword(String oldPassword, String newPassword){
	if (password.equals(oldPassword)){
	    password = newPassword;
	    return true;
	} else {
	    return false;
	}
    }
}
