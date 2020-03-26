package be.ordina.unittesting.mechanism;

import be.ordina.unittesting.model.Portion;
import be.ordina.unittesting.model.PortionSize;
import be.ordina.unittesting.mechanism.interfaces.Container;
import be.ordina.unittesting.model.PortionType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Here we have a ExpressoMachine with a coffee and water container.
 * Basically, if the machine has enough coffee (beans) and water, it can make coffee.
 */
@ExtendWith(MockitoExtension.class)
class Exercise03SuperbCoffeeMachineTest {
    @Mock
    private Container coffeeBeanContainer;

    @Mock
    private Container waterContainer;

    private SuperbCoffeeMachine machine;

    @BeforeEach
    void setUp() {
        machine = new SuperbCoffeeMachine(coffeeBeanContainer, waterContainer);
    }

    @AfterEach
    void tearDown() {
        Mockito.verifyNoMoreInteractions(coffeeBeanContainer, waterContainer);
    }

    @Test
    void given_insufficient_coffee_beans_make_coffee_should_return_no_coffee_portion() {
        // given
        final PortionSize portionSize = PortionSize.SMALL;
        Mockito.when(this.coffeeBeanContainer.getPortion(portionSize)).thenReturn(false);

        // when
        Optional<Portion> coffeePortion = machine.makeCoffee(portionSize);

        // then
        assertThat(coffeePortion).isEmpty();

        Mockito.verify(coffeeBeanContainer).getPortion(portionSize);
    }

    @Test
    void given_insufficient_water_make_coffee_should_return_no_coffee_portion() {
        // given
        final PortionSize portionSize = PortionSize.SMALL;
        Mockito.when(this.coffeeBeanContainer.getPortion(portionSize)).thenReturn(true);
        Mockito.when(this.waterContainer.getPortion(portionSize)).thenReturn(false);

        // when
        Optional<Portion> coffeePortion = machine.makeCoffee(portionSize);

        // then
        assertThat(coffeePortion).isEmpty();

        Mockito.verify(coffeeBeanContainer).getPortion(portionSize);
        Mockito.verify(waterContainer).getPortion(portionSize);
    }

    @Test
    void given_sufficient_coffee_beans_and_water_make_coffee_should_return_coffee_portion() {
        // given
        final PortionSize portionSize = PortionSize.SMALL;
        Mockito.when(this.coffeeBeanContainer.getPortion(portionSize)).thenReturn(true);
        Mockito.when(this.waterContainer.getPortion(portionSize)).thenReturn(true);

        // when
        Optional<Portion> coffeePortion = machine.makeCoffee(portionSize);

        // then
        assertThat(coffeePortion).isNotEmpty();
        assertThat(coffeePortion.get().getPortionType()).isEqualTo(PortionType.COFFEE);
        assertThat(coffeePortion.get().getPortionSize()).isEqualTo(portionSize);

        Mockito.verify(coffeeBeanContainer).getPortion(portionSize);
        Mockito.verify(waterContainer).getPortion(portionSize);
    }
}
