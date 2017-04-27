/**
 * Created by WYF on 4/27/2017.
 */
public class virus {
    private double v_rate_spread;
    private double v_rate_morbidity;
    private double v_rate_lethality;
    public virus(double rate_spread, double rate_morbidity, double rate_lethality){
        v_rate_spread=rate_spread;
        v_rate_morbidity=rate_morbidity;
        v_rate_lethality=rate_lethality;
    }
    
    public void computeInfected(Population population){
        population.setPopInfected( poulation.getTotalPop() * v_rate_spread ); 
    }
    
    public void computeMorbidity(Population population){
        population.setPopSymptom( poulation.getPopInfected() * v_rate_morbidity );
    }
    
    public void computeLethality(Population population){
        population.setPopDead( poulation.getPopSymptom() * v_rate_lethality );
    }

    public double getRateLethality() {return v_rate_lethality;}
    public double getRateMorbidity() {return v_rate_morbidity;}
    public double geRateSpread() {return v_rate_spread;}
    public void setRateLethality(double v_rate_lethality) {this.v_rate_lethality = v_rate_lethality;}
    public void setRateMorbidity(double v_rate_morbidity) {this.v_rate_morbidity = v_rate_morbidity;}
    public void setRateSpread(double v_rate_spread) {this.v_rate_spread = v_rate_spread;}

}
