import java.util.concurrent.Semaphore;

/**
 * 
 * @author Eileen Guerrero, Juan Manuel
 *
 */
public class MonitorDormilon extends Thread {

	private int numero_estudiante;

	public int getNumero_estudiante() {
		return numero_estudiante;
	}


	public void setNumero_estudiante(int numero_estudiante) {
		this.numero_estudiante = numero_estudiante;
	}


	public Semaphore getCorredor() {
		return corredor;
	}


	public void setCorredor(Semaphore corredor) {
		this.corredor = corredor;
	}


	public Estudiante[] getEstudiantes() {
		return estudiantes;
	}


	public void setEstudiantes(Estudiante[] estudiantes) {
		this.estudiantes = estudiantes;
	}


	public Semaphore getSemaforo_oficina() {
		return semaforo_oficina;
	}


	public void setSemaforo_oficina(Semaphore semaforo_oficina) {
		this.semaforo_oficina = semaforo_oficina;
	}


	public OficinaMonitor getOficina_monitor() {
		return oficina_monitor;
	}


	public void setOficina_monitor(OficinaMonitor oficina_monitor) {
		this.oficina_monitor = oficina_monitor;
	}


	private Semaphore corredor;

	private Estudiante estudiantes[];

	private Semaphore semaforo_oficina;

	private OficinaMonitor oficina_monitor;
	
	public MonitorDormilon(Semaphore ofi, int num) {
		// TODO Auto-generated constructor stub
		semaforo_oficina = ofi;
		oficina_monitor = new OficinaMonitor();
		
	}
	

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (true) {
			
			try {
				semaforo_oficina.acquire();
				
				/*
				 * Monitor durmiendo y hay 3 sillas disponibles
				 */
				if (oficina_monitor.getCantidad_sillas_disponibles()==3) {
					System.out.println("Monitor duerimiendo...");
					
				}else {
					System.out.println("Entrando a monitoria...");
				}
				
				
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
