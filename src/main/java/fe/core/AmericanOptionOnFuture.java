package fe.core;

/**
 * User: macondo
 */
public class AmericanOptionOnFuture implements Value<Double> {
    private int termsBeforeExpiry;
    private int strike;

    public Double valueOf(BinomialModel model, int depth) {
        if (depth == 0) {

        }
        return null;
    }
}
