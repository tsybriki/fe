package fe.core;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;

/**
 * User: macondo
 */
public class BinomialModelTest {

    @Test
    public void upmoveShouldBeSTimesU() {
        final BinomialModel b = new BinomialModel(2, 100);

        Assert.assertThat(b.getUpMove(), equalTo(model(2, 200)));
    }

    @Test
    public void downmoveShouldBeSDevidedByU() {
        final BinomialModel b = new BinomialModel(2, 100);

        Assert.assertThat(b.getDownMove(), equalTo(model(2, 50)));
    }

    private static BinomialModel model(double u, double s_0) {
        return new BinomialModel(u, s_0);
    }
}
