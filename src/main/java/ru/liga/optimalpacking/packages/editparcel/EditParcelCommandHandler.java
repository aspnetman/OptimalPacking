package ru.liga.optimalpacking.packages.editparcel;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.editparcel.businessrules.BusinessRulesChecker;
import ru.liga.optimalpacking.packages.editparcel.dto.EditParcelResponse;

@RequiredArgsConstructor
public class EditParcelCommandHandler implements Command.Handler<EditParcelCommand, EditParcelResponse> {

    private final ParcelsRepository parcelsRepository;

    private final BusinessRulesChecker businessRulesChecker;

    private final EditParcelMapper mapper;

    @Override
    public EditParcelResponse handle(EditParcelCommand editParcelCommand) {

        businessRulesChecker.checkParcelExists(editParcelCommand.name());

        parcelsRepository.editParcel(
                editParcelCommand.name(),
                mapper.toEntity(editParcelCommand.parcel()));

        return null;
    }
}
