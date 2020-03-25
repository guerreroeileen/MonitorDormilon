import java.util.concurrent.Semaphore;

/**
 * 
 * @author Eileen Guerrero, Juan Manuel
 *
 */
public class Estudiante extends Thread {

	private int studentNumber;
	private Semaphore helpStudent;
	private Semaphore salaDeEspera;
	private Semaphore takeNap;

	public Estudiante(int studentId, Semaphore help, Semaphore salaDeEspera, Semaphore nap) {
		
		this.helpStudent = help;
		this.salaDeEspera = salaDeEspera;
		this.studentNumber = studentId;
		this.takeNap = nap;
	}

	/**
	 * run method to control the thread
	 */
	@Override
	public void run() {
		while (true) {
			try {
				salaDeEspera.acquire();
				System.out.println("#" + studentNumber + " en sala de espera");
				salaDeEspera.release();
				System.out.println("#" + studentNumber + " esperando al monitor");
				helpStudent.acquire();
				takeNap.release();
				System.out.println("#" + studentNumber + " está siendo atendido");
				sleep((long) (Math.random() * 15000));
				helpStudent.release();
				System.out.println("#" + studentNumber + " fue atendido");
				takeNap.acquire();

			} catch (InterruptedException e) {
				System.out.println("Error: "+e.getMessage());
			}
		}
	}

}
