package org.gerdenmaintenance.application.filesystem.mapper;

import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Coordinate;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Instruction;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.LawnmowerInstructions;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Orientation;

import java.util.Arrays;
import java.util.function.BiFunction;

public class LawnmowerInstructionsMapper implements BiFunction<String, String, LawnmowerInstructions> {
    @Override
    public LawnmowerInstructions apply(String firstLine, String secondLine) {
        final var firstLineSplitted = firstLine.trim().split(" ");
        final int x = Integer.parseInt(firstLineSplitted[0]);
        final int y = Integer.parseInt(firstLineSplitted[1]);
        final Orientation orientation = Orientation.valueOf(firstLineSplitted[2]);

        final var instructions = Arrays.stream(secondLine.trim().split("")).map(Instruction::valueOf).toList();

        return new LawnmowerInstructions(new Coordinate(x, y), orientation, instructions);
    }
}
