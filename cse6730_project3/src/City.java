//import com.sun.xml.internal.rngom.digested.DDataPattern;

import java.util.*;

public class City {
    public static boolean warning = false;
    private Population population;
    private String name;
    private int num_air = 1, num_train = 1, num_ship = 1;
    private static double[][] distance_mat;
    private static double[] pop_mat;
    private static double[][] distance_prob;
    private double[] pop_prob;
    private String state_name;

    private int cityId;
    private int stateId;

    // Latitude and longitude of this city
    final private double latitude;
    final private double longitude;

    public City(String name, String state, Population population, double latitude, double longitude, int cityId, int stateId) {
        this.name = name;
        this.state_name = state;
        this.population = population;
        this.latitude = latitude;
        this.longitude = longitude;
        this.cityId = cityId;
        this.stateId = stateId;
        this.num_air = (int) (Parameter.max_air * Math.sqrt(population.getTotalPop()) / Parameter.pop_scale);
        this.num_train = (int) (Parameter.max_train * Math.sqrt(population.getTotalPop()) / Parameter.pop_scale);
        this.num_ship = (int) (Parameter.max_ship * Math.sqrt(population.getTotalPop()) / Parameter.pop_scale);
//        System.out.println(name + ": " + " num_air " + num_air + " num_train " + num_train + " num_ship " + num_ship );
    }

    public static void computeDistance(){
        distance_mat = new double[VirusSim.num_cities][VirusSim.num_cities];
        for (int i = 0; i < VirusSim.num_cities ; i++){
            for (int j = i + 1 ; j < VirusSim.num_cities; j++){
                distance_mat[i][j] = 1. / distance(VirusSim.cities[i], VirusSim.cities[j]);
                distance_mat[j][i] = distance_mat[i][j];
            }
        }
//        Parameter.printMatrix(distance_mat);

    }

    public static void computePop(){
        pop_mat = new double[VirusSim.num_cities];
        for (int i = 0; i < VirusSim.num_cities ; i++){
            pop_mat[i] = Math.sqrt(VirusSim.cities[i].getPopulation().getTotalPop());
            Population.grand_sq_total_pop += pop_mat[i];
        }
//        Parameter.printMatrix(pop_mat);
    }

    public void computePopProb(){
        pop_prob = new double[VirusSim.num_cities];
        double temp_sum = 0;
        double temp_total = Population.grand_sq_total_pop - Math.sqrt(this.getPopulation().getTotalPop());
        for (int i = 0; i < VirusSim.num_cities; i++){
            pop_prob[i] = temp_sum +  ((i == this.getCityId()) ? 0 : pop_mat[i]) / temp_total;
            temp_sum = pop_prob[i];
        }
    }


    public static void computeDistanceProb(){
        distance_prob = new double[VirusSim.num_cities][VirusSim.num_cities];

        for (int i = 0; i < VirusSim.num_cities ; i++){
            double temp_sum = 0;
            for (int j = 0 ; j < VirusSim.num_cities; j++){
                temp_sum += distance_mat[i][j];
            }
            double prefix = 0;
            for (int j = 0 ; j < VirusSim.num_cities; j++){
                distance_prob[i][j] = prefix + distance_mat[i][j] / temp_sum;
                prefix = distance_prob[i][j];
            }
        }
//        Parameter.printMatrix(distance_prob);
    }

    public void hospitalTurn() {
        if (!warning){
            if ((this.getPopulation().getPopDead() + this.getPopulation().getPopSymptom()) >= Parameter.threshold){

                warning = true;
                //System.out.println("Warning is on!!! " + this.getName() + " " + (int)this.getPopulation().getPopDead() + " " + (int)this.getPopulation().getPopSymptom());
            }
        }
        if (warning){
            Hospital.quarantine(population);
        }
        if (Hospital.research){
            Hospital.vaccinate(population);
            Hospital.cure(population);
        }
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

    public void transportTurn(int current_time, int debug) {
        if (debug == 0) {
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
        } else {
            // Set air event
            for (int i = 0; i < num_air; i++) {
                TransportEvent airEvent = Transportation.depart(current_time, this, TransportEvent.AIR);
                VirusSim.events.add(airEvent);
                //System.out.print("At " + current_time + ", " + this.getName() + " add event: ");
                //VirusSim.printEvent(airEvent);
            }
            // Set train event
            for (int i = 0; i < num_train; i++) {
                TransportEvent trainEvent = Transportation.depart(current_time, this, TransportEvent.TRAIN);
                VirusSim.events.add(trainEvent);
                //System.out.print("At " + current_time + ", " + this.getName() + " add event: ");
                //VirusSim.printEvent(trainEvent);
            }
            // Set ship event
            for (int i = 0; i < num_ship; i++) {
                TransportEvent shipEvent = Transportation.depart(current_time, this, TransportEvent.SHIP);
                VirusSim.events.add(shipEvent);
                //System.out.print("At " + current_time + ", " + this.getName() + " add event: ");
                //VirusSim.printEvent(shipEvent);
            }
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
    public String getState() { return state_name;}

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }

    public int getCityId(){ return cityId;}
    public int getStateId(){ return stateId;}

    public double[] getPopProb() { return pop_prob;}

    public double[] getDistProb() { return distance_prob[this.getCityId()];}
}