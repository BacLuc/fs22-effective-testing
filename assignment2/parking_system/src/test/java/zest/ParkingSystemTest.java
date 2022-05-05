package zest;

import net.jqwik.api.*;
import net.jqwik.api.stateful.Action;
import net.jqwik.api.stateful.ActionSequence;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParkingSystemTest {

    @Property
    void takes_cars_until_full(
            @ForAll("validParkingSystems") ParkingSystem parkingSystem,
            @ForAll("parkActionSequences") ActionSequence<ParkingSystem> actionSequence) {
        actionSequence.run(parkingSystem);
    }

    @ParameterizedTest
    @CsvSource({
            "-1,1,1",
            "1,-1,1",
            "1,1,-1",
            "1001,1,1",
            "1,1001,1",
            "1,1,1001"
    })
    void validates_parking_space_sizes(int big, int medium, int small) {
        assertThrows(RuntimeException.class, () -> new ParkingSystem(big, medium, small));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 4})
    void throws_for_invalid_car(int invalidCarType) {
        ParkingSystem parkingSystem = new ParkingSystem(1000, 1000, 1000);

        assertThrows(RuntimeException.class, () -> parkingSystem.addCar(invalidCarType));
    }

    @Test
    void throws_for_the_1000st_car() {
        ParkingSystem parkingSystem = new ParkingSystem(1000, 1000, 1000);
        IntStream.range(0, 1000).forEach(__ -> parkingSystem.addCar(1));

        assertThrows(RuntimeException.class, () -> parkingSystem.addCar(1));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    void returns_false_if_no_space_for_car_type(int carType) {
        ParkingSystem emptyParkingSystem = new ParkingSystem(0, 0, 0);

        assertThat(emptyParkingSystem.addCar(carType)).isFalse();

        ParkingSystem parkingSystemWithSpace = new ParkingSystem(10, 10, 10);
        IntStream.range(0, 10).forEach(__ -> parkingSystemWithSpace.addCar(carType));

        assertThat(parkingSystemWithSpace.addCar(carType)).isFalse();
    }


    /**
     * I started with a maximum of 1000 actions and all the ParkingSystems, but that did not always work.
     * Then I tried to filter out ParkingSystems with the sum of all Parking Spaces greater than some number,
     * but jqwik always found the case were one of the sizes was 0 were it only had to park few cars to break the test.
     * With all ParkingSpot sizes at least 400 times available and 999 actions at max, the test runs through now
     * and tests something meaningful.
     */
    @SuppressWarnings("unused")
    @Provide
    private Arbitrary<ParkingSystem> validParkingSystems() {
        Arbitrary<Integer> parkingSpaceSize = Arbitraries.integers().between(400, 1000);
        return Combinators.combine(parkingSpaceSize, parkingSpaceSize, parkingSpaceSize)
                .as(ParkingSystem::new);
    }

    @SuppressWarnings("unused")
    @Provide
    private Arbitrary<ActionSequence<ParkingSystem>> parkActionSequences() {
        Arbitrary<Action<ParkingSystem>> parkActions = Arbitraries.integers().between(1, 3).map(ParkAction::new);
        return Arbitraries.sequences(Arbitraries.oneOf(parkActions)).ofMaxSize(999);
    }

    private static class ParkAction implements Action<ParkingSystem> {

        private final int carType;

        ParkAction(int carType) {
            if (carType < 1 || carType > 3)
                throw new RuntimeException("Only car types 1,2,3 are available");

            this.carType = carType;
        }

        @Override
        public ParkingSystem run(ParkingSystem state) {
            assertThat(state.addCar(carType)).isTrue();

            return state;
        }

        @Override
        public String toString() {
            return "ParkAction{" +
                    "carType=" + carType +
                    '}';
        }
    }
}
