package atm1;


public class User {

	private String userId; 
	private String pin; 
	private double balance; 
	public User(String userId, String pin, double balance) 
	{ 
		this.userId = userId; 
		this.pin = pin; 
		this.balance = balance; 
	} 
	
	public String getUserId() 
	{ 
		return userId; 
	} 
	
	public String getPin() 
	{ 
		return pin; 
	} 
	
	public double getBalance() 
	{ 
		return balance; 
	}
	
	public void deposit(double amount) 
	{ 
		balance += amount; 
	} 
	
	public boolean withdraw(double amount) 
	{ 
		if (amount > balance) 
		{ 
			return false; 
		} 
			balance -= amount; 
		return true; 
	} 
	
	public boolean transfer(User recipient, double amount) 
	{ 
		if (amount > balance) 
		{ 
			return false; 
		} 
			balance -= amount; 
		recipient.deposit(amount); 
		return true; 
	}
	
}

