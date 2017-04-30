public class Hospital {
    public static boolean research = false;
    private static double max_vaccine = Parameter.max_vaccine;
    private static double max_patient = Parameter.max_patient;
    private static double max_quarantine = Parameter.max_quarantine;

    private static double cure_rate = Parameter.cure_rate;

    // public Hospital(double max_vaccine, double max_patient, double max_quarantine, double cure_rate) {
    //     this.max_vaccine = max_vaccine;
    //     this.max_patient = max_patient;
    //     this.max_quarantine = max_quarantine;
    //     this.cure_rate = cure_rate;
    // }

    public static void quarantine(Population population) {
        double pop_infected = population.getPopInfected();
        double pop_quarantine = population.getPopQuarantine();

        if (pop_quarantine < max_quarantine) {
            double diff = max_quarantine - pop_quarantine;
            double tmp_quarantine = (pop_infected < diff)? pop_infected : diff;
            population.setPopQuarantine( population.getPopQuarantine() + tmp_quarantine );
            population.setPopInfected( population.getPopInfected() - tmp_quarantine );

            Population.grand_pop_quarantine += tmp_quarantine;
            Population.grand_pop_infected -= tmp_quarantine;
        }
    }


    public static void vaccinate(Population population) {
        double pop_withoutantibody = population.getPopWithoutAntibody();
        double pop_vaccinated = (pop_withoutantibody < max_vaccine)? pop_withoutantibody : max_vaccine;
        population.setPopAntibody( population.getPopAntibody() + pop_vaccinated );
        population.setPopWithoutAntibody( population.getPopWithoutAntibody() - pop_vaccinated );

        Population.grand_pop_antibody += pop_vaccinated;
        Population.grand_pop_withoutantibody -= pop_vaccinated;
    }

    public static void cure(Population population) {
        double pop_symptom = population.getPopSymptom();
        double max_cured = (pop_symptom < max_patient)? pop_symptom : max_patient;
        population.setPopAntibody( population.getPopAntibody() + max_cured * cure_rate);
        population.setPopSymptom( population.getPopSymptom() - max_cured * cure_rate);

        Population.grand_pop_antibody += max_cured * cure_rate;
        Population.grand_pop_symptom -= max_cured * cure_rate;
    }
}
