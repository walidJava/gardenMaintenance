package org.gerdenmaintenance.domain.usecase.mowthelawn;

import org.assertj.core.api.Assertions;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Coordinate;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Lawnmower;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.LawnmowerInstructions;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Orientation;
import org.gerdenmaintenance.domain.usecase.mowthelawn.port.input.MowTheLawnCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Instruction.*;

@DisplayName("Test du useCase Tondre la pelouse")
public class MowTheLawnUseCaseTest {

    MowTheLawnUseCase mowTheLawnUseCase;

    @BeforeEach
    void setUp() {
        mowTheLawnUseCase = new MowTheLawnUseCase();
    }

    @Test
    @DisplayName("Tondre la pelouse avec deux tondeuses, cas nominal")
    void testNominalCaseWithTwoLawnmower() {

        // GIVEN
        final LawnmowerInstructions firstLawnmowerInstructions = new LawnmowerInstructions(new Coordinate(1, 2), Orientation.N, List.of(G, A, G, A, G, A, G, A, A));
        final LawnmowerInstructions secondLawnmowerInstructions = new LawnmowerInstructions(new Coordinate(3, 3), Orientation.E, List.of(A, A, D, A, A, D, A, D, D, A));
        final MowTheLawnCommand mowTheLawnCommand = new MowTheLawnCommand(new Coordinate(5, 5), List.of(firstLawnmowerInstructions, secondLawnmowerInstructions));

        // WHEN
        var result = mowTheLawnUseCase.apply(mowTheLawnCommand);

        // THEN
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.lawnmowers()).hasSize(2);
        Assertions.assertThat(result.lawnmowers()).containsExactly(
                new Lawnmower(new Coordinate(1, 3), Orientation.N),
                new Lawnmower(new Coordinate(5, 1), Orientation.E)
        );
    }

    @Test
    @DisplayName("Les instructions de la tondeuse dépassent la limite de la pelouse")
    void testInstructionsExceedsLawnBoundary() {
        // Given
        final LawnmowerInstructions lawnMowerInstructions = new LawnmowerInstructions(new Coordinate(1, 1), Orientation.N, List.of(A, A, A, A, A, A, A));
        final MowTheLawnCommand mowTheLawnCommand = new MowTheLawnCommand(new Coordinate(5, 5), List.of(lawnMowerInstructions));


        // WHEN
        var result = mowTheLawnUseCase.apply(mowTheLawnCommand);


        // THEN
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.lawnmowers()).hasSize(1);
        Assertions.assertThat(result.lawnmowers().getFirst()).isEqualTo(
                new Lawnmower(new Coordinate(1, 5), Orientation.N)
        );
    }

    @Test
    @DisplayName("La position initiale de la tondeuse dépasse la limite de la pelouse")
    void testInitialPositionExceedsLawnBoundary2() {
        // Given
        final LawnmowerInstructions lawnMowerInstructions = new LawnmowerInstructions(new Coordinate(10, 10), Orientation.N, List.of(A, A, A, A, A, A, A));
        final MowTheLawnCommand mowTheLawnCommand = new MowTheLawnCommand(new Coordinate(5, 5), List.of(lawnMowerInstructions));


        // WHEN
        var result = mowTheLawnUseCase.apply(mowTheLawnCommand);


        // THEN
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result.lawnmowers()).hasSize(1);
        Assertions.assertThat(result.lawnmowers().getFirst()).isEqualTo(
                new Lawnmower(new Coordinate(10, 10), Orientation.N)
        );
    }
}
