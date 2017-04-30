import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


public class VirusSim {

    public static City[] cities;
    public static TreeSet<TransportEvent> events = new TreeSet<>();
    public static Random rand = new Random(0);
    public static int num_cities = 0;

    public static void main(String[] args){
        int debug = 2;
        int clock = 0;
        int tick = 60; // in minutes
        int days = 1; // one month = four weeks = 28 days
        int[] compute_death= new int [57];
        int max_length=300;
        //int max_length = (int) (days * 24 * 60 / tick);
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
                if (id != -1 && id<num_cities) {
                    String[] temp = line.split(csvsplit);
                    String city_name = temp[1];
                    String state_name = temp[2];
                    System.out.println(city_name);
                    System.out.println(state_name);
                    System.out.println(temp[3].trim());
                    int total_population = Integer.parseInt(temp[3].trim());
                    double latitude = Double.parseDouble(temp[6].trim());
                    double longitude = Double.parseDouble(temp[7].trim());
                    int state_id = Integer.parseInt(temp[8].trim());
                    Population pop_tmp = new Population(total_population);
                    City city_tmp =new City(city_name, state_name, pop_tmp,latitude,longitude,id,state_id);
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
           for (int i = 0; i < num_cities; i++)
           {
               Population temp = cities[i].getPopulation();
               System.out.println("clock: "+clock +" "+cities[i].getName()+"   total pop: "+temp.getTotalPop()+"   dead: "+temp.getPopDead()+"   infected: "+temp.getPopInfected()+"   symptom: "+temp.getPopSymptom()+"   anitbody: "+temp.getPopAntibody()+"   no anti: "+temp.getPopWithoutAntibody()+"    Quara: "+temp.getPopQuarantine());
           }

            for (int i = 0; i < num_cities; i++){
                cities[i].hospitalTurn();
                cities[i].virusTurn();
                cities[i].transportTurn(clock, debug);
            }


//           if (clock == 100)
//           {
//               for (int i = 0; i < num_cities; i++)
//               {
//                   Population temp = cities[i].getPopulation();
//                   System.out.println(cities[i].getName()+" "+temp.getTotalPop());
//                   compute_death[cities[i].getstateId()] += temp.getPopDead();
//               }
//               for (int i = 0; i < 57; i++)
//               {
//                   System.out.printf("state: %d, death: %d\n", i, compute_death[i]);
//               }
//           }
           if (debug == 2){
                if (clock % 2 == 0){
                    printEvents(events, clock);
                }
           }
           clock ++;
        }
        System.out.print("end\n");
        for (int i = 0; i < num_cities; i++)
        {
            Population temp = cities[i].getPopulation();
            System.out.println(cities[i].getName()+" "+temp.getTotalPop());
            compute_death[cities[i].getstateId()] += temp.getPopDead();
        }
        JSONArray list = new JSONArray();
        for (int i = 0; i < 57; i++)
        {
            JSONObject obj = new JSONObject();
            obj.put("id", i);
            obj.put("dead", compute_death[i]);
            list.add(obj);
        }
        try(FileWriter file = new FileWriter("test.json")){
            file.write(list.toString());
            file.flush();
        }catch (IOException e){
            e.printStackTrace();
        }

//        for (int i = 0; i < 57; i++)
//           System.out.printf("state: %d, death: %d\n", i, compute_death[i]);

    }

public static void printEvents(TreeSet events, int clock){
//        System.out.println("===== Events print out at clock " + clock + " with size of " + events.size() + " =====");
//        for (Object ev : events) {
//            printEvent((TransportEvent)ev);
//        }
        }

public static void printEvent(TransportEvent ev){
//        System.out.print("(time: " + ev.getDestTime());
//        System.out.print(", daoda: " + ev.getDestCity().getName());
//        System.out.print(", chufa: " + ev.getDeptCity().getName());
//        System.out.print(", type: " + ev.getType());
//        System.out.println(")");
        }
        }
