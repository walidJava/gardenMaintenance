package org.gerdenmaintenance.application.filesystem;

import lombok.AllArgsConstructor;
import org.gerdenmaintenance.application.filesystem.exception.LawnmowerFileException;
import org.gerdenmaintenance.application.filesystem.mapper.LawnmowerInstructionsMapper;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.Coordinate;
import org.gerdenmaintenance.domain.usecase.mowthelawn.entity.LawnmowerInstructions;
import org.gerdenmaintenance.domain.usecase.mowthelawn.port.input.IMowTheLawnUseCase;
import org.gerdenmaintenance.domain.usecase.mowthelawn.port.input.MowTheLawnCommand;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@AllArgsConstructor
public class LawnmowerFileSystemReader {

    private final IMowTheLawnUseCase mowTheLawnUseCase;
    private final LawnmowerInstructionsMapper lawnmowerInstructionsMapper;

    private static List<String> readFile(String fileName) {
        List<String> allLines = new ArrayList<>();

        try {
            try (InputStream inputStream = LawnmowerFileSystemReader.class.getClassLoader().getResourceAsStream(fileName)) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                while (reader.ready()) {
                    allLines.add(reader.readLine());
                }
            }
        } catch (Exception e) {
            throw new LawnmowerFileException("Reading file Exception", e);
        }
        return allLines;
    }

    private static boolean isTheGoodNumberOfLines(List<String> allLines) {
        return allLines.size() > 1 && allLines.size() % 2 != 0;
    }

    public String processLawnmowerFile(String fileName) {

        List<String> allLines = readFile(fileName);

        if (!isTheGoodNumberOfLines(allLines)) {
            throw new LawnmowerFileException(String.format("Bad number of lines: %d", allLines.size()));
        }

        MowTheLawnCommand mowTheLawnCommand = buildMowTheLawnCommand(allLines);

        var mowTheLawnResult = mowTheLawnUseCase.apply(mowTheLawnCommand);

        return mowTheLawnResult.toString();
    }

    private MowTheLawnCommand buildMowTheLawnCommand(List<String> allLines) {
        final var firstLineSplitted = allLines.getFirst().trim().split("\\s");
        final int x = Integer.parseInt(firstLineSplitted[0]);
        final int y = Integer.parseInt(firstLineSplitted[1]);
        Coordinate maxSizeOfTheGarden = new Coordinate(x, y);

        List<LawnmowerInstructions> lawnmowerInstructionsLawnmowers = IntStream.range(1, allLines.size() / 2 + 1).mapToObj(i -> {
            String firstLine = allLines.get(i * 2 - 1);
            String secondLine = allLines.get(i * 2);
            return lawnmowerInstructionsMapper.apply(firstLine, secondLine);
        }).toList();

        return new MowTheLawnCommand(maxSizeOfTheGarden, lawnmowerInstructionsLawnmowers);
    }
}
