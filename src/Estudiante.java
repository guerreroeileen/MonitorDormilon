import java.util.concurrent.Semaphore;

public class Estudiante extends Thread {

	// monitores y semaforos
	private Semaphore monitor;
	private Semaphore corredor;

	/**
	 * cantidad de sillas
	 */
	private int cantidad_sillas;

	/**
	 * Nombre o identificador de ese estudiante
	 */
	private String nombre_estudiante;

	/**
	 * Me dice hasta cuando ese hilo debe esperar
	 */
	private boolean bandera;
	
	/**
	 * Metodo constructor de la clase EEtudiante
	 * @param moni
	 * @param corre
	 * @param nombre_estud
	 */
	public Estudiante(Semaphore moni, Semaphore corre, String nombre_estud) {
		monitor = moni;
		corredor = corre;
		nombre_estudiante=nombre_estud;
		bandera = false;
	}
	
	

	/**
	 * Metodo run. Administra el hilo del estudiante.
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			
		}
	}

}
