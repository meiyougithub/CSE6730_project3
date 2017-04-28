import java.util.*;

public class City {
    private Population population;
    private String name;

    // Latitude and longitude of this city
    final private double latitude;
    final private double longitude;

    public City(String name, Population population, double latitude, double longitude) {
        this.name = name;
        this.population = population;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void hospitalTurn() {
        Hospital.quarantine(population);
        Hospital.vaccinate(population);
        Hospital.cure(population);
    }

    public void virusTurn() {
        Virus.computeInfected(population);
        Virus.computeMorbidity(population);
        Virus.computeLethality(population);
    }

    public void transportTurn(int current_time) {
        // Set air event
        for (int i = 0; i < num_air; i++) {
            TransportEvent airEvent = Transportation.depart(current_time, this, TransportEvent.AIR);
            VirusSim.events.add(airEvent);
        }
        // Set train event
        for (int i = 0; i < num_train; i++) {
            TransportEvent trainEvent = Transportation.depart(current_time, this, TransportEvent.TRAIN);
            VirusSim.events.add(trainEvent);
        }
        // Set ship event
        for (int i = 0; i < num_ship; i++) {
            TransportEvent shipEvent = Transportation.depart(current_time, this, TransportEvent.SHIP);
            VirusSim.events.add(shipEvent);
        }
    }

    public void handle(TransportEvent event) {
        population.setPopInfected( population.getPopInfected() + event.getNumInfected() );
        population.setPopAntibody( population.getPopAntibody() + event.getNumAntibody() );
        population.setPopWithoutAntibody( population.getPopWithoutAntibody() + event.getNumWithoutAntibody() );
        population.setTotalPop( population.getTotalPop() + event.getNumPassenger() );
    }

    public static double distance(City source, City target) {
        double d1 = source.getLongitude() - target.getLongitude();
        double d2 = source.getLatitude() - target.getLatitude();
        double coef = 111.0; // 111.0 km/deg
        return Math.sqrt(Math.pow(d1, 2) + Math.pow(d2, 2)) * coef;
    }

    public Population getPopulation() { return population; }

    public String getName() { return name; }

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }
}