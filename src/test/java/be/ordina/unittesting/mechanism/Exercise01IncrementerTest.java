package be.ordina.unittesting.mechanism;

import be.ordina.unittesting.exceptions.CatastrophicException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class Exercise01IncrementerTest {

    /**
     * Our Incrementor class is a simple class that has the capability to add one to a given number.
     * Due to technical limitation it is unable to work with negative numbers and number over 99.
     *
     * Also, it really doesn't like the number 13 for some reason.
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
    void given_0_should_result_in_1() {
        // arrange
        Incrementer incrementer = new Incrementer();

        // act
        int result = incrementer.compute(0);

        // assert
        Assertions.assertThat(result).isEqualTo(1);
    }

    @Test
    void given_99_should_result_in_100() {
        // arrange
        Incrementer incrementer = new Incrementer();

        // act
        int result = incrementer.compute(99);

        // assert
        Assertions.assertThat(result).isEqualTo(100);
    }

    @Test
    void given_minus_1_should_throw_an_illegal_argument_exception() {
        // arrange
        Incrementer incrementer = new Incrementer();

        try {
            // act
            incrementer.compute(-1);
            Assertions.fail("Should fail!");
        } catch (Exception ia) {
            // assert
            Assertions.assertThat(ia)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("'-1' isn't allowed.");
        }
    }

    @Test
    void given_100_should_throw_an_illegal_argument_exception() {
        // arrange
        Incrementer incrementer = new Incrementer();

        try {
            // act
            incrementer.compute(100);
            Assertions.fail("Should fail!");
        } catch (Exception ia) {
            // assert
            Assertions.assertThat(ia)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("'100' isn't allowed.");
        }
    }

    @Test
    void given_13_should_throw_an_catastrophic_exception() {
        // arrange
        Incrementer incrementer = new Incrementer();

        try {
            // act
            incrementer.compute(13);
            Assertions.fail("Should fail!");
        } catch (Exception ia) {
            // assert
            Assertions.assertThat(ia)
                    .isInstanceOf(CatastrophicException.class)
                    .hasMessage("Bad luck!!");
        }
    }

    @Test
    void given_0_until_99_besides_13_should_result_in_an_incremented_number() {
        // arrange
        Incrementer incrementer = new Incrementer();

        // act
        for (int i=0; i<100; i++) {
            if (i == 13) {
                continue;
            }
            int result = incrementer.compute(i);
            // assert
            Assertions.assertThat(result).isEqualTo(i+1);
        }
    }
}
