package project.first.part;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class SJF {
	public static void init(Queue<Job> readyJobs) {
		int systemTime = 0;	
		// variáveis para as médias
		float averageReturnTime = 0;
		float averageResponseTime = 0;
		float averageWaitTime = 0;
		// total de processos
		int sizeJobs = readyJobs.size();
		Job job = new Job();
		
		// Fila de prioridade de acordo com o tempo de duracao de cada processo
		LinkedList<Job> priorityQueue = new LinkedList<Job>();
		
		// Executa ate que nao exista processos nas filas
		while(readyJobs.peek() != null || priorityQueue.peek() != null) {
			// Um processo só pode ser adicionado na lista de prioridade caso ele ja tenha chegado no tempo atual do sistema
			while (readyJobs.peek() != null && readyJobs.peek().getArrivalTime() <= systemTime) 
				priorityQueue.add(readyJobs.poll()); //processo de troca de fila
			
			// Efetua ordenacao de acordo com o tempo de duracao do job
			Collections.sort(priorityQueue, new Comparator<Job>() {
	            @Override
	            public int compare(final Job object1, final Job object2) {
//	            	System.out.println("[DEBUG] duration 1 = " + object1.getDuration());
//	            	System.out.println("[DEBUG] duration 2 = " + object2.getDuration());
	                return object1.getDuration() - object2.getDuration();
	            }
	        });
			
			// Algum processo chegou na fila de prontos, de acordo com o tempo do sistema, e foi posto na fila de prioridades do SJF.
			if (priorityQueue.peek() != null){
				// Processo entra no processador
				job = priorityQueue.poll(); 
				
				// Computa o Tresp e Tesp
				averageResponseTime += systemTime - job.getArrivalTime();
				averageWaitTime += systemTime - job.getArrivalTime();
				
				// --- Processo finalizou ---
					
				// Atualiza do tempo do sistema
				systemTime += job.getDuration();
				// Computa o Tret
				averageReturnTime += systemTime - job.getArrivalTime();
				
			}
			// Caso nao tenha chegado nenhum processo na fila de prontos, atualiza o tempo
			else {
//				System.out.println("[DEBUG] else " + readyJobs.peek().getArrivalTime());
				systemTime = readyJobs.peek().getArrivalTime();
			}						
		}
		
		if (sizeJobs > 0)
			System.out.printf("SJF %.1f %.1f %.1f\n", (averageReturnTime/sizeJobs), (averageResponseTime/sizeJobs), (averageWaitTime/sizeJobs));		
	}
}
