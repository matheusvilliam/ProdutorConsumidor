
public class Main {
	
	static int nProducers = 3;
	static int nConsumers = 3;
	static int sleepTime = 15000;

	public static void main(String[] args) {
		
		Consumer[] consumers = new Consumer[nConsumers];
		Producer[] producers = new Producer[nProducers];
		Buffer buffer = new Buffer();
		
		for (int i = 0; i < consumers.length; i++) {
			consumers[i] = new Consumer(buffer, i, 5);
			consumers[i].start();
		}
		
		for (int i = 0; i < producers.length; i++) {
			producers[i] = new Producer(buffer, i, 5);
			producers[i].start();
		}
		
		
		try {
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("Fim da Aplicação");
		System.exit(0);
		
	}
}
