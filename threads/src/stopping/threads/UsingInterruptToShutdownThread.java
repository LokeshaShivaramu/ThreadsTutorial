package stopping.threads;

public class UsingInterruptToShutdownThread extends Thread 
{
	public void run()
	{
		while (!Thread.currentThread().isInterrupted()) 
		{
			System.out.println("Running. ");
			System.out.flush();
			try 
			{
				Thread.sleep(1000);
			} catch (InterruptedException ex) 
			{
				System.out.println("Thread interrupted");
				Thread.currentThread().interrupt(); // very important
				//Thread.currentThread().interrupt() to immediately interrupted the thread again. 
				/*
				 * When the exception is thrown, the interrupted flag is cleared, 
				 * so if you have nested loops, it will cause trouble in the outer loops.
				 */
			}
		}
		System.out.println("Shutting down thread");
	}
	
	
	public static void main(String[] args)
			throws InterruptedException 
	{
		Thread t = new UsingInterruptToShutdownThread();
		t.start();
		Thread.sleep(2000);  //main thread sleeps for 5 seconds
		t.interrupt();
	}
}