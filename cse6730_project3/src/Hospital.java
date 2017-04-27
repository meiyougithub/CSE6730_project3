public class Hospital {
    private int max_vaccine;
    private int max_patient;
    private int max_quarantine;

    private double cure_rate;

    public Hospital(int max_vaccine, int max_patient, double cure_rate) {
        this.max_vaccine = max_vaccine;
        this.max_patient = max_patient;
        this.cure_rate = cure_rate;
    }

    public static void quarantine(Population population) {
        int pop_infected = population.getPopInfected();
        int pop_quarantine = (pop_infected < max_quarantine)? pop_infected : max_quarantine
        population.setPopQuarantine( population.getPopQuarantine() + pop_quarantine );
        population.setPopInfected( population.getPopInfected() - pop_quarantine );
    }

    public static void vaccinate(Population population) {
        int pop_withoutantibody = population.getPopWithoutAntibody();
        int pop_vaccinated = (pop_withoutantibody < max_vaccine)? pop_withoutantibody : max_vaccine
        population.setPopAntibody( population.getPopAntibody() + pop_vaccinated );
        population.setPopWithoutAntibody( population.getPopWithoutAntibody() - pop_vaccinated );
    }

    public static void cure(Population population) {
        int pop_symptom = population.getPopSymptom();
        int max_cured = (pop_symptom < max_patient)? pop_symptom : max_patient;
        population.setPopAntibody( population.getPopAntibody() + (int) (max_cured * max_cured) );
        population.setPopSymptom( population.getPopSymptom() - (int) (max_cured * max_cured) );
    }
}