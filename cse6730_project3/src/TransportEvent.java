import java.util.*;

public class TransportEvent implements Comparable<TransportEvent> {

    final static int AIR = 0;
    final static int TRAIN = 1;
    final static int SHIP = 2;
	
	private int dest_time;
	private int num_infected;
    private int num_antibody;
    private int num_withoutantibody;
    private int trip_type;
	private City dest_city;
	private City dept_city;

	public TransportEvent(int dest_time, City dept_city, City dest_city, int num_infected, int num_antibody, int num_withoutantibody, int trip_type){
		this.dest_time = dest_time;
		this.dept_city = dept_city;
        this.dest_city = dest_city;
		this.num_infected = num_infected;
        this.num_antibody = num_antibody;
        this.num_withoutantibody = num_withoutantibody;
        this.trip_type = trip_type;
	}

    public int compareTo (final TransportEvent ev) {
    	return this.getDestTime() - ev.getDestTime();
    } 

    public int getDestTime() { return dest_time; }
    public City getDestCity() { return dest_city; }
    public City getDeptCity() { return dept_city; }
    public int getNumPassenger() { return num_infected + num_antibody + num_withoutantibody; }
    public int getNumInfected() { return num_infected; }
    public int getNumAntibody() { return num_antibody; }
    public int getNumWithoutAntibody() { return num_withoutantibody; }
    public String getType(){
	    if (trip_type == 0){
	        return "AIR";
        }
        else if (trip_type == 1){
	        return "TRAIN";
        }
        else if (trip_type == 2){
            return "SHIP";
        }
        else{
            return "ERROR TRIP";
        }
    }

}
