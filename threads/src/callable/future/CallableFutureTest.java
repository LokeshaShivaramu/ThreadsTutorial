package callable.future;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableFutureTest 
{

	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		final Random random = new Random();
		
		ExecutorService exeService = Executors.newFixedThreadPool(5);
		
		Callable<Integer> sleepCallable = new Callable<Integer>()
		{
			
			@Override
			public Integer call() throws Exception {
				
				int randomValue = random.nextInt(3000);
				
				Thread.sleep(randomValue);
				
				return randomValue;
			}
		};
		
		List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();
		
		for (int i = 0; i < 5; i++)
		{
			Future<Integer> future = exeService.submit(sleepCallable);
			futureList.add(future);
		}

		exeService.shutdown();
		
		for (Future<Integer> callFuture : futureList)
		{
			System.out.println("Return from callable : " + callFuture.get());
		}
		
	}
}
