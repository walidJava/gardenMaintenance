package org.gerdenmaintenance.domain.usecase.mowthelawn.movescommand;

import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Garden;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Instruction;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Lawnmower;

public interface ICommand {
    static ICommand create(Instruction instruction) {
        return switch (instruction) {
            case A -> new MoveForwardCommand();
            case D -> new RotateLeftCommand();
            case G -> new RotateRightCommand();
        };
    }

    Lawnmower process(Lawnmower lawnmower, Garden garden);
}
