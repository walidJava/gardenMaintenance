package org.gerdenmaintenance.domain.usecase.mowthelawn.movescommand;

import org.assertj.core.api.Assertions;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Test de la commande MoveForward")
public class MoveForwardCommandTest {

    ICommand moveForwardCommand;

    @BeforeEach
    void setUp() {
        moveForwardCommand = ICommand.create(Instruction.A);
    }

    @DisplayName("Avancer vers le N sans atteindre la bordure")
    @Test
    void testMoveToOrientationN() {
        // GIVEN
        Garden garden = new Garden(new Coordinate(5, 5));
        Lawnmower lawnmower = new Lawnmower(new Coordinate(2, 2), Orientation.N);

        // WHEN
        Lawnmower result = moveForwardCommand.process(lawnmower, garden);

        // THEN
        Assertions.assertThat(result).isEqualTo(new Lawnmower(new Coordinate(2, 3), Orientation.N));
    }

    @DisplayName("Avancer vers le N en dépassant la limite du jardin")
    @Test
    void testMoveToOrientationN_whenLawnmowerAtGardenLimit() {
        // GIVEN
        Garden garden = new Garden(new Coordinate(5, 5));
        Lawnmower lawnmower = new Lawnmower(new Coordinate(2, 5), Orientation.N);

        // WHEN
        Lawnmower result = moveForwardCommand.process(lawnmower, garden);

        // THEN
        Assertions.assertThat(result).isEqualTo(new Lawnmower(new Coordinate(2, 5), Orientation.N));
    }

    @DisplayName("Avancer vers le E sans atteindre la limite du jardin")
    @Test
    void testMoveToOrientationE() {
        // GIVEN
        Garden garden = new Garden(new Coordinate(5, 5));
        Lawnmower lawnmower = new Lawnmower(new Coordinate(2, 2), Orientation.E);

        // WHEN
        Lawnmower result = moveForwardCommand.process(lawnmower, garden);

        // THEN
        Assertions.assertThat(result).isEqualTo(new Lawnmower(new Coordinate(3, 2), Orientation.E));
    }

    @DisplayName("Avancer vers le E en dépassant la limite du jardin")
    @Test
    void testMoveToOrientationE_whenLawnmowerAtGardenLimit() {
        // GIVEN
        Garden garden = new Garden(new Coordinate(5, 5));
        Lawnmower lawnmower = new Lawnmower(new Coordinate(5, 2), Orientation.E);

        // WHEN
        Lawnmower result = moveForwardCommand.process(lawnmower, garden);

        // THEN
        Assertions.assertThat(result).isEqualTo(new Lawnmower(new Coordinate(5, 2), Orientation.E));
    }

    @DisplayName("Avancer vers le W sans atteindre la limite du jardin")
    @Test
    void testMoveToOrientationW() {
        // GIVEN
        Garden garden = new Garden(new Coordinate(5, 5));
        Lawnmower lawnmower = new Lawnmower(new Coordinate(2, 2), Orientation.W);

        // WHEN
        Lawnmower result = moveForwardCommand.process(lawnmower, garden);

        // THEN
        Assertions.assertThat(result).isEqualTo(new Lawnmower(new Coordinate(1, 2), Orientation.W));
    }

    @DisplayName("Avancer vers le W en dépassant la limite du jardin")
    @Test
    void testMoveToOrientationW_whenLawnmowerAtGardenLimit() {
        // GIVEN
        Garden garden = new Garden(new Coordinate(5, 5));
        Lawnmower lawnmower = new Lawnmower(new Coordinate(0, 2), Orientation.W);

        // WHEN
        Lawnmower result = moveForwardCommand.process(lawnmower, garden);

        // THEN
        Assertions.assertThat(result).isEqualTo(new Lawnmower(new Coordinate(0, 2), Orientation.W));
    }

    @DisplayName("Avancer vers le S sans atteindre la limite du jardin")
    @Test
    void testMoveToOrientationS() {
        // GIVEN
        Garden garden = new Garden(new Coordinate(5, 5));
        Lawnmower lawnmower = new Lawnmower(new Coordinate(2, 2), Orientation.S);

        // WHEN
        Lawnmower result = moveForwardCommand.process(lawnmower, garden);

        // THEN
        Assertions.assertThat(result).isEqualTo(new Lawnmower(new Coordinate(2, 1), Orientation.S));
    }

    @DisplayName("Avancer vers le S en dépassant la limite du jardin")
    @Test
    void testMoveToOrientationS_whenLawnmowerAtGardenLimit() {
        // GIVEN
        Garden garden = new Garden(new Coordinate(5, 5));
        Lawnmower lawnmower = new Lawnmower(new Coordinate(2, 0), Orientation.S);

        // WHEN
        Lawnmower result = moveForwardCommand.process(lawnmower, garden);

        // THEN
        Assertions.assertThat(result).isEqualTo(new Lawnmower(new Coordinate(2, 0), Orientation.S));
    }
}
