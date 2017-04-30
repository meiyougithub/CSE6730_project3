public class Population {
    public static double grand_sq_total_pop = 0.0;
    public static double grand_total_pop = 0.0;
    public static double grand_pop_antibody = 0.0;
    public static double grand_pop_withoutantibody = 0.0;
    public static double grand_pop_dead = 0.0;
    public static double grand_pop_symptom = 0.0;
    public static double grand_pop_infected = 0.0;
    public static double grand_pop_quarantine = 0.0;

    private double total_pop; // total population 
    private double pop_antibody; // healthy people with antibody
    private double pop_withoutantibody; // healthy people without antibody
    private double pop_dead; // dead people caused by virus
    private double pop_symptom; // infected people who has symptoms
    private double pop_infected; // infected people who don't display symptoms
    private double pop_quarantine; // quarantined pop_symptom in hospital
    
    private double antibody_ratio = Parameter.antibody_ratio;
    
    public Population(double total_pop) {
        this.total_pop = total_pop;
        this.pop_infected = 1.0;
        this.pop_antibody = total_pop * antibody_ratio;
        this.pop_withoutantibody = total_pop - pop_antibody - this.pop_infected;
        this.pop_dead = 0.0;
        this.pop_symptom = 0.0;

        grand_total_pop += total_pop;
    }

    public double getTotalPop() { return total_pop; }
    public double getPopAntibody() { return pop_antibody; }
    public double getPopWithoutAntibody() { return pop_withoutantibody; }
    public double getPopDead() { return pop_dead; }
    public double getPopSymptom() { return pop_symptom; }
    public double getPopInfected() { return pop_infected; }
    public double getPopQuarantine() { return pop_quarantine; }

    public void setTotalPop(double total_pop) { this.total_pop = total_pop; }
    public void setPopAntibody(double pop_antibody) { this.pop_antibody = pop_antibody; }
    public void setPopWithoutAntibody(double pop_withoutantibody) { this.pop_withoutantibody = pop_withoutantibody; }
    public void setPopDead(double pop_dead) { this.pop_dead = pop_dead; }
    public void setPopSymptom(double pop_symptom) { this.pop_symptom = pop_symptom; }
    public void setPopInfected(double pop_infected) { this.pop_infected = pop_infected; }
    public void setPopQuarantine(double pop_quarantine) { this.pop_quarantine = pop_quarantine; }
}
