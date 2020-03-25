import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;


/**
 * 
 * @author Eileen Guerrero, Juan Manuel
 *
 */
public class OficinaMonitor {

	private int studentsTotal;
	private ArrayList<Estudiante> students;
	private MonitorDormilon monitorDormilon;

	//MONITOR ATENDIENDO UN ESTUDIANTE
	private Semaphore atencionMonitor;
	//SALA DE ESPERA
	private Semaphore wainting;
	
	private Semaphore entrandoAlaSalaDeEspera;
	//MONITOR DURMIENDO
	private Semaphore takeNap;

	/**
	 * Oficina monitor constructor
	 * @param studentsTotal number of students that you want to play with
	 */
	public OficinaMonitor(int studentsTotal) {
		this.studentsTotal = studentsTotal;
		this.students = new ArrayList<Estudiante>();
		this.atencionMonitor = new Semaphore(1, true);
		this.wainting = new Semaphore(3,true);
		this.entrandoAlaSalaDeEspera = new Semaphore(1,true);
		this.takeNap = new Semaphore(1,true);
		takeNap.drainPermits();
	}

	/**
	 * Create and start students threads
	 */
	public void inicializarYDespertarHilos() {
		//CREANDO LA CANTIDAD DE ESTUDIANTES RECIBIDA POR PARAMENTRO
		for (int i = 0; i < studentsTotal; i++) {
			students.add(new Estudiante(i, atencionMonitor, wainting, takeNap));
		}

		monitorDormilon = new MonitorDormilon(takeNap);
		monitorDormilon.start();

		//INICIANDO CADA UNO DE LOS HILOS DE CADA ESTUDIANTE
		for (int i = 0; i < students.size(); i++) {
			students.get(i).start();
		}
	}

	/**
	 * Main thread for run all the program
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Iniciando ejercicio Monitor Dormilon...");
		System.out.println("Ingrese la cantidad de estudiantes:");
		InputStreamReader isr = new InputStreamReader(System.in);

		BufferedReader br = new BufferedReader(isr);
		try {
			String num_est = br.readLine();
			int num = Integer.parseInt(num_est);

			OficinaMonitor oficinaMonitorMainInstance = new OficinaMonitor(num);

			oficinaMonitorMainInstance.inicializarYDespertarHilos();

		} catch (IOException e) {
			System.out.println("Error: "+e.getMessage());
		}

	}

}
