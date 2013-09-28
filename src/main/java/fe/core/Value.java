package fe.core;

/**
 * User: macondo
 */
public interface Value {
    double valueOf(BinomialModel model, int depth);

    double strike();
}
