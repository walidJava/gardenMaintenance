package org.gerdenmaintenance.domain.usecase.mowthelawn.entity;


import java.util.List;

public record LawnmowerInstructions(Coordinate coordinateDeDepart, Orientation orientation,
                                    List<Instruction> instructions) {
}
