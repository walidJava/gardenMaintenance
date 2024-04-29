package org.gerdenmaintenance.domain.usecase.mowthelawn.movescommand;

import org.assertj.core.api.Assertions;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Test de la commande RotateLeft")
public class RotateLeftCommandTest {

    ICommand rotateLeftCommand;

    @BeforeEach
    void setUp() {
        rotateLeftCommand = ICommand.create(Instruction.G);
    }

    @DisplayName("Tourner à gauche à partir d'une orientation")
    @ParameterizedTest
    @CsvSource({"N,W", "W,S", "S,E", "E,N"})
    void testRotateLeft(String input, String expected) {
        // GIVEN
        Garden garden = new Garden(new Coordinate(5,5));
        Lawnmower lawnmower = new Lawnmower(new Coordinate(2,2), Orientation.valueOf(input));

        // WHEN
        Lawnmower result = rotateLeftCommand.process(lawnmower, garden);

        // THEN
        Assertions.assertThat(result).isEqualTo(new Lawnmower(new Coordinate(2,2), Orientation.valueOf(expected)));
    }
}
