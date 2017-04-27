import java.util.*;

class TransportEvent implements Comparable<TransportEvent>{
	
	private int destTime;
	private int numPassenger;
	private City destCity;

	TransportEvent(int destTime, int numPassenger, City destCity){
		this.destTime = destTime;
		this.numPassenger = numPassenger;
		this.destCity = destCity;
	}

    public int compareTo(final TransportEvent ev){
    	return this.getDestTime() - ev.getDestTime();
    } 

    public int getDestTime(){return this.destTime;}


}
