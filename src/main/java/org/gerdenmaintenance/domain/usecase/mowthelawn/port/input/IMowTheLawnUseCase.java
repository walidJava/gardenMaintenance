package org.gerdenmaintenance.domain.usecase.mowthelawn.port.input;

import org.gerdenmaintenance.domain.usecase.mowthelawn.port.output.MowTheLawnResult;

import java.util.function.Function;

public interface IMowTheLawnUseCase extends Function<MowTheLawnCommand, MowTheLawnResult> {

}
