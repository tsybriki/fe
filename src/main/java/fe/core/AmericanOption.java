package fe.core;

/**
 * User: macondo
 */
public class AmericanOption implements Value<Double> {
    private double strike;
    private double q;
    private double R_n;
    private int putCallMultiplier;

    private AmericanOption(double strike, double q, double R_n, int putCallMultiplier) {
        this.strike = strike;
        this.q = q;
        this.R_n = R_n;
        this.putCallMultiplier = putCallMultiplier;
    }

    public Double valueOf(BinomialModel model, int depth) {
        if (depth == 0) {
            return Math.max(0, putCallMultiplier * (model.getS_0() - strike));
        } else {
            double earlyExcerciseValue = putCallMultiplier * (model.getS_0() - strike);
            double holdValue = (q * model.getUpMove().accept(depth - 1, this) + (1 - q) * model.getDownMove().accept(depth - 1, this)) / R_n;

            System.out.printf("Depth: %d; diff: %f %n", depth, holdValue - earlyExcerciseValue);

            return Math.max(earlyExcerciseValue, holdValue);
        }

    }

    public static AmericanOption call(double strike, double q, double R_n) {
        return new AmericanOption(strike, q, R_n, 1);
    }

    public static AmericanOption put(double strike, double q, double R_n) {
        return new AmericanOption(strike, q, R_n, -1);
    }
}
