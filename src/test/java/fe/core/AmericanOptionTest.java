package fe.core;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * User: macondo
 */
public class AmericanOptionTest {

    private BinomialModel model = new BinomialModel(2, 100);

    @Test
    public void shouldReturnSpotMinusStrikeIfStrikeIsLessThanSpotForCall() {
        AmericanOption co = AmericanOption.call(80, 0.5, 1);

        Assert.assertThat(model.accept(0, co), CoreMatchers.equalTo(20d));
    }

    @Test
    public void shouldReturnZeroIfStrikeIsGreaterThanSpotForCall() {
        AmericanOption co = AmericanOption.call(120, 0.5, 1);

        Assert.assertThat(model.accept(0, co), CoreMatchers.equalTo(0d));
    }

    @Test
    public void shouldBeAbleToValueOneStepModelForCall() {
        AmericanOption co = AmericanOption.call(100, 0.5, 1);

        Assert.assertThat(model.accept(1, co), CoreMatchers.equalTo(50d));
    }

    @Test
    public void value15PeriodBinomialModelAmCallOption() {
        BinomialModel pricingModel = new BinomialModel(1.03949, 100);
        AmericanOption call = AmericanOption.call(110, 0.49247, 1.000333);

        System.out.println(pricingModel.accept(15, call));
    }

    @Test
    public void shouldBeAbleToValueAmericanPut() {
        BinomialModel pricingModel = new BinomialModel(1.03949, 100);
        AmericanOption put = AmericanOption.put(110, 0.49247, 1.000333);

        Assert.assertThat(pricingModel.accept(15, put), Matchers.closeTo(12.36, 0.005));
    }
}
