package org.gerdenmaintenance.domain.usecase.mowthelawn.entity;

import lombok.With;

@With
public record Lawnmower(Coordinate coordinate, Orientation orientation) {
}
