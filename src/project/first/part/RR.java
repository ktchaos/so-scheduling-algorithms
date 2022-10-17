package project.first.part;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author catarinaserrano
 *
 */
public class RR {
	public static void init(Queue<Job> readyJobs, int quantum){
		int systemTime = 0;		
		float averageReturnTime = 0;		
		float averageResponseTime = 0;		
		float averageWaitTime = 0;
		int sizeJobs = readyJobs.size();		
		Job job = new Job();
		
		// Fila auxiliar para escalonar apenas os processos que ja chegaram na fila de prontos
		Queue<Job> rrQueue = new LinkedList<Job>();
		
		// Array que ira verificar se houve a produção da primeira resposta
		boolean[] response = new boolean[sizeJobs];
		
		// Executa ate que nao exista processos nas filas
		while (readyJobs.peek() != null || rrQueue.peek() != null) {	
			// Vamos preencher a fila rrQueue apenas com
			// os processos que estao com tempo de chegada coerente com o tempo do sistema  
			
			while (readyJobs.peek() != null && readyJobs.peek().getArrivalTime() <= systemTime) 
				rrQueue.add(readyJobs.poll());
			
			if (rrQueue.peek() != null){
				
				// Processo entra no processador
				job = rrQueue.poll();				
				averageWaitTime += systemTime - job.getArrivalTime();
				
				// Verifica se ja houve a producao da primeira resposta deste job. de acordo com seu PID
				if (!response[job.getPID()]) { // primeira vez - verifica com a flag
					averageResponseTime += systemTime - job.getArrivalTime();
					response[job.getPID()] = true;
				}			
				
				// Verificar se o processo atual terminou sua execucao de acordo com o quantum
				if (job.getDuration() < quantum) {
					systemTime += job.getDuration();
					job.setDuration(0);
				}
				// Caso nao tenha terminado, diminui sua duracao para o proximo escalonamento
				else {
					systemTime += quantum; //atualiza o tempo de acordo com o quantum
					job.setDuration(job.getDuration() - quantum);				
				}

				/*
				 * Inserir na fila do rr os processos que chegaram da fila de prontos com o decorrer do tempo do sistema
				 * antes de re-inserir os processos incompletos na fila rr, para garantir a ordem correta dos processos.
				 */
				while (readyJobs.peek() != null && readyJobs.peek().getArrivalTime() <= systemTime) 
					rrQueue.add(readyJobs.poll());		
				
				// Verifica se o processo terminou
				if (job.getDuration() > 0) {
					job.setArrivalTime(systemTime);
					rrQueue.add(job);
				}
				else { // caso nao tenha terminado
					rrQueue.remove(job); // nao volta para a fila
					// Computa o tempo de retorno deste processo, a partir do primeiro tempo de chegada
					averageReturnTime += systemTime - job.getFirstArrivalTime();
				}
			}
			// Caso nao tenha chegado nenhum processo na fila de prontos, atualiza o tempo
			else {
				systemTime = readyJobs.peek().getArrivalTime();
			}	
		}
		
		if (sizeJobs > 0)		
			System.out.printf("RR %.1f %.1f %.1f\n", (averageReturnTime/sizeJobs), (averageResponseTime/sizeJobs), (averageWaitTime/sizeJobs));
	}
	
}
