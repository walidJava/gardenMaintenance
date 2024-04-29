package org.gerdenmaintenance.domain.usecase.mowthelawn.movescommand;

import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Coordinate;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Garden;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Lawnmower;

public class MoveForwardCommand implements ICommand {
    @Override
    public Lawnmower process(Lawnmower lawnmower, Garden garden) {
        Coordinate nextCoordinate = calculateNextCoordinate(lawnmower, garden);

        return lawnmower.withCoordinate(nextCoordinate);
    }

    private Coordinate calculateNextCoordinate(Lawnmower lawnmower, Garden garden) {
        var currentCoordinate = lawnmower.coordinate();

        Coordinate nextCoordinate = switch (lawnmower.orientation()) {
            case E -> currentCoordinate.rightTranslation();
            case S -> currentCoordinate.downTranslation();
            case W -> currentCoordinate.leftTranslation();
            case N -> currentCoordinate.upTranslation();
        };

        if (isOutGardenLimit(nextCoordinate, garden)) {
            return currentCoordinate;
        }

        return nextCoordinate;
    }

    private boolean isOutGardenLimit(Coordinate coordinate, Garden garden) {
        var cornerCoordinate = garden.upperCornerCoordinate();
       return coordinate.x() > cornerCoordinate.x() ||
               coordinate.y() > cornerCoordinate.y() ||
               coordinate.x() < 0 ||
               coordinate.y() < 0;

    }

}
