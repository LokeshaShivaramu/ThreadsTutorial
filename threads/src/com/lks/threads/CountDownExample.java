package com.lks.threads;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownExample {

	public static void main(String[] args) {
		
		CountDownLatch latch = new CountDownLatch(3);
		
		ExecutorService executor = Executors.newFixedThreadPool(3);
		
		try{
			for (int i = 0; i < 3; i++)
			{
				executor.submit(new ProcessorThread(i, latch));
			}
		}
		finally
		{
			try {
				latch.await();
				System.out.println("All threads over");
				executor.shutdown();
			} catch (InterruptedException e) {
				
			}
		}
	}
	
}

class ProcessorThread implements Runnable{
	
	private int Id;
	
	private CountDownLatch latch;
	
	public ProcessorThread(int threadId, CountDownLatch lat)
	{
		Id = threadId;
		latch = lat;
	}
	
	
	@Override
	public void run() {
		System.out.println("Started..." + Id);
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			System.out.println("End... " +  Id);
			latch.countDown();
		}
	}
	
}