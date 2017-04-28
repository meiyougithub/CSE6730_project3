import java.util.*;

public class Transportation {

    private static int air_passenger_mean = Parameter.air_passenger_mean;
    private static int train_passenger_mean = Parameter.train_passenger_mean;
    private static int ship_passenger_mean = Parameter.ship_passenger_mean;
    private static double air_speed_mean = Parameter.air_speed_mean;
    private static double train_speed_mean = Parameter.train_speed_mean;
    private static double ship_speed_mean = Parameter.ship_speed_mean;

    // Number of three types passengers
    private static int num_infected;
    private static int num_antibody;
    private static int num_withoutantibody;

    // public Transportation(int air_passenger_mean, int ship_passenger_mean, int train_passenger_mean, 
    //     double air_speed_mean, double train_speed_mean, double ship_speed_mean) {
    //     this.air_passenger_mean = air_passenger_mean;
    //     this.train_passenger_mean = train_passenger_mean;
    //     this.ship_passenger_mean = ship_passenger_mean;
    //     this.air_speed_mean = air_speed_mean;
    //     this.train_speed_mean = ship_speed_mean;
    //     this.ship_speed_mean = train_speed_mean;
    // }

    public static TransportEvent depart(double current_time, City departure, int trip_type) {
        // Get the information of the population at departure city
        Population population = departure.getPopulation();
        int total_pop = population.getTotalPop();
        int pop_infected = population.getPopInfected();
        int pop_antibody = population.getPopAntibody();
//        int pop_withoutantibody = population.getPopWithoutAntibody();

        // Set the destination of this trip
        City destination = getDestination(departure);

        // Compute the distance between departure and destination
        double distance = City.distance(departure, destination);

        // Declare a transportation event
        TransportEvent event;
        int num_passenger;
        double trip_time;

        // Compute the number of passengers and trip times according to different trip type
        switch (trip_type) {
            case TransportEvent.AIR:
                num_passenger = getNumPassengers(air_passenger_mean);
                trip_time = Math.round(distance / getSpeed(air_speed_mean));
                break;

            case TransportEvent.TRAIN:
                num_passenger = getNumPassengers(train_passenger_mean);
                trip_time = Math.round(distance / getSpeed(train_speed_mean));
                break;

            default:
                num_passenger = getNumPassengers(ship_passenger_mean);
                trip_time = Math.round(distance / getSpeed(ship_speed_mean));
                break;
        }

        // Set the number of three type passengers
        setTripInfo(num_passenger, total_pop, pop_infected, pop_antibody);
        event = new TransportEvent((int) (current_time + trip_time), destination, num_infected, num_antibody, num_withoutantibody, trip_type);
        return event;
    }

    public static int getNumPassengers(int passenger_mean) {
        double std = 0.05;
        int passenger = (int) ((VirusSim.rand.nextGaussian() * std + 1) * passenger_mean);
        return passenger;
    }

    public static double getSpeed(double speed_mean) {
        double std = 0.05;
        double speed = (int) ((VirusSim.rand.nextGaussian() * std + 1) * speed_mean);
        return speed;
    }

    public static City getDestination(City departure) {
        // Randomly choose the destination city
        int max = VirusSim.cities.length;
        int index = VirusSim.rand.nextInt(max);
        while (departure == VirusSim.cities[index]){
            index = VirusSim.rand.nextInt(max);
        }
        return VirusSim.cities[index];
    }

    public static void setTripInfo(int num_passenger, int total_pop, int pop_infected, int pop_antibody) {
        num_infected = (int) (num_passenger / total_pop * pop_infected);
        num_antibody = (int) (num_passenger / total_pop * pop_antibody);
        num_withoutantibody = num_passenger - num_infected - num_antibody;
    }

    // public double getAirSpeed() { return air_speed; }
    // public double getTrainSpeed() { return train_speed; }
    // public double getShipSpeed() { return ship_speed; }
    // public int getAirPassenger() { return air_passenger; }
    // public int getTrainPassenger() { return train_passenger; }
    // public int getShipPassenger() { return ship_passenger; }

}
