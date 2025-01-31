package ru.liga.optimalpacking.packages.getparcel;

import an.awesome.pipelinr.Command;
import ru.liga.optimalpacking.packages.getparcel.dto.GetParcelResponse;
import ru.liga.optimalpacking.packages.shared.middlewares.IRequestNotFound;

public record GetParcelQuery(String packageName) implements Command<GetParcelResponse>, IRequestNotFound {

    @Override
    public String getNotFoundErrorMessage() {
        return String.format("Посылка с именем %s не найдена.", packageName);
    }
}
