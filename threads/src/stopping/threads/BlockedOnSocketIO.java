package stopping.threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BlockedOnSocketIO {
	public static void main(String[] args)
			throws IOException, InterruptedException {
		ServerSocket ss = new ServerSocket(4444);
		Socket socket = new Socket("localhost", 4444);
		System.out.println("Made socket, now reading from socket");
		Thread t = new BlockedOnIO(socket.getInputStream());
		t.start();
		Thread.sleep(5000);
		t.interrupt();
		t.join();
		
		ss.close();
		socket.close();
	}
}
