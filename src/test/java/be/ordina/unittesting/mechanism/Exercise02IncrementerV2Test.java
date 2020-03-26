package be.ordina.unittesting.mechanism;

import be.ordina.unittesting.exceptions.CatastrophicException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Exercise02IncrementerV2Test {

    /**
     * Let's improve our Incrementer following  Test-Driven Development principles meaning that
     * we will first adapt our tests to the new expected behaviour before adapting the business logic.
     *
     * Our Incrementer has a little identity crisis... Instead of incrementing by one, it now
     * decrements by one! Besides it now decrementing it only accepts negative number up until -24.
     * And it made peace with number 13 but now started a fight with number -7.
     *
     * First start by copying your tests of Exercise01IncrementerTest.
     * After doing so alter the tests first to test the above behaviour.
     * Finally, alter the logic of the Incrementer implementation below and see if the tests work!
     */
    class Incrementer {
        int compute(int value) {
            if (value < 0 || value > 99) {
                throw new IllegalArgumentException(String.format("'%s' isn't allowed.", value));
            }
            if (value == 13)
            {
                throw new CatastrophicException("Bad luck!!");
            }
            return value+1;
        }
    }

    @Test
    void given_minus_1_should_result_in_minus_2() {
        // TODO
    }

    @Test
    void given_minus_24_should_result_in_minus_25() {
        // TODO
    }

    @Test
    void given_0_should_throw_an_illegal_argument_exception() {
        // TODO
    }

    @Test
    void given_minus_25_should_throw_an_illegal_argument_exception() {
        // TODO
    }

    @Test
    void given_minus_7_should_throw_an_catastrophic_exception() {
        // TODO
    }

    @Test
    void given_minus_1_until_minus_24_besides_minus_7_should_result_in_an_decremented_number() {
        // TODO
    }
}
