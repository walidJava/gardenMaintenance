package org.gerdenmaintenance.domain.usecase.mowthelawn.movescommand;

import org.assertj.core.api.Assertions;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayName("Test de la commande RotateRight")
public class RotateRightCommandTest {

    ICommand rotateRightCommand;

    @BeforeEach
    void setUp() {
        rotateRightCommand = ICommand.create(Instruction.D);
    }

    @DisplayName("Tourner à droite à partir d'une orientation")
    @ParameterizedTest
    @CsvSource({"W,N", "S,W", "E,S", "N,E"})
    void testRotateRight(String input, String expected) {
        // GIVEN
        Garden garden = new Garden(new Coordinate(5, 5));
        Lawnmower lawnmower = new Lawnmower(new Coordinate(2, 2), Orientation.valueOf(input));

        // WHEN
        Lawnmower result = rotateRightCommand.process(lawnmower, garden);

        // THEN
        Assertions.assertThat(result).isEqualTo(new Lawnmower(new Coordinate(2, 2), Orientation.valueOf(expected)));
    }
}
