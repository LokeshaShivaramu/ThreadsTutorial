package intr.producer.consumr;

import java.util.concurrent.BlockingQueue;

public interface IProgramStateIntr {
	
	public BlockingQueue<Integer> getDataSource();
	
	public Object getLockObject();
	
	public boolean canProduce();
	
	public void stopProduce();
	
}
