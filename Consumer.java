import java.util.Random;

public class Consumer  extends Thread {

	Buffer buffer;
	Random rnd = new Random();
	int index = 0;
	int bufferItem;
	
	public Consumer(Buffer buffer, int index, int numConsumers) {
		this.buffer = buffer;
		this.index = index;
	}
	
	public void run() {
		while (true) {
			rest();
			get_item();
		}
	}
	
	private void rest() {
		System.out.println("Consumer " + index + " está em repouso");
		
		try {
			sleep((int)(rnd.nextFloat()*4000.0f));
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void get_item() {
		if(buffer.remove_item(0,index)) {
			System.out.println("Resta(m) " + buffer.qtdItens + " item(ns) no buffer");
		}
		else {
			System.out.println("BUFFER VAZIO: O Consumer " + index + " não conseguiu completar a tarefa.");
		}
	}
}
