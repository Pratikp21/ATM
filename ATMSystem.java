package atm1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATMSystem {
	private static Map<String, User> users;

	public static void main(String[] args) {
		users = new HashMap<>(); // Create some sample users
		users.put("user1", new User("user1", "1234", 1000.0));
		users.put("user2", new User("user2", "5678", 2000.0));
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter user id: ");
		String userId = scanner.nextLine();
		System.out.print("Enter user pin: ");
		String pin = scanner.nextLine();
		User user = authenticateUser(userId, pin);
		if (user == null) {
			System.out.println("Authentication failed. Exiting.");
			return;
		}
		System.out.println("Authentication successful. Welcome, " + user.getUserId() + "!");
		displayMenu();
		int choice;
		do {
			System.out.print("Enter your choice: ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				showTransactionHistory(user);
				break;
			case 2:
				performWithdrawal(user, scanner);
				break;
			case 3:
				performDeposit(user, scanner);
				break;
			case 4:
				performTransfer(user, scanner);
				break;
			case 5:
				System.out.println("Thank you for using our ATM. Goodbye!");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 5);
	}

	private static User authenticateUser(String userId, String pin) {
		if (users.containsKey(userId)) {
			User user = users.get(userId);
			if (user.getPin().equals(pin)) {
				return user;
			}
		}
		return null;
	}

	private static void displayMenu() {
		System.out.println("ATM Menu:");
		System.out.println("1. View Transaction History");
		System.out.println("2. Withdraw");
		System.out.println("3. Deposit");
		System.out.println("4. Transfer");
		System.out.println("5. Quit");
	}

	private static void showTransactionHistory(User user) {
		System.out.println("Transaction History for " + user.getUserId() + ":"); // Implement your logic to display the
																					// transaction history for the user
	}

	private static void performWithdrawal(User user, Scanner scanner) {
		System.out.print("Enter amount to withdraw: ");
		double amount = scanner.nextDouble();
		if (user.withdraw(amount)) {
			System.out.println("Withdrawal successful. Remaining balance: " + user.getBalance());
		} else {
			System.out.println("Insufficient balance. Withdrawal failed.");
		}
	}

	private static void performDeposit(User user, Scanner scanner) {
		System.out.print("Enter amount to deposit: ");
		double amount = scanner.nextDouble();
		user.deposit(amount);
		System.out.println("Deposit successful. Updated balance: " + user.getBalance());
	}

	private static void performTransfer(User user, Scanner scanner) {
		System.out.print("Enter recipient's user id: ");
		String recipientId = scanner.next();
		if (users.containsKey(recipientId)) {
			User recipient = users.get(recipientId);
			System.out.print("Enter amount to transfer: ");
			double amount = scanner.nextDouble();
			if (user.transfer(recipient, amount)) {
				System.out.println("Transfer successful. Remaining balance: " + user.getBalance());
			} else {
				System.out.println("Insufficient balance. Transfer failed.");
			}
		} else {
			System.out.println("Recipient user id not found. Transfer failed.");
		}
	}
}
