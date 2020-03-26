import java.util.concurrent.Semaphore;

/**
 * 
 * @author Eileen Guerrero, Juan Manuel
 *
 */
public class MonitorDormilon extends Thread {

	private Semaphore takeNap;
	private Semaphore waiting;
	private Semaphore helpStudent;

	/**
	 * Constructor for a teacher assistant
	 * @param nap
	 */
	public MonitorDormilon(Semaphore nap) {
		this.takeNap = nap;
		waiting = new Semaphore(3,true);
		helpStudent = new Semaphore(1,true);
	}


	/**
	 * run method to control the thread
	 */
	@Override
	public void run() {
		while (true) {
			try {
				
				if(waiting.availablePermits() == 3 && helpStudent.availablePermits()==1) {
					System.out.println("Monitor durmiendo");
				}else {
					takeNap.acquire();
				}
			} catch (InterruptedException e) {
				System.out.println("Error: "+e.getMessage());
			}
		}
	}

	public Semaphore getTakeNap() {
		return takeNap;
	}

	public void setTakeNap(Semaphore takeNap) {
		this.takeNap = takeNap;
	}

	public Semaphore getWaiting() {
		return waiting;
	}

	public void setWaiting(Semaphore waiting) {
		this.waiting = waiting;
	}

	public Semaphore getHelpStudent() {
		return helpStudent;
	}

	public void setHelpStudent(Semaphore helpStudent) {
		this.helpStudent = helpStudent;
	}

}
