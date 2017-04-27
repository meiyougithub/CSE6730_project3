/**
 * Created by WYF on 4/27/2017.
 */
import java.util.Random;

public class transportation {
    private int air_passenger;
    private int train_passenger;
    private int bus_passenger;
    private double air_speed;
    private double train_speed;
    private double bus_speed;
    public transportation(int air_passenger_mean, int bus_passenger_mean, int train_passenger_mean, double air_speed_mean, double train_speed_mean, double bus_speed_mean){
    this.air_passenger=randomNumPassengers(air_passenger_mean);
    this.train_passenger=randomNumPassengers(train_passenger_mean);
    this.bus_passenger=randomNumPassengers(bus_passenger_mean);
    this.air_speed=randomSpeed(air_speed_mean);
    this.bus_speed=randomSpeed(bus_speed_mean);
    this.train_speed=randomSpeed(train_speed_mean);
    }
    public int randomNumPassengers(int passenger_mean){
        double std = 0.05;
        Random r = new Random();
        int passenger = (int)((r.nextGaussian()*std+1)*passenger_mean);
        return passenger;
    }

    public double randomSpeed(double speed_mean){
        double std=0.05;
        Random r = new Random();
        double speed = (int)((r.nextGaussian()*std+1)*speed_mean);
        return speed;
    }

    public double getAirSpeed() {return air_speed;}
    public double getBusSpeed() {return bus_speed;}
    public double getTrainSpeed() {return train_speed;}
    public int getAirPassenger() {return air_passenger;}
    public int getBusPassenger() {return bus_passenger;}
    public int getTrainPassenger() {return train_passenger;}


}
