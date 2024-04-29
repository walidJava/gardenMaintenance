package org.gerdenmaintenance.application.filesystem.mapper;

import org.assertj.core.api.Assertions;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Coordinate;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Instruction;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.LawnmowerInstructions;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Orientation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Mapper deux chaines de caractères en instructions")
public class LawnmowerInstructionsMapperTest {

    LawnmowerInstructionsMapper instructionsMapper;

    @BeforeEach
    void setUp() {
        instructionsMapper = new LawnmowerInstructionsMapper();
    }

    @DisplayName("Tester le cas nominal")
    @Test
    void testNominalCase() {
        // GIVEN
        var firstLine = "1 2 N";
        var secondLine = "GAG";

        // WHEN
        var instruction = instructionsMapper.apply(firstLine, secondLine);

        // THEN
        Assertions.assertThat(instruction)
                .isEqualTo(
                        new LawnmowerInstructions(new Coordinate(1, 2), Orientation.N, List.of(Instruction.G, Instruction.A, Instruction.G)));

    }
}
