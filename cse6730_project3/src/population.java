/**
 * Created by WYF on 4/27/2017.
 */
public class Population {
    private int total_pop; // total population 
    private int pop_antibody; // healthy people with antibody
    private int pop_withoutantibody; // healthy people without antibody
    private int pop_dead; // dead people caused by virus
    private int pop_symptom; // infected people who has symptoms
    private int pop_infected; // infected people who don't display symptoms
    private int pop_quarantine; // quarantined pop_symptom in hospital
    private double antibody_ratio = 0.001;
    
    public population(int total_pop){
        total_pop = total_pop;
        pop_antibody = (int) total_pop * antibody_ratio;
        pop_withoutantibody = total_pop - pop_antibody;
        pop_dead = 0;
        pop_symptom = 0;
        pop_infected = 0;
    }
    
    public population(int total_pop,int pop_antibody, int pop_withoutantibody, int pop_dead, int pop_symptom, int pop_infected){
        total_pop = total_pop;
        pop_antibody = pop_antibody;
        pop_withoutantibody = pop_withoutantibody;
        pop_dead = pop_dead;
        pop_symptom = pop_symptom;
        pop_infected = pop_infected;
    }

    public int getTotalPop() {return total_pop;}
    public int getPopAntibody() {return pop_antibody;}
    public int getPopWithoutAntibody() {return pop_withoutantibody;}
    public int getPopDead() {return pop_dead;}
    public int getPopInfected() {return pop_infected;}
    public int getPopSymptom() {return pop_symptom;}
    public int getPopQuarantine() {return pop_quarantine;}
    public void setPopAntibody(int pop_antibody) {this.pop_antibody = pop_antibody;}
    public void setPopDead(int pop_dead) {this.pop_dead = pop_dead;}
    public void setPopInfected(int pop_infected) {this.pop_infected = pop_infected;}
    public void setPopSymptom(int pop_symptom) {this.pop_symptom = pop_symptom;}
    public void setPopWithoutAntibody(int pop_withoutantibody) {this.pop_withoutantibody = pop_withoutantibody;}
    public void setTotalPop(int total_pop) {this.total_pop = total_pop;}
    public void setPopQuarantine(int pop_quarantine) {this.pop_quarantine = pop_quarantine;}
}
