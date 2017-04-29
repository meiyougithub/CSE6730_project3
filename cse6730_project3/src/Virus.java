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
        population.setPopInfected((int) (population.getPopInfected() + population.getPopInfected() * rate_spread));
        population.setPopWithoutAntibody( (int) (poulation.getPopWithoutAntibody() - population.getPopInfected() * rate_spread));
        
    }
    
    public static void computeMorbidity(Population population) {
        population.setPopSymptom( (int) ( population.getPopSymptom() + (population.getPopInfected() + population.getPopQuarantine()) * rate_morbidity ));
        population.setPopInfected( (int) (population.getPopInfected() - population.getPopInfected() * rate_morbidity ));
        population.setPopQuarantine( (int) (population.getPopQuarantine() - population.getPopQuarantine() * rate_morbidity ));
    }
    
    public static void computeLethality(Population population) {
        population.setPopDead( (int) (population.getPopDead() + population.getPopSymptom() * rate_lethality ));
        population.setTotalPop( (int) (population.getTotalPop() - population.getPopSymptom() * rate_lethality ));
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
