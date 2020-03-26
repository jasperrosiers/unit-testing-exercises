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
            if (value < -24 || value > -1) {
                throw new IllegalArgumentException(String.format("'%s' isn't allowed.", value));
            }
            if (value == -7)
            {
                throw new CatastrophicException("Bad luck!!");
            }
            return value-1;
        }
    }

    @Test
    void given_minus_1_should_result_in_minus_2() {
        // arrange
        Incrementer incrementer = new Incrementer();

        // act
        int result = incrementer.compute(-1);

        // assert
        Assertions.assertThat(result).isEqualTo(-2);
    }

    @Test
    void given_minus_24_should_result_in_minus_25() {
        // arrange
        Incrementer incrementer = new Incrementer();

        // act
        int result = incrementer.compute(-24);

        // assert
        Assertions.assertThat(result).isEqualTo(-25);
    }

    @Test
    void given_0_should_throw_an_illegal_argument_exception() {
        // arrange
        Incrementer incrementer = new Incrementer();

        try {
            // act
            incrementer.compute(0);
            Assertions.fail("Should fail!");
        } catch (Exception ia) {
            // assert
            Assertions.assertThat(ia)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("'0' isn't allowed.");
        }
    }

    @Test
    void given_minus_25_should_throw_an_illegal_argument_exception() {
        // arrange
        Incrementer incrementer = new Incrementer();

        try {
            // act
            incrementer.compute(-25);
            Assertions.fail("Should fail!");
        } catch (Exception ia) {
            // assert
            Assertions.assertThat(ia)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("'-25' isn't allowed.");
        }
    }

    @Test
    void given_minus_7_should_throw_an_catastrophic_exception() {
        // arrange
        Incrementer incrementer = new Incrementer();

        try {
            // act
            incrementer.compute(-7);
            Assertions.fail("Should fail!");
        } catch (Exception ia) {
            // assert
            Assertions.assertThat(ia)
                    .isInstanceOf(CatastrophicException.class)
                    .hasMessage("Bad luck!!");
        }
    }

    @Test
    void given_minus_1_until_minus_24_besides_minus_7_should_result_in_an_decremented_number() {
        // arrange
        Incrementer incrementer = new Incrementer();

        // act
        for (int i=-1; i>-25; i--) {
            if (i == -7) {
                continue;
            }
            int result = incrementer.compute(i);
            // assert
            Assertions.assertThat(result).isEqualTo(i-1);
        }
    }
}
