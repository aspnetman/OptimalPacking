package ru.liga.optimalpacking.packages.editparcel;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.editparcel.businessrules.BusinessRulesChecker;
import ru.liga.optimalpacking.packages.editparcel.dto.EditParcelResponse;

@RequiredArgsConstructor
public class EditParcelCommandHandler implements Command.Handler<EditParcelCommand, EditParcelResponse> {

    private final EditParcelsRepository editParcelsRepository;

    private final BusinessRulesChecker businessRulesChecker;

    private final EditParcelMapper mapper;

    @Override
    public EditParcelResponse handle(EditParcelCommand editParcelCommand) {

        businessRulesChecker.checkParcelExists(editParcelCommand.name());

        editParcelsRepository.editParcel(
                editParcelCommand.name(),
                mapper.toEntity(editParcelCommand.parcel()));

        return null;
    }
}
