package semaphore;

import java.util.concurrent.Semaphore;

public class Connection {

	private static int connectionsCount = 0;
	private Semaphore permitts = new Semaphore(10, true);
	
	private static final Connection CONNECTION = new Connection();
	
	private Connection()
	{
	}
	
	public void doWork() throws InterruptedException
	{
		permitts.acquire();
		
		try{
			createConnection();
		}
		finally
		{
			permitts.release();
		}
	}
	
	private void createConnection() throws InterruptedException
	{
		synchronized(this)
		{
			System.out.println("Creating connection : " + connectionsCount);
			connectionsCount++;
		}
		
		Thread.sleep(1000);
		
		synchronized (this) {
			connectionsCount--;
			System.out.println("Releasing Connection : " + connectionsCount);
		}
	}
	
	
	public static Connection getInstance()
	{
		return CONNECTION;
	}
}
