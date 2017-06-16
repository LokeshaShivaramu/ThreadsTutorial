package producer.consumr;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerController {

	
	public static void main(String[] args) throws InterruptedException {
		
		IProgramState progState = new ProgramState(5);
		
		Producer producer = new Producer(progState);
		Consumer consumer = new Consumer(progState);
		
		Thread prodThread = new Thread(producer);
		Thread consThread = new Thread(consumer);
		
		prodThread.start();
		consThread.start();
		
		Thread.sleep(1000);
		
		progState.stopProduce();
		prodThread.interrupt();
		
		prodThread.join();
		
		consThread.join();

		System.out.println("Added Count : " + producer.getAddedCount() + " : Consumed Count : " + consumer.getConsumedCount());
		
	}
}


class ProgramState implements IProgramState
{
	private Object lock = new Object();
	
	private BlockingQueue<Integer> dataSource;
	
	private boolean continueProduce = true;
	
	private boolean continueConsume = true;
	
	ProgramState(int dataSrcCapacity)
	{
		dataSource = new ArrayBlockingQueue<Integer>(dataSrcCapacity);
	}
	
	@Override
	public BlockingQueue<Integer> getDataSource() {
		return dataSource;
	}

	@Override
	public Object getLockObject() {
		return lock;
	}
	
	@Override
	public boolean canProduce()
	{
		return continueProduce;
	}

	@Override
	public void stopConsume() {
		continueConsume = false;
	}

	@Override
	public boolean canConsume() {
		return continueConsume;
	}

	@Override
	public void stopProduce() {
		continueProduce = false;
	}
	
}
