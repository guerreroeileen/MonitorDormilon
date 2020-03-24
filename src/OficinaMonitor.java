import java.util.concurrent.Semaphore;

public class OficinaMonitor {

	private int cantidad_sillas;

	private Semaphore corredor;
	private Semaphore monitor;

	/**
	 * Constructor de la clase principal
	 */
	public OficinaMonitor() {
		// TODO Auto-generated constructor stub
		cantidad_sillas = 3;
		corredor = new Semaphore(1, true);
		monitor = new Semaphore(1, true);

	}
	
	public static void main(String[] args) {
		
	}

}
