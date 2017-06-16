package intr.producer.consumr;

import java.util.Random;

public class ProducerIntr  implements Runnable
{
	private Random random = new Random();
	
	private IProgramStateIntr pState;
	
	private int addedCount = 0;

	public ProducerIntr(IProgramStateIntr progState)
	{
		pState = progState;
	}
	
	
	@Override
	public void run() 
	{
		Integer value = random.nextInt(100);
		synchronized (pState.getLockObject()) 
		{
			boolean result = false;
			while (true)
			{
				result = pState.getDataSource().offer(value);
				if (result)
				{
					addedCount++;
					pState.getLockObject().notifyAll();
					value = random.nextInt(100);
				}
				else
				{
					try 
					{
						pState.getLockObject().wait();
					} catch (InterruptedException e) 
					{
						System.out.println("Producer Interrupted");
						Thread.currentThread().interrupt(); // very important
						break;
					}
//					finally{
//						pState.getLockObject().notifyAll();	
//					}
				}
			}
			pState.stopProduce();
			System.out.println("Producer Queue Size : " + (pState.getDataSource().size()));
		}
	}
	
	
	public int getAddedCount()
	{
		return addedCount;
	}
	
}