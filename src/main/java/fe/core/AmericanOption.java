package fe.core;

/**
 * User: macondo
 */
public class AmericanOption implements Value {
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

    public double valueOf(BinomialModel model, int depth) {
        if (depth == 0) {
            return Math.max(0, putCallMultiplier * (model.getS_0() - strike()));
        } else {
            return Math.max(putCallMultiplier * (model.getS_0() - strike), q * model.getUpMove().value(depth - 1, this) + (1 - q) * model.getDownMove().value(depth - 1, this)) / R_n;
        }

    }

    public double strike() {
        return strike;
    }

    public static AmericanOption call(double strike, double q, double R_n) {
        return new AmericanOption(strike, q, R_n, 1);
    }

    public static AmericanOption put(double strike, double q, double R_n) {
        return new AmericanOption(strike, q, R_n, -1);
    }
}
