package org.gerdenmaintenance.domain.usecase.mowthelawn.movescommand;

import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Garden;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Lawnmower;

public class RotateLeftCommand implements ICommand {
    @Override
    public Lawnmower process(Lawnmower lawnmower, Garden garden) {
        var nextOrientation = lawnmower.orientation().orientationToTheRight();
        return lawnmower.withOrientation(nextOrientation);
    }
}
