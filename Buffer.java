import java.util.concurrent.Semaphore;

public class Buffer {

	public static int N_Itens = 5;
	private int item[] = new int[N_Itens];
	
	public int qtdItens = 0;
	
	private Semaphore bufferVazio = new Semaphore(N_Itens);
	private Semaphore bufferCheio = new Semaphore(0);
	public Semaphore mutex = new Semaphore(1);
	
	//INSERINDO ITENS
	public boolean insert_item (int item) {
		try {
			bufferVazio.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(qtdItens < N_Itens) {
			this.item[qtdItens] = item;
			qtdItens++;
			bufferCheio = new Semaphore(qtdItens);
			return true;
		}
		return false;
	}
	
	//REMOVENDO ITENS
	public boolean remove_item (int id, int consumerId) {
		try {
			bufferCheio.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(qtdItens > 0) {
			System.out.println("O Consumidor " + consumerId + " removeu o item " + this.item[id] + " do buffer.");
			qtdItens--;
			for(int i = 0; i < qtdItens; i++) {
				item[i] = item[i + 1];
			}
			if(!(qtdItens == 0)) {
				bufferCheio.release();
				bufferVazio.release();	
			} else {
				bufferVazio.release();
			}
			return true;
		}
		return false;
	}
}
