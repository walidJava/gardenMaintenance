package org.gerdenmaintenance.domain.usecase.mowthelawn.port.output;

import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Lawnmower;

import java.util.List;
import java.util.stream.Collectors;

public record MowTheLawnResult(List<Lawnmower> lawnmowers) {

    public String toString() {
        return lawnmowers.stream()
                .map(lawnmower -> String.format("%d %d %s", lawnmower.coordinate().x(), lawnmower.coordinate().y(), lawnmower.orientation().toString()))
                .collect(Collectors.joining(" "));
    }
}
