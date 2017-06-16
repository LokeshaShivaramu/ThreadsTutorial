package com.lks.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {
	
	Random random = new Random();
	
	private Object l1 = new Object();
	private Object l2 = new Object();
	
	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();

	public void stageOne()
	{
		synchronized(l1){
			try{
				Thread.sleep(1);
			}
			catch(InterruptedException intExp)
			{
				intExp.printStackTrace();
			}
			
			list1.add(random.nextInt(100));
		}
	}
	
	public void stageTwo()
	{
		synchronized(l2){
			try{
				Thread.sleep(1);
			}
			catch(InterruptedException intExp)
			{
				intExp.printStackTrace();
			}
			list2.add(random.nextInt(100));
		}
	}
	
	public void process()
	{
		for (int i = 0; i < 1000; i++)
		{
			stageOne();
			stageTwo();
		}
	}
	
	public void main() {
		
		System.out.println("Starting...");
		
		long start = System.currentTimeMillis();
		
		Thread runer1 = new Thread(new Runnable()
		{
			@Override
			public void run() {
				process();
			}
		});
		
		runer1.start();
		
		Thread runer2 = new Thread(new Runnable()
		{
			@Override
			public void run() {
				process();
			}
		});
		
		runer2.start();
		
		try {
			runer1.join();
			runer2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		System.out.println("Time Taken : " + (end - start));
		
		System.out.println("Size of List 1 : " + list1.size() + "   Size of List 2 : " + list2.size());
	}
}
