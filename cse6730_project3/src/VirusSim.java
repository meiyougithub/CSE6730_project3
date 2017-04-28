import java.util.*;


class VirusSim {

    public static void main(String[] args){
        int clock = 0;
        int tick = 1;
        int maxLength = 4 * 7 * 24;
        int numCities = 50;
        City[] cities = new City[numCities];
        TreeSet<TransportEvent> events = new TreeSet<>();
        /*

        Initialize cities here

        */
        while (clock < maxLength){
            if (!events.isEmpty()){
                TransportEvent tempEvent = events.first();
                while (tempEvent.getDestTime() == clock){
                    events.remove();
                    int tempPop = tempEvent.getNumPassenger();
                    City tempCity = tempEvent.getDestCity();
                    tempCity.setPopWithoutAntibody() = tempCity.getPopWithoutAntibody() + tempPop;
                    if (!events.isEmpty()){
                        tempEvent = events.first();
                    }
                }
            }

            for (int i = 0; i < numCities; i++){
                Cities[i].hospitalTurn();
                Cities[i].virusTurn();
                Cities[i].transportTurn(clock, events);

            }
        }
        clock ++;

    }

}
