package com.lks.threads;

import java.util.Scanner;



class RunThread extends Thread {
	
	private static boolean running = true;
	
//	BlockingQueue sd;
	
	private int i = 0;
	
	RunThread(int startVal)
	{
		i = startVal;
	}
	
	@Override
	public void run() {
		
		while(running)
		{
			i = i + 2;
		}
	}
	
	public void shutDown()
	{
		running = false;
	}
	
	public int getI()
	{
		return i;
	}
	
	public boolean getRunning()
	{
		return running;
	}
}

public class ThreadStopWithVar {
	
	public static void main(String[] args) {
		
		RunThread evenThread = new RunThread(0);
		RunThread oddThread = new RunThread(1);
		
		evenThread.start();
		oddThread.start();
		
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		
		evenThread.shutDown();
		
//		System.out.println("Even " + evenThread.getI());
//		System.out.println("Odd " + oddThread.getI());
//	
//		System.out.println("Even " + evenThread.getRunning());
//		System.out.println("Odd " +  oddThread.getRunning());
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally{
			scanner.close();
		}
		
		oddThread.shutDown();
	}
}
