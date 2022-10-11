package project.first.part;

/**
 * @author Catarina Serrano
 *
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
	public static void main (String[] args) {
		try {
			// Input file
			String filePath = "/Users/catarinaserrano/desktop/ufpb/so/first-part/src/jobs.txt";
	        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
	        
	        // LinkedList para os jobs
	        LinkedList<Job> jobs = new LinkedList<Job>();	
	        // linha do arquivo
	        String line;
	        // par em cada linha
	        String[] pair = new String[2];
	        
	        // Preenchendo a lista de processos
	        while ((line = bufferedReader.readLine()) != null) {
	        	pair = line.split(" ");
	        	jobs.add(new Job(Integer.parseInt(pair[0]), Integer.parseInt(pair[1])));
	        }
	        bufferedReader.close();
	        
	        // Sort para ordenar processos de acordo com o seu tempo de chegada (menor para o maior)
	        if (jobs.size() > 0) {
	          Collections.sort(jobs, new Comparator<Job>() {
	              @Override
	              public int compare(final Job first, final Job second) {
	                  return first.getArrivalTime() - second.getArrivalTime();
	              }
	          });
	        }
	        
	        // 1. FCFS
	        FCFS.init(new LinkedList<Job>(jobs));
			// 2. SJF
	        
	        // 3. RR

	    } catch (IOException ex) {
	        System.err.print(ex.getMessage());
	    } 			
	}
}