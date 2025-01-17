package ru.liga.optimalpacking.packages.editparcel;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.editparcel.businessrules.EditParcelBusinessRulesChecker;
import ru.liga.optimalpacking.packages.editparcel.dto.EditParcelResponse;

@RequiredArgsConstructor
public class EditParcelCommandHandler implements Command.Handler<EditParcelCommand, EditParcelResponse> {

    private final EditParcelsRepository editParcelsRepository;

    private final EditParcelBusinessRulesChecker editParcelBusinessRulesChecker;

    private final EditParcelMapper mapper;

    @Override
    public EditParcelResponse handle(EditParcelCommand editParcelCommand) {

        editParcelBusinessRulesChecker.checkParcelExists(editParcelCommand.name());

        editParcelsRepository.editParcel(
                editParcelCommand.name(),
                mapper.toEntity(editParcelCommand.parcel()));

        return null;
    }
}
