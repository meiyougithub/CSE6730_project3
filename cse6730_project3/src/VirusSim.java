import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;

public class VirusSim {

    public static City[] cities;
    public static TreeSet<TransportEvent> events = new TreeSet<>();
    public static Random rand = new Random();
    public static int num_cities = 0;

    public static void main(String[] args){
        int debug = 2;
        int clock = 0;
        int tick = 60; // in minutes
        int days = 1; // one month = four weeks = 28 days
        int max_length = (int) (days * 24 * 60 / tick);
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

        num_cities = myWindow.getNum_city();
        String csvFile = "city_pop.csv";
        String line = "";
        String csvsplit = ",";
        cities = new City[num_cities];
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            int id = -1;
            while ((line = br.readLine()) != null) {
                if (id != -1 && id < num_cities) {
                    String[] temp = line.split(csvsplit);
                    String city_name = temp[1];
                    System.out.println(city_name);
                    System.out.println(temp[3].trim());
                    int total_population = Integer.parseInt(temp[3].trim());
                    double latitude = Double.parseDouble(temp[6].trim());
                    double longitude = Double.parseDouble(temp[7].trim());
                   // System.out.println(city_name +" "+ total_population+" "+latitude+" "+longitude);
                    Population pop_tmp = new Population(total_population);
                    City city_tmp =new City(city_name,pop_tmp,latitude,longitude,id);
                    cities[id]=city_tmp;
                }
                id++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        // prepare distance matrix
        City.computeDistance();

        // prepare distance prob matrix
        City.computeDistanceProb();

        // prepare pop vector
        City.computePop();

        // prepare pop prob vector
        for (int i = 0; i < num_cities; i++){
            cities[i].computePopProb();
        }


       while (clock < max_length) {
            if (!events.isEmpty()) {
                TransportEvent temp_event = events.first();
                while (temp_event.getDestTime() == clock) {
                    events.pollFirst();
                    City temp_city = temp_event.getDestCity();
                    temp_city.handle(temp_event);
                    if (!events.isEmpty()){
                        temp_event = events.first();
                    } else {
                        break;
                    }
                }
            }

            for (int i = 0; i < num_cities; i++){
                cities[i].hospitalTurn();
                cities[i].virusTurn();
                cities[i].transportTurn(clock, debug);

            }
            if (debug == 2){
                if (clock % 2 == 0){
                    printEvents(events, clock);
                }

            }
           clock ++;
        }
        System.out.print("end");
    }

    public static void printEvents(TreeSet events, int clock){
        System.out.println("===== Events print out at clock " + clock + " with size of " + events.size() + " =====");
        for (Object ev : events) {
            printEvent((TransportEvent)ev);
        }
    }

    public static void printEvent(TransportEvent ev){
        System.out.print("(time: " + ev.getDestTime());
        System.out.print(", daoda: " + ev.getDestCity().getName());
        System.out.print(", chufa: " + ev.getDeptCity().getName());
        System.out.print(", type: " + ev.getType());
        System.out.println(")");
    }
}
