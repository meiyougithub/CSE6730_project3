public class Hospital {
    private static int max_vaccine = Parameter.max_vaccine;
    private static int max_patient = Parameter.max_patient;
    private static int max_quarantine = Parameter.max_quarantine;

    private static double cure_rate = Parameter.cure_rate;

    // public Hospital(int max_vaccine, int max_patient, int max_quarantine, double cure_rate) {
    //     this.max_vaccine = max_vaccine;
    //     this.max_patient = max_patient;
    //     this.max_quarantine = max_quarantine;
    //     this.cure_rate = cure_rate;
    // }

    public static void quarantine(Population population) {
        int pop_infected = population.getPopInfected();
        int pop_quarantine = population.getPopQuarantine();

        if (pop_quarantine < max_quarantine) {
            int diff = max_quarantine - pop_quarantine;
            int tmp_quarantine = (pop_infected < diff)? pop_infected : diff;
            population.setPopQuarantine( population.getPopQuarantine() + tmp_quarantine );
            population.setPopInfected( population.getPopInfected() - tmp_quarantine );
        }
    }


    public static void vaccinate(Population population) {
        int pop_withoutantibody = population.getPopWithoutAntibody();
        int pop_vaccinated = (pop_withoutantibody < max_vaccine)? pop_withoutantibody : max_vaccine;
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
