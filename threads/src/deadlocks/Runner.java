package deadlocks;

import java.util.Random;

public class Runner {

	private Account acc1 = new Account();
	private Account acc2 = new Account();
	
	public void firstThread()
	{
		Random random = new Random();
		
		for (int i = 0; i < 10000; i++)
		{
			Account.transfer(acc1, acc2, random.nextInt(100));
		}
	}
	
	public void secondThread()
	{
		Random random = new Random();
		
		for (int i = 0; i < 10000; i++)
		{
			Account.transfer(acc2, acc1, random.nextInt(100));
		}
	}

	public void finishedThread()
	{
		System.out.println("Account 1 Balance : " + acc1.getBalance());
		System.out.println("Account 2 Balance : " + acc2.getBalance());
		System.out.println("Total Balance : " + (acc1.getBalance() + acc2.getBalance()));
	}
}
