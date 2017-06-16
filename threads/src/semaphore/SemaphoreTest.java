package semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SemaphoreTest 
{

	public static void main(String[] args)
	{
		Connection connection = Connection.getInstance();
		ExecutorService executors = Executors.newFixedThreadPool(100);
		
		Runnable connectWork = new Runnable()
		{
			public void run() 
			{
				try {
					connection.doWork();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};
		};
		
		try{
			for (int i = 0; i < 1000; i++)
			{
				executors.execute(connectWork);
			}
		}
		finally
		{
			executors.shutdown();
		}
	}

}
