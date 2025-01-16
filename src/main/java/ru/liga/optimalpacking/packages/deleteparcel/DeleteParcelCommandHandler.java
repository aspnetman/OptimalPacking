package ru.liga.optimalpacking.packages.deleteparcel;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.deleteparcel.businessrules.BusinessRulesChecker;
import ru.liga.optimalpacking.packages.deleteparcel.dto.DeleteParcelResponse;

@RequiredArgsConstructor
public class DeleteParcelCommandHandler implements Command.Handler<DeleteParcelCommand, DeleteParcelResponse> {

    private final DeleteParcelsRepository deleteParcelsRepository;

    private final BusinessRulesChecker businessRulesChecker;

    @Override
    public DeleteParcelResponse handle(DeleteParcelCommand deleteParcelCommand) {

        businessRulesChecker.checkParcelExists(deleteParcelCommand.name());

        deleteParcelsRepository.deleteParcel(deleteParcelCommand.name());

        return new DeleteParcelResponse();
    }
}
