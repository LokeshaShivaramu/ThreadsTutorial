package intr.producer.consumr;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerIntruptController {

	
	public static void main(String[] args) throws InterruptedException {
		
		IProgramStateIntr progState = new ProgramState(5);
		
		ProducerIntr producer = new ProducerIntr(progState);
		ConsumerIntr consumer = new ConsumerIntr(progState);
		
		Thread prodThread = new Thread(producer);
		Thread consThread = new Thread(consumer);
		
		prodThread.start();
		consThread.start();
		
		Thread.sleep(10000);
		
		prodThread.interrupt();
		
		consThread.join();
		
		System.out.println("Added Count : " + producer.getAddedCount() + " : Consumed Count : " + consumer.getConsumedCount());
		
	}
}


class ProgramState implements IProgramStateIntr
{
	private Object lock = new Object();
	
	private BlockingQueue<Integer> dataSource;
	
	private boolean produceOn = true;
	
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
	public boolean canProduce() {
		return produceOn;
	}

	@Override
	public void stopProduce() {
		produceOn = false;
	}
	
}
