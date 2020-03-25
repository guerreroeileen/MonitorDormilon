import java.util.concurrent.Semaphore;

/**
 * 
 * @author Eileen Guerrero, Juan Manuel
 *
 */
public class MonitorDormilon extends Thread {

	private Semaphore takeNap;

	/**
	 * Constructor for a teacher assistant
	 * @param nap
	 */
	public MonitorDormilon(Semaphore nap) {
		this.takeNap = nap;
	}

	/**
	 * run method to control the thread
	 */
	@Override
	public void run() {
		while (true) {
			try {
				takeNap.acquire();
			} catch (InterruptedException e) {
				System.out.println("Error: "+e.getMessage());
			}
		}
	}

}
