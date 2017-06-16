package deadlocks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
	
	private int balance = 10000;
	
	private static Lock lock = new ReentrantLock();
	
	public void deposit(int amount)
	{
		balance = balance + amount;
	}

	public void withDraw(int amount)
	{
		balance = balance - amount;
	}

	public int getBalance()
	{
		return balance;
	}
	
	//Adding synchronized at method level or creating a synchronized block solves the concurrency issues.
//	public static void transfer(Account acc1, Account acc2, int amount)
//	{
//		acc1.withDraw(amount);
//		acc2.deposit(amount);
//	}
	
	//Using a reentrant lock also solves concurrency issues
	public static void transfer(Account acc1, Account acc2, int amount)
	{
		lock.lock();
		try {
			acc1.withDraw(amount);
			acc2.deposit(amount);
		}
		finally{
			lock.unlock();
		}
	}
}
