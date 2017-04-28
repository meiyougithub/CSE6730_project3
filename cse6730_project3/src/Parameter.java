public class Parameter {
    final static public int air_passenger_mean;
    final static public int train_passenger_mean;
    final static public int ship_passenger_mean;
    final static public double air_speed_mean;
    final static public double train_speed_mean;
    final static public double ship_speed_mean;

    final static public double rate_spread;
    final static public double rate_morbidity;
    final static public double rate_lethality;

    final static public double antibody_ratio;

    final static public int max_vaccine;
    final static public int max_patient;
    final static public int max_quarantine;
    final static public double cure_rate;

    public Parameter(int apm, int tpm, int spm, double asm, double tsm, double ssm, double rs, double rm, double rl, double ar, int mv, int mp, int mq, double cr) {
        air_passenger_mean = apm;
        train_passenger_mean = tpm;
        ship_passenger_mean = spm;

        air_speed_mean = asm;
        train_speed_mean = tsm;
        ship_speed_mean = ssm;

        rate_spread = rs;
        rate_morbidity = rm;
        rate_lethality = rl;

        antibody_ratio = ar;

        max_vaccine = mv;
        max_patient = mp;
        max_quarantine = mq;

        cure_rate = cr;
    }
}