import java.util.concurrent.Semaphore;

/**
 * 
 * @author Eileen Guerrero, Juan Manuel
 *
 */
public class Estudiante extends Thread {

	private int studentNumber;
	private Semaphore helpStudent;
	private Semaphore waiting;
	private Semaphore takeNap;
	private Semaphore IrMonitoria;


	public Estudiante(int studentId, Semaphore help, Semaphore salaDeEspera,Semaphore IrMonitoria, Semaphore nap) {		
		this.helpStudent = help;
		this.waiting = salaDeEspera;
		this.studentNumber = studentId;
		this.takeNap = nap;
		this.IrMonitoria=IrMonitoria;
	}

	/**
	 * run method to control the thread
	 */
	@Override
	public void run() {
		while (true) {
			try {				
				if(waiting.availablePermits()>0 && IrMonitoria.availablePermits()==1) {
					waiting.acquire();
					System.out.println("#" + studentNumber + " en sala de espera");
					IrMonitoria.acquire();
					System.out.println("#" + studentNumber + " esperando al monitor");
					helpStudent.acquire();
					waiting.release();
					takeNap.release();			
					System.out.println("#" + studentNumber + " está siendo atendido");
					sleep((long) (Math.random() * 15000));
					System.out.println("#" + studentNumber + " fue atendido");
					helpStudent.release();
					takeNap.release();	
				}else {
					IrMonitoria.release();
					System.out.println("#"+ studentNumber + " se va a programar a la sala");
					sleep((long) (Math.random() * 100000));
				}
			} catch (InterruptedException e) {
				System.out.println("Error: "+e.getMessage());
			}
		}
	}

	

}
