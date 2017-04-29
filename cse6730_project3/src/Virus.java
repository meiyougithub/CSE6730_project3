public class Virus {
    private static double rate_spread = Parameter.rate_spread;
    private static double rate_morbidity = Parameter.rate_morbidity;
    private static double rate_lethality = Parameter.rate_lethality;

    // public Virus(double rate_spread, double rate_morbidity, double rate_lethality) {
    //     this.rate_spread = rate_spread;
    //     this.rate_morbidity = rate_morbidity;
    //     this.rate_lethality = rate_lethality;
    // }
    
    public static void computeInfected(Population population) {
        int infected = (int) (population.getPopInfected() * rate_spread);
        if (infected > population.getPopWithoutAntibody()){
            infected = population.getPopWithoutAntibody();
        }
        population.setPopInfected(population.getPopInfected() + infected);
        population.setPopWithoutAntibody( population.getPopWithoutAntibody() - infected);
        
    }
    
    public static void computeMorbidity(Population population) {
        int morbidity_1 = (int) (population.getPopInfected() * rate_morbidity);
        int morbidity_2 = (int) (population.getPopQuarantine() * rate_morbidity);
        
        population.setPopSymptom( population.getPopSymptom() + morbidity_1 + morbidity_2 );
        population.setPopInfected( population.getPopInfected() - morbidity_1 );
        population.setPopQuarantine( population.getPopQuarantine() - morbidity_2 );
    }
    
    public static void computeLethality(Population population) {
        int dead = (int) (population.getPopSymptom() * rate_lethality);
        if (dead > population.getTotalPop()-population.getPopAntibody()){
            dead = population.getTotalPop()-population.getPopAntibody();
        }
        population.setPopDead( population.getPopDead() + dead );
        population.setTotalPop( population.getTotalPop() - dead );
        Population.grand_total_pop -= dead;
        Population.grand_pop_dead += dead;
    }

    public static int quarantineToCure(Population population){
        return (int) (population.getPopQuarantine() * rate_morbidity);
    }

    public double getRateLethality() { return rate_lethality; }
    public double getRateMorbidity() { return rate_morbidity; }
    public double geRateSpread() { return rate_spread; }
    public void setRateLethality(double rate_lethality) { this.rate_lethality = rate_lethality; }
    public void setRateMorbidity(double rate_morbidity) { this.rate_morbidity = rate_morbidity; }
    public void setRateSpread(double rate_spread) { this.rate_spread = rate_spread; }

}
