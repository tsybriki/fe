package fe.core;

/**
 * User: macondo
 */
public class CallOption implements Value {
    private double strike;
    private double q;
    private double R_n;

    public CallOption(double strike, double q, double R_n) {
        this.strike = strike;
        this.q = q;
        this.R_n = R_n;
    }

    public double valueOf(BinomialModel model, int depth) {
        if (depth == 0) {
            return Math.max(0, model.getS_0() - strike());
        } else {
            return Math.max(model.getS_0() - strike, q * model.getUpMove().value(depth - 1, this) + (1 - q) * model.getDownMove().value(depth - 1, this)) / R_n;
        }

    }

    public double strike() {
        return strike;
    }
}
