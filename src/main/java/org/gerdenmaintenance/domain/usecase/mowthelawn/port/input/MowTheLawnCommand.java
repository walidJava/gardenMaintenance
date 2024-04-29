package org.gerdenmaintenance.domain.usecase.mowthelawn.port.input;

import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Coordinate;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.LawnmowerInstructions;

import java.util.List;

public record MowTheLawnCommand(Coordinate cointSuperieur,
                                List<LawnmowerInstructions> lawnmowersInstructions) {
}
