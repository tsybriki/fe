package fe.core;

/**
 * User: macondo
 */
public class TransformIntoFutureLatice implements Value<BinomialModel> {
    private double q;

    public TransformIntoFutureLatice(double q) {
        this.q = q;
    }

    public BinomialModel valueOf(BinomialModel model, int depth) {
        if (depth == 0) {
            return model;
        } else {
            final BinomialModel upMove = valueOf(model.getUpMove(), depth - 1);
            final BinomialModel downMove = valueOf(model.getDownMove(), depth - 1);
            return new BinomialModel(q * upMove.getS_0() + (1-q) * downMove.getS_0(), upMove, downMove);
        }
    }
}
