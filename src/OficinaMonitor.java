import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.Semaphore;

public class OficinaMonitor {

	private int cantidad_sillas_total;
	private int cantidad_sillas_disponibles;
	

	private Semaphore corredor;
	private Semaphore monitor;

	
	private  Estudiante estudiantes[];
	private MonitorDormilon monitor_dormilon;
	public MonitorDormilon getMonitor_dormilon() {
		return monitor_dormilon;
	}

	public void setMonitor_dormilon(MonitorDormilon monitor_dormilon) {
		this.monitor_dormilon = monitor_dormilon;
	}

	public Estudiante[] getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(Estudiante[] estudiantes) {
		this.estudiantes = estudiantes;
	}

	/**
	 * Constructor de la clase principal
	 */
	public OficinaMonitor() {
		// TODO Auto-generated constructor stub
		cantidad_sillas_total = 3;
		corredor = new Semaphore(1, true);
		monitor = new Semaphore(1, true);
	
	}

	public int getCantidad_sillas_total() {
		return cantidad_sillas_total;
	}

	public void setCantidad_sillas_total(int cantidad_sillas_total) {
		this.cantidad_sillas_total = cantidad_sillas_total;
	}

	public int getCantidad_sillas_disponibles() {
		return cantidad_sillas_disponibles;
	}

	public void setCantidad_sillas_disponibles(int cantidad_sillas_disponibles) {
		this.cantidad_sillas_disponibles = cantidad_sillas_disponibles;
	}

	public Semaphore getCorredor() {
		return corredor;
	}

	public void setCorredor(Semaphore corredor) {
		this.corredor = corredor;
	}

	public Semaphore getMonitor() {
		return monitor;
	}

	public void setMonitor(Semaphore monitor) {
		this.monitor = monitor;
	}

	public static void main(String[] args) {
		System.out.println("Iniciando ejercicio Monitor Dormilon...");
		
		
		OficinaMonitor oficina = new OficinaMonitor();
		
		System.out.println("Ingrese la cantidad de estudiantes:");
		
		
		
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		try {
			
			String num_est = br.readLine();
			int num = Integer.parseInt(num_est);
			
			
			/**
			 * definiendo cantidad de estudiantes
			 */
			oficina.setEstudiantes( new Estudiante[num]);
				
//			semaforoMonitor.drainPermits();
//			semaforoSalaEspera.drainPermits();
			
			oficina.setMonitor_dormilon(new MonitorDormilon(oficina.getMonitor(), num));
			oficina.getMonitor_dormilon().start();
			int numero;
			
			
			
			for (int i = 0; i < oficina.getEstudiantes().length; i++) {
				
				
				numero = i+1;
				oficina.getEstudiantes()[i] = new Estudiante(oficina.getMonitor(), oficina.getCorredor(), "Estudiante: "+numero);
				oficina.getEstudiantes()[i].start();
				
			}
			oficina.getMonitor_dormilon().setEstudiantes(oficina.getEstudiantes());
			
			
//			for (int i = 0; i < estudiantes.length; i++) {
//				estudiantes[i].stop();
//			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	}

}
