/**
 * Created by WYF on 4/27/2017.
 */
public class population {
    private int p_total_pop;
    private int p_pop_antibody;
    private int p_pop_withoutantibody;
    private int p_pop_dead;
    private int p_pop_symptom;
    private int p_pop_infected;
    public population(int total_pop,int pop_antibody, int pop_withoutantibody, int pop_dead, int pop_symptom, int pop_infected){
        p_total_pop=total_pop;
        p_pop_antibody=pop_antibody;
        p_pop_withoutantibody=pop_withoutantibody;
        p_pop_dead=pop_dead;
        p_pop_symptom=pop_symptom;
        p_pop_infected=pop_infected;
    }

    public int getP_total_pop() {return p_total_pop;}
    public int getP_pop_antibody() {return p_pop_antibody;}
    public int getP_pop_withoutantibody() {return p_pop_withoutantibody;}
    public int getP_pop_dead() {return p_pop_dead;}
    public int getP_pop_infected() {return p_pop_infected;}
    public int getP_pop_symptom() {return p_pop_symptom;}
    public void setP_pop_antibody(int p_pop_antibody) {this.p_pop_antibody = p_pop_antibody;}
    public void setP_pop_dead(int p_pop_dead) {this.p_pop_dead = p_pop_dead;}
    public void setP_pop_infected(int p_pop_infected) {this.p_pop_infected = p_pop_infected;}
    public void setP_pop_symptom(int p_pop_symptom) {this.p_pop_symptom = p_pop_symptom;}
    public void setP_pop_withoutantibody(int p_pop_withoutantibody) {this.p_pop_withoutantibody = p_pop_withoutantibody;}
    public void setP_total_pop(int p_total_pop) {this.p_total_pop = p_total_pop;}
}

