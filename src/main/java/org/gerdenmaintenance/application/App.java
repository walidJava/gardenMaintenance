package org.gerdenmaintenance.application;

import org.gerdenmaintenance.application.filesystem.LawnmowerFileSystemReader;
import org.gerdenmaintenance.application.filesystem.mapper.LawnmowerInstructionsMapper;
import org.gerdenmaintenance.domain.usecase.mowthelawn.MowTheLawnUseCase;

import java.util.logging.Logger;

public class App {
    private static final Logger LOGGER = Logger.getLogger(App.class.getName());

    public static void main(String[] args) {
        final LawnmowerFileSystemReader lawnmowerFileSystemReader = new LawnmowerFileSystemReader(new MowTheLawnUseCase(), new LawnmowerInstructionsMapper());
        final var lawnmowerFinalPosition = lawnmowerFileSystemReader.processLawnmowerFile("input.txt");
        LOGGER.info(lawnmowerFinalPosition);
    }
}
