package fe.core;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

/**
 * User: macondo
 */
public class BinomialModel {
    private double u;
    private double s_0;
    private Supplier<BinomialModel> upMove;
    private Supplier<BinomialModel> downMove;

    public BinomialModel(double u, double s_0) {
        this.u = u;
        this.s_0 = s_0;

        upMove = Suppliers.memoize(new Supplier<BinomialModel>() {
            public BinomialModel get() {
                return new BinomialModel(BinomialModel.this.u, BinomialModel.this.s_0 * BinomialModel.this.u);
            }
        });

        downMove = Suppliers.memoize(new Supplier<BinomialModel>() {
            public BinomialModel get() {
                return new BinomialModel(BinomialModel.this.u, BinomialModel.this.s_0 / BinomialModel.this.u);
            }
        });
    }

    public BinomialModel(double s_0, BinomialModel upMove, BinomialModel downMove) {
        this.s_0 = s_0;
        this.upMove = Suppliers.ofInstance(upMove);
        this.downMove = Suppliers.ofInstance(downMove);
    }

    public double getS_0() {
        return s_0;
    }

    public BinomialModel getUpMove() {
        return upMove.get();
    }

    public BinomialModel getDownMove() {
        return downMove.get();
    }

    public <T> T accept(int depth, Value<T> valueFn) {
            return valueFn.valueOf(this, depth);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BinomialModel that = (BinomialModel) o;

        if (Double.compare(that.s_0, s_0) != 0) return false;
        if (Double.compare(that.u, u) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(u);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(s_0);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
