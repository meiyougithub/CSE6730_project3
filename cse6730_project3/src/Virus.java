public class Virus {
    private double rate_spread = Parameter.rate_spread;
    private double rate_morbidity = Parameter.rate_morbidity;
    private double rate_lethality = Parameter.rate_lethality;

    public Virus(double rate_spread, double rate_morbidity, double rate_lethality) {
        this.rate_spread = rate_spread;
        this.rate_morbidity = rate_morbidity;
        this.rate_lethality = rate_lethality;
    }
    
    public static void computeInfected(Population population) {
        population.setPopInfected( (int) (population.getPopWithoutAntibody() * rate_spread) ); 
    }
    
    public static void computeMorbidity(Population population) {
        population.setPopSymptom( (int) (population.getPopInfected() * rate_morbidity) );
    }
    
    public static void computeLethality(Population population) {
        population.setPopDead( (int) (population.getPopSymptom() * rate_lethality) );
    }

    public double getRateLethality() { return rate_lethality; }
    public double getRateMorbidity() { return rate_morbidity; }
    public double geRateSpread() { return rate_spread; }
    public void setRateLethality(double rate_lethality) { this.rate_lethality = rate_lethality; }
    public void setRateMorbidity(double rate_morbidity) { this.rate_morbidity = rate_morbidity; }
    public void setRateSpread(double rate_spread) { this.rate_spread = rate_spread; }

}
