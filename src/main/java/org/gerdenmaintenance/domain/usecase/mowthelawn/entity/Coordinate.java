package org.gerdenmaintenance.domain.usecase.mowthelawn.entity;

import lombok.With;

@With
public record Coordinate(int x, int y) {
    public Coordinate rightTranslation() {
        return this.withX(x + 1);
    }

    public Coordinate leftTranslation() {
        return this.withX(x - 1);
    }

    public Coordinate upTranslation() {
        return this.withY(y + 1);
    }

    public Coordinate downTranslation() {
        return this.withY(y - 1);
    }
}
