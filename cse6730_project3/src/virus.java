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

    public double getV_rate_lethality() {return v_rate_lethality;}
    public double getV_rate_morbidity() {return v_rate_morbidity;}
    public double getV_rate_spread() {return v_rate_spread;}
    public void setV_rate_lethality(double v_rate_lethality) {this.v_rate_lethality = v_rate_lethality;}
    public void setV_rate_morbidity(double v_rate_morbidity) {this.v_rate_morbidity = v_rate_morbidity;}
    public void setV_rate_spread(double v_rate_spread) {this.v_rate_spread = v_rate_spread;}

}
