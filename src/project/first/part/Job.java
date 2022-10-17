package project.first.part;

/**
 * @author Catarina Serrano
 *
 */
public class Job {
	private int arrivalTime; 
	private int firstArrivalTime;
	private int duration;
	private int PID;
	
	public Job(){}

	public Job(int arrivalTime, int duration, int PID) {		
		this.arrivalTime = arrivalTime;
		this.duration = duration;
		this.firstArrivalTime = arrivalTime;
		this.PID = PID;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public int getFirstArrivalTime() {
		return firstArrivalTime;
	}

	public void setfirstArrivalTime(int firstArrivalTime) {
		this.firstArrivalTime = firstArrivalTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public int getPID() {
		return PID;
	}

	public void setPID(int pid) {
		PID = pid;
	}

	@Override
	public String toString() {
		return "Job [arrivalTime=" + arrivalTime + ", firstArrivalTime=" + firstArrivalTime + ", duration=" + duration + ", PID="+ PID + "]";
	}	
}
