package stopping.threads;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;

/*
 * If you want to stop a thread waiting on a socket, 
 * you will have to unfortunately close the socket underneath the thread. 
 * Fortunately, the interrupt() method is not final, so you can override it to close the socket. 
 * Inside the catch clause of java.io.IOException you can then check whether the thread has been interrupted or not:
 */
public class BlockedOnIO extends Thread 
{
	private final InputStream in;
	
	public BlockedOnIO(InputStream in) 
	{
		this.in = in;
	}
	
	public void interrupt() 
	{
		super.interrupt();
		System.out.println("In interrupt()");
		try 
		{
			in.close();
		} catch (IOException e) 
		{
			System.out.println("In catch IOException");
		} // quietly close
	}
	
	public void run() 
	{
		try 
		{
			System.out.println("Reading from input stream");
			in.read();      
			System.out.println("Finished reading");
		} 
		catch (InterruptedIOException e) 
		{
			Thread.currentThread().interrupt();
			System.out.println("Interrupted via InterruptedIOException");
		}
		catch (IOException e) 
		{
			if (!isInterrupted())
			{
				e.printStackTrace();
			}
			else
			{
				System.out.println("Interrupted because of IOException as socket stream is closed in interrupt() method");
			}
		}
		System.out.println("Shutting down thread");
	}
}
