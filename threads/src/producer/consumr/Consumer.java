package producer.consumr;


public class Consumer implements Runnable
{
	private IProgramState pState;
	
	private int consumerCount = 0;

	public Consumer(IProgramState progState)
	{
		pState = progState;
	}
	
	
	@Override
	public void run() 
	{
		while (pState.canConsume() || !pState.getDataSource().isEmpty())
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
					} catch (InterruptedException e) 
					{
						e.printStackTrace();
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
