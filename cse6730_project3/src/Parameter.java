public class Parameter {
    public static int air_passenger_mean;
    public static int train_passenger_mean;
    public static int ship_passenger_mean;
    public static double air_speed_mean;
    public static double train_speed_mean;
    public static double ship_speed_mean;

    public static double rate_spread;
    public static double rate_morbidity;
    public static double rate_lethality;

    public static double antibody_ratio;

    public static int max_vaccine;
    public static int max_patient;
    public static int max_quarantine;
    public static double cure_rate;

    public static int threshold = 20;
    public static int countdown = 7 * 24;

    public static int max_air = 60, max_train = 40, max_ship = 20;
    public static double pop_scale = 3000.0;

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


    public static void printMatrix(double[][] mat){
        int n = mat.length;
        int m = mat[0].length;
        for (int i = 0; i < n ; i++){
            for (int j = 0; j < m; j++)
                System.out.print(mat[i][j]+" ");
            System.out.println();
        }
    }

    public static void printMatrix(double[] mat){
        int n = mat.length;
        for (int i = 0; i < n ; i++){
            System.out.print(mat[i]+" ");
            System.out.println();
        }
    }

    public static int naiveSearch(double prob, double[] scores) {
        int max_index;
        max_index = scores.length;
        for (int i = 0; i < max_index; i++) {
            if (prob <= scores[i]) return i;
        }
        return max_index;
    }
}