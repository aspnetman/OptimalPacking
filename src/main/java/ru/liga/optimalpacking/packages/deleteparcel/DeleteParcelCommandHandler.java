package ru.liga.optimalpacking.packages.deleteparcel;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.liga.optimalpacking.packages.deleteparcel.businessrules.DeleteParcelBusinessRulesChecker;
import ru.liga.optimalpacking.packages.deleteparcel.dto.DeleteParcelResponse;

@Component
@RequiredArgsConstructor
public class DeleteParcelCommandHandler implements Command.Handler<DeleteParcelCommand, DeleteParcelResponse> {

    private final DeleteParcelsRepository deleteParcelsRepository;

    private final DeleteParcelBusinessRulesChecker deleteParcelBusinessRulesChecker;

    @Override
    public DeleteParcelResponse handle(DeleteParcelCommand deleteParcelCommand) {

        deleteParcelBusinessRulesChecker.checkParcelExists(deleteParcelCommand.name());

        deleteParcelsRepository.deleteParcel(deleteParcelCommand.name());

        return new DeleteParcelResponse();
    }
}