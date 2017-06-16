package wait.notify;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Processor {

	private Object lock = new Object();
	private List<Integer> dataStore = new ArrayList<Integer>();
	private static final int SIZE = 10;
	
	public void produce() throws InterruptedException 
	{
		Random random = new Random();
		synchronized (lock) {
			
			System.out.println("in Produce thread running...");
			
			while (true)
			{
				if (dataStore.size() == SIZE)
				{
					lock.wait();
				}
				
				dataStore.add(random.nextInt(100));
				lock.notify();
			}
		}
	}
	
	public void consume() throws InterruptedException
	{
		synchronized (lock) {
			
			System.out.println("in Consume thread running...");
			while (true)
			{
				if (dataStore.size() == 0)
				{
					lock.wait();
				}
				
				Integer value = dataStore.remove(0);
				System.out.println(" Value from Queue : " + value);
				lock.notify();
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		Processor processor = new Processor();
		
		Thread t1 = new Thread(new Runnable(){
			public void run() {
				try {
					processor.produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		});
		
		Thread t2 = new Thread(new Runnable(){
			public void run() {
				try {
					processor.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
		});
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
	}
}
