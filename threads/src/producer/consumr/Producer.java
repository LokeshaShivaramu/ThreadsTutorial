package producer.consumr;

import java.util.Random;

public class Producer  implements Runnable
{
	private Random random = new Random();
	
	private IProgramState pState;
	
	private int addedCount = 0;

	public Producer(IProgramState progState)
	{
		pState = progState;
	}
	
	
	@Override
	public void run() 
	{
		while (pState.canProduce())
		{
			Integer value = random.nextInt(100);
			synchronized (pState.getLockObject()) 
			{
				boolean result = false;
				while (!result)
				{
					result = pState.getDataSource().offer(value);
					if (result)
					{
						addedCount++;
						pState.getLockObject().notifyAll();
					}
					else
					{
						try 
						{
							pState.getLockObject().wait();
						} catch (InterruptedException e) 
						{
							Thread.currentThread().interrupt(); // very important
							break;
						}
					}
				}
			}
		}
		synchronized (pState.getLockObject()) 
		{
			pState.stopConsume();
			pState.getLockObject().notifyAll();
			System.out.println("Producer Queue Size : " + (pState.getDataSource().size()));
		}
	}
	
	
	public int getAddedCount()
	{
		return addedCount;
	}
	
}