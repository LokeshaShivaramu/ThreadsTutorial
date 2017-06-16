package producer.consumr;

import java.util.concurrent.BlockingQueue;

public interface IProgramState {
	
	public BlockingQueue<Integer> getDataSource();
	
	public Object getLockObject();
	
	public boolean canProduce();
	
	public boolean canConsume();
	
	public void stopConsume();
	
	public void stopProduce();
	
}
