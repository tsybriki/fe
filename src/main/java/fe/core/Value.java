package fe.core;

/**
 * User: macondo
 */
public interface Value<T> {
    T valueOf(BinomialModel model, int depth);
}
