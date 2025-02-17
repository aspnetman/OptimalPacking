package ru.liga.optimalpacking.packages.editparcel;

import an.awesome.pipelinr.Command;
import lombok.RequiredArgsConstructor;
import ru.liga.optimalpacking.packages.editparcel.businessrules.EditParcelBusinessRulesChecker;
import ru.liga.optimalpacking.packages.editparcel.dto.EditParcelResponse;
import ru.liga.optimalpacking.packages.shared.ParcelsRepository;

@RequiredArgsConstructor
public class EditParcelCommandHandler implements Command.Handler<EditParcelCommand, EditParcelResponse> {

    private final ParcelsRepository parcelsRepository;

    private final EditParcelBusinessRulesChecker editParcelBusinessRulesChecker;

    private final EditParcelMapper mapper;

    @Override
    public EditParcelResponse handle(EditParcelCommand editParcelCommand) {

        editParcelBusinessRulesChecker.checkParcelExists(editParcelCommand.name());

        parcelsRepository.save(mapper.toEntity(editParcelCommand.parcelDto()));

        return new EditParcelResponse();
    }
}
