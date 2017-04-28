import java.util.*;

public class VirusSim {
    int num_cities = 50;
    public static City[] cities;
    public static TreeSet<TransportEvent> events;
    public static Random rand;

    public static void main(String[] args){
        int clock = 0;
        int tick = 1;
        int max_length = 4 * 7 * 24;
        
        /*

        Initialize cities here

        Initialize event list here

        */
        while (clock < max_length) {
            if (!events.isEmpty()) {
                TransportEvent temp_event = events.first();
                while (temp_event.getDestTime() == clock) {
                    events.remove();
                    City temp_city = temp_event.getDestCity();
                    temp_city.handle(temp_event);
                    if (!events.isEmpty()){
                        tempEvent = events.first();
                    } else {
                        break;
                    }
                }
            }

            for (int i = 0; i < numCities; i++){
                cities[i].hospitalTurn();
                cities[i].virusTurn();
                cities[i].transportTurn(clock);

            }

            clock ++;
        }

    }

}
