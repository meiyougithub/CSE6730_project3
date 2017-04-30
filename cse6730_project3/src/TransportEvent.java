import java.util.*;

public class TransportEvent implements Comparable<TransportEvent> {
    private static int numEvents = 0;

    final static int AIR = 0;
    final static int TRAIN = 1;
    final static int SHIP = 2;
	
	private int dest_time;
	private double num_infected;
    private double num_antibody;
    private double num_withoutantibody;
    private int trip_type;
	private City dest_city;
	private City dept_city;
	private int id;

	public TransportEvent(int dest_time, City dept_city, City dest_city, double num_infected, double num_antibody, double num_withoutantibody, int trip_type){
		this.dest_time = dest_time;
		this.dept_city = dept_city;
        this.dest_city = dest_city;
		this.num_infected = num_infected;
        this.num_antibody = num_antibody;
        this.num_withoutantibody = num_withoutantibody;
        this.trip_type = trip_type;
        this.id = numEvents ++;
	}

    public int compareTo (final TransportEvent ev) {
    	if (this.getDestTime() - ev.getDestTime() == 0){
            return this.getId() - ev.getId();
        }
        else{
            return this.getDestTime() - ev.getDestTime();
        }
    } 

    public int getDestTime() { return dest_time; }
    public City getDestCity() { return dest_city; }
    public City getDeptCity() { return dept_city; }
    public double getNumPassenger() { return num_infected + num_antibody + num_withoutantibody; }
    public double getNumInfected() { return num_infected; }
    public double getNumAntibody() { return num_antibody; }
    public double getNumWithoutAntibody() { return num_withoutantibody; }

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

    public int getId() { return id; }

}
