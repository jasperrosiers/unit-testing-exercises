package be.ordina.unittesting.mechanism;

import be.ordina.unittesting.model.PortionSize;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * A few examples for some initial inspiration.
 */
class ExampleCoffeeBeanContainerTest {

    @Test
    void given_coffee_container_with_volume_1_should_result_in_successful_creation() {
        // when
        CoffeeBeanContainer coffeeBeanContainer = new CoffeeBeanContainer(1);

        // then
        Assertions.assertThat(coffeeBeanContainer.getTotalVolume()).isEqualTo(1);
        Assertions.assertThat(coffeeBeanContainer.getCurrentVolume()).isEqualTo(1);
    }

    @Test
    void given_coffee_container_with_zero_volume_should_throw_illegal_argument_exception() {
        assertThatThrownBy(() -> new CoffeeBeanContainer(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Container's value must be greater then 0");
    }

    @Test
    void get_portion_with_sufficient_volume_should_result_in_portion_taken_successfully_and_reduce_total_volume() {
        // given
        final CoffeeBeanContainer coffeeBeanContainer = new CoffeeBeanContainer(2);

        // when
        boolean portionTakenSuccessfully = coffeeBeanContainer.getPortion(PortionSize.MEDIUM);

        // then
        Assertions.assertThat(portionTakenSuccessfully).isTrue();
        Assertions.assertThat(coffeeBeanContainer.getTotalVolume()).isEqualTo(2);
        Assertions.assertThat(coffeeBeanContainer.getCurrentVolume()).isEqualTo(0);
    }

    @Test
    void get_portion_with_insufficient_volume_should_result_in_portion_taken_unsuccessfully_and_not_reduce_total_volume() {
        // given
        final CoffeeBeanContainer coffeeBeanContainer = new CoffeeBeanContainer(1);

        // when
        boolean portionTakenSuccessfully = coffeeBeanContainer.getPortion(PortionSize.MEDIUM);

        // then
        Assertions.assertThat(portionTakenSuccessfully).isFalse();
        Assertions.assertThat(coffeeBeanContainer.getTotalVolume()).isEqualTo(1);
        Assertions.assertThat(coffeeBeanContainer.getCurrentVolume()).isEqualTo(1);
    }
}