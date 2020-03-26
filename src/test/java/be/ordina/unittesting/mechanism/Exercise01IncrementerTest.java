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
        public int compute(int value) {
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
        // TODO Tip: use AssertJ's Assertions.assertThat(...) to assert the result
    }

    @Test
    void given_99_should_result_in_100() {
        // TODO
    }

    @Test
    void given_minus_1_should_throw_an_illegal_argument_exception() {
        // TODO Tip 1: make use of Assertions.fail to properly have the test fail if the compute
        // doesn't throw an exception
        // TODO Tip 2: While catching the exception, verify that it's an instance of IllegalArgumentException
        // and that exception's message is "'-1' isn't allowed."
    }

    @Test
    void given_100_should_throw_an_illegal_argument_exception() {
        // TODO
    }

    @Test
    void given_13_should_throw_an_catastrophic_exception() {
        // TODO Tip: Don't forget to check whether the exception is an instance of CatastrophicException
    }

    @Test
    void given_0_until_99_besides_13_should_result_in_an_incremented_number() {
        // TODO Tip: use a for loop to run through the numbers
    }
}
