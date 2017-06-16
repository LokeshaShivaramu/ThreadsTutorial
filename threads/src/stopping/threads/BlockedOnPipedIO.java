package stopping.threads;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class BlockedOnPipedIO 
{
	public static void main(String[] args)
			throws IOException, InterruptedException 
	{
		PipedInputStream in =
				new PipedInputStream(new PipedOutputStream());
		Thread t = new BlockedOnIO(in);
		t.start();
		Thread.sleep(5000);
		t.interrupt();
	}
}
