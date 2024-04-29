package org.gerdenmaintenance.application.filesystem;

import org.assertj.core.api.Assertions;
import org.gerdenmaintenance.application.filesystem.exception.LawnmowerFileException;
import org.gerdenmaintenance.application.filesystem.mapper.LawnmowerInstructionsMapper;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Coordinate;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Lawnmower;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.LawnmowerInstructions;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Orientation;
import org.gerdenmaintenance.domain.usecase.mowthelawn.port.input.IMowTheLawnUseCase;
import org.gerdenmaintenance.domain.usecase.mowthelawn.port.input.MowTheLawnCommand;
import org.gerdenmaintenance.domain.usecase.mowthelawn.port.output.MowTheLawnResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.List;

import static org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Instruction.*;

@DisplayName("Lire le fichier d'instructions de la tondeuse")
public class LawnmowerFileSystemReaderTest {

    private LawnmowerInstructionsMapper lawnmowerInstructionsMapperMock;
    private IMowTheLawnUseCase mowTheLawnUseCaseMock;
    private LawnmowerFileSystemReader lawnmowerFileSystemReader;

    @BeforeEach
    void setUp() {
        lawnmowerInstructionsMapperMock = Mockito.mock(LawnmowerInstructionsMapper.class);
        mowTheLawnUseCaseMock = Mockito.mock(IMowTheLawnUseCase.class);

        this.lawnmowerFileSystemReader = new LawnmowerFileSystemReader(mowTheLawnUseCaseMock, lawnmowerInstructionsMapperMock);
    }

    @Test
    @DisplayName("Le fichier d'instructions est au bon format")
    void testProcessLawnmowerFileWithNominalCase() {
        // GIVEN
        final LawnmowerInstructions firstLawnmowerInstructions = new LawnmowerInstructions(new Coordinate(1, 2), Orientation.N, List.of(G, A, G, A, G, A, G, A, A));
        final LawnmowerInstructions secondLawnmowerInstructions = new LawnmowerInstructions(new Coordinate(3, 3), Orientation.E, List.of(A, A, D, A, A, D, A, D, D, A));
        final MowTheLawnCommand expectedCommand = new MowTheLawnCommand(new Coordinate(5, 5), List.of(firstLawnmowerInstructions, secondLawnmowerInstructions));
        final MowTheLawnResult expectedUseCaseResult = new MowTheLawnResult(List.of(
                new Lawnmower(new Coordinate(1, 3), Orientation.N),
                new Lawnmower(new Coordinate(5, 1), Orientation.E)));

        BDDMockito.given(mowTheLawnUseCaseMock.apply(ArgumentMatchers.eq(expectedCommand))).willReturn(expectedUseCaseResult);

        BDDMockito.given(lawnmowerInstructionsMapperMock.apply(ArgumentMatchers.eq("1 2 N"), ArgumentMatchers.eq("GAGAGAGAA"))).willReturn(firstLawnmowerInstructions);
        BDDMockito.given(lawnmowerInstructionsMapperMock.apply(ArgumentMatchers.eq("3 3 E"), ArgumentMatchers.eq("AADAADADDA"))).willReturn(secondLawnmowerInstructions);


        // WHEN
        String result = this.lawnmowerFileSystemReader.processLawnmowerFile("ok_input.txt");


        // THEN
        Assertions.assertThat(result).isEqualTo("1 3 N 5 1 E");
    }

    @Test
    @DisplayName("Le fichier d'instructions n'existe pas")
    void testProcessLawnmowerFileWithNotExistingFile() {
        Assertions.assertThatThrownBy(() -> {
                    // WHEN
                    this.lawnmowerFileSystemReader.processLawnmowerFile("input_does_not_exist.txt");
                })
                .isInstanceOf(LawnmowerFileException.class)
                .hasMessage("Reading file Exception");
    }

    @Test
    @DisplayName("Le fichier d'instructions est vide")
    void testProcessLawnmowerFileWithEmptyFile() {
        Assertions.assertThatThrownBy(() -> {
                    // WHEN
                    this.lawnmowerFileSystemReader.processLawnmowerFile("ok_input_empty.txt");
                })
                .isInstanceOf(LawnmowerFileException.class)
                .hasMessage("Bad number of lines: 0");
    }

    @Test
    @DisplayName("Le fichier d'instructions n'a pas le bon nombre de ligne")
    void testProcessLawnmowerFileWithWrongNumberOfLinesFile() {
        Assertions.assertThatThrownBy(() -> {
                    // WHEN
                    this.lawnmowerFileSystemReader.processLawnmowerFile("ok_input_wrong_number_of_lines.txt");
                })
                .isInstanceOf(LawnmowerFileException.class)
                .hasMessage("Bad number of lines: 4");
    }
}
