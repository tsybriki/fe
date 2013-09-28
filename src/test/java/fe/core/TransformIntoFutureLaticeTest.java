package fe.core;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * User: macondo
 */
public class TransformIntoFutureLaticeTest {

    @Test
    public void shouldApplyExpectaionsToEndPrices() {
        final BinomialModel source = new BinomialModel(2, 100);
        final TransformIntoFutureLatice transform = new TransformIntoFutureLatice(0.5);

        final BinomialModel target = source.accept(1, transform);

        Assert.assertThat(target.getS_0(), CoreMatchers.equalTo(125d));
    }

    @Test
    public void shouldPriceAmericalCallOnFutures() {
        BinomialModel stockModel = new BinomialModel(1.03949, 100);
        TransformIntoFutureLatice futures = new TransformIntoFutureLatice(0.49247);

        final BinomialModel futuresModel = stockModel.accept(15, futures);

        AmericanOption call = AmericanOption.call(110, 0.49247, 1.000333);

        System.out.println(futuresModel.accept(10, call));
    }
}
