import java.util.Random;
import java.util.concurrent.Semaphore;

public class Producer extends Thread{
	
	Buffer buffer;
	Random rnd = new Random();
	int index = 0;
	int bufferItem;
	
	public Producer(Buffer buffer, int index, int numConsumers) {
		this.buffer = buffer;
		this.index = index;
	}
	
	public void run() {
		while (true) {
			rest();
			create_item();
		}
	}
	
	private void rest() {
		System.out.println("Producer " + index + " está em repouso");
		
		try {
			sleep((int)(rnd.nextFloat()*4000.0f));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void create_item () {
		bufferItem = (int) Math.round(Math.random() * 50);
		if(buffer.insert_item(bufferItem)) {
			System.out.println("Producer " + index + " colocou o item " + bufferItem + " no buffer");
		}
		else {
			System.out.println("BUFFER CHEIO: O Producer " + index + " não conseguiu completar a tarefa.");
		}
	}
}
