package project.first.part;

/**
 * @author Catarina Serrano
 *
 */
import java.util.Queue;

public class FCFS {
	public static void init(Queue<Job> readyJobs) {
		// variavel para simular o tempo atual do sistema
		int currentTime = 0;
		
		// Quantidade necessária de tempo para executar um processo = (Tret)
		float averageReturnTime = 0;
		
		// Quantidade de tempo entre a requisição de execução de um programa e a produção da primeira resposta = (Tresp)
		float averageResponseTime = 0;
		
		// Quantidade de tempo que um processo aguardou na fila de prontos = (Tesp)
		float averageWaitTime = 0;
		
		// Quantidade de processos prontos para serem escalonados - total
		int totalJobs = readyJobs.size();
		
		Job job = new Job();
		
		// executar enquanto existir processos na fila de prontos
		while (readyJobs.peek() != null) {
			// Verifica se o processo chegou na fila de prontos (tempoDeChegada <= tempoDoSistema)
			if (readyJobs.peek().getArrivalTime() <= currentTime) {
				
				// Pega o primeiro processo da fila de prontos, simulando sua entrada no processador.
				job = readyJobs.poll();
				
				// Computa o tempo de Resposta 
				averageResponseTime += currentTime - job.getArrivalTime();
				// P1 -> Tresp = 0 - 0 = 0
				// P2 -> Tresp = 10 - 0 = 0
				// P3 -> Tresp = 30 - 4 = 26
				// P4 -> Tresp = 36 - 4 = 32
				
				// Computa o tempo de espera
				averageWaitTime += currentTime - job.getArrivalTime();
				// P1 -> Tesp = 0 - 0 = 0
				// P2 -> Tesp = 10 - 0 = 0
				// P3 -> Tesp = 30 - 4 = 26
				// P4 -> Tesp = 36 - 4 = 32
				
				// ---- Fim do pico do processo (sai do processador		
				// Atualiza o tempo atual, a partir do processo que finalizou
				currentTime += job.getDuration();
				
				// Computa o tempo de retorno deste processo
				averageReturnTime += currentTime - job.getArrivalTime();
				// P1 -> Tret = 10 - 0 = 10
				// P2 -> Tret = 30 - 0 = 0
				// P3 -> Tret = 36 - 4 = 32
				// P4 -> Tret = 36 - 4 = 32
				
			}
			// Nao chegou nenhum processo na fila de prontos, atualiza o tempo do sistema
			else {
				currentTime = readyJobs.peek().getArrivalTime();
			}
		}
		
		if (totalJobs > 0)
			System.out.printf("FCFS %.1f %.1f %.1f\n",
				(averageReturnTime/totalJobs), (averageResponseTime/totalJobs), (averageWaitTime/totalJobs));		
	}
}

