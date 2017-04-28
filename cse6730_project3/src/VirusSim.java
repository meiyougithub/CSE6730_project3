import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class VirusSim {
    int num_cities = 50;
    public static City[] cities;
    public static TreeSet<TransportEvent> events;
    public static Random rand;

    public static void main(String[] args){
        int clock = 0;
        int tick = 1;
        int max_length = 4 * 7 * 24;
        InputWindow myWindow = new InputWindow();
        myWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //2. Size the frame
        myWindow.setSize(800, 600);
        myWindow.setVisible(true);

        while (!myWindow.isPressed_or_not()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int num_city = myWindow.getNum_city();
        String csvFile = "city_pop.csv";
        String line = "";
        String csvsplit = ",";
        City[] cityList = new City[num_city];
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int i = -1;
            while ((line = br.readLine()) != null) {
                if (i != -1 && i<num_city) {
                    String[] temp = line.split(csvsplit);
                    String city_name = temp[1];
                    int total_population = Integer.parseInt(temp[3]);
                    double latitude = Double.parseDouble(temp[6]);
                    double longitude = Double.parseDouble(temp[7]);
                   // System.out.println(city_name +" "+ total_population+" "+latitude+" "+longitude);
                    Population pop_tmp = new Population(total_population);
                    City city_tmp =new City(city_name,pop_tmp,latitude,longitude);
                    cityList[i]=city_tmp;
                }
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
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
