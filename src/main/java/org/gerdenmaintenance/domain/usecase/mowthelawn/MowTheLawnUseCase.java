package org.gerdenmaintenance.domain.usecase.mowthelawn;

import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Garden;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Lawnmower;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.LawnmowerInstructions;
import org.gerdenmaintenance.domain.usecase.mowthelawn.movescommand.ICommand;
import org.gerdenmaintenance.domain.usecase.mowthelawn.port.input.IMowTheLawnUseCase;
import org.gerdenmaintenance.domain.usecase.mowthelawn.port.input.MowTheLawnCommand;
import org.gerdenmaintenance.domain.usecase.mowthelawn.port.output.MowTheLawnResult;

import java.util.List;

public class MowTheLawnUseCase implements IMowTheLawnUseCase {

    private static Lawnmower processInstructions(LawnmowerInstructions lawnMowerInstructions, Lawnmower lawnmowerInitial, Garden garden) {
        return lawnMowerInstructions.instructions()
                .stream()
                .map(ICommand::create)
                .reduce(lawnmowerInitial, (lawnmower, cmd) -> cmd.process(lawnmower, garden), (previouslawnmowerState, nextLawnmowerState) -> nextLawnmowerState);
    }

    @Override
    public MowTheLawnResult apply(MowTheLawnCommand useCaseCommand) {

        final Garden garden = new Garden(useCaseCommand.cointSuperieur());

        List<Lawnmower> lawnmowerInFinalState = useCaseCommand.lawnmowersInstructions().stream()
                .map(lawnmowerInstructions -> {
                    Lawnmower lawnmowerInFirstState = new Lawnmower(lawnmowerInstructions.coordinateDeDepart(), lawnmowerInstructions.orientation());
                    return processInstructions(lawnmowerInstructions, lawnmowerInFirstState, garden);
                })
                .toList();

        return new MowTheLawnResult(lawnmowerInFinalState);
    }
}
