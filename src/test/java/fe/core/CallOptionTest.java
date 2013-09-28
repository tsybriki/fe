package fe.core;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

/**
 * User: macondo
 */
public class CallOptionTest {

    private BinomialModel model = new BinomialModel(2, 100);

    @Test
    public void shouldReturnSpotMinusStrikeIfStrikeIsLessThanSpot() {
        CallOption co = new CallOption(80, 0.5, 1);

        Assert.assertThat(model.value(0, co), CoreMatchers.equalTo(20d));
    }

    @Test
    public void shouldReturnZeroIfStrikeIsGreaterThanSpot() {
        CallOption co = new CallOption(120, 0.5, 1);

        Assert.assertThat(model.value(0, co), CoreMatchers.equalTo(0d));
    }

    @Test
    public void shouldBeAbleToValueOneStepModel() {
        CallOption co = new CallOption(100, 0.5, 1);

        Assert.assertThat(model.value(1, co), CoreMatchers.equalTo(50d));
    }

    @Test
    public void value10PeriodBinomialModelAmCallOption() {
        BinomialModel pricingModel = new BinomialModel(1.03949, 100);
        CallOption call = new CallOption(110, 0.49247, 1.000333);

        System.out.println(pricingModel.value(15, call));
    }
}
