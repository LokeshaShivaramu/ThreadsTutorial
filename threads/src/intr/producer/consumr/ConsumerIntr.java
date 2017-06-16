package intr.producer.consumr;


public class ConsumerIntr implements Runnable
{
	private IProgramStateIntr pState;
	
	private int consumerCount = 0;

	public ConsumerIntr(IProgramStateIntr progState)
	{
		pState = progState;
	}
	
	
	@Override
	public void run() 
	{
		while (true)
		{
			Integer data = null;
			synchronized (pState.getLockObject()) 
			{
				data = pState.getDataSource().poll();
				if (data == null)
				{
					try 
					{
						if (pState.canProduce())
						{
							pState.getLockObject().wait();
						}
						else
						{
							break;	
						}
					} catch (InterruptedException e) 
					{
						System.out.println("Consumer Interrupted");
						Thread.currentThread().interrupt(); // very important
						break;
					}
				}
				else
				{
					consumerCount++;
					System.out.println("Data From Queue : " + data + "  Queue Size : " + (pState.getDataSource().size() + 1));
					pState.getLockObject().notifyAll();
				}
			}
		}
		
	
		synchronized (pState.getLockObject()) 
		{
			System.out.println("Consumer Queue Size : " + (pState.getDataSource().size()));
		}
	}
	
	public int getConsumedCount()
	{
		return consumerCount;
	}
}
